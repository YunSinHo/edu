package com.docedu.web.admin.controller;

/*
 * 팝빌 문자 API Java SDK SpringMVC Example
 *
 * - SpringMVC SDK 연동환경 설정방법 안내 : https://docs.popbill.com/message/tutorial/java
 * - 업데이트 일자 : 2022-10-06
 * - 연동 기술지원 연락처 : 1600-9854
 * - 연동 기술지원 이메일 : code@linkhubcorp.com
 *
 * <테스트 연동개발 준비사항>
 * 1) src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml 파일에 선언된
 *    util:properties 의 링크아이디(LinkID)와 비밀키(SecretKey)를 연동신청 시 메일로
 *    발급받은 인증정보를 참조하여 변경합니다.
 * 2) 발신번호 사전등록을 합니다. (등록방법은 사이트/API 두가지 방식이 있습니다.)
 *    - 1. 팝빌 사이트 로그인 > [문자/팩스] > [문자] > [발신번호 사전등록] 메뉴에서 등록
 *    - 2. getSenderNumberMgtURL API를 통해 반환된 URL을 이용하여 발신번호 등록
 *
 * Copyright 2006-2014 linkhub.co.kr, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import java.io.File;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.popbill.api.ChargeInfo;
import com.popbill.api.MessageService;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.message.AutoDeny;
import com.popbill.api.message.MSGSearchResult;
import com.popbill.api.message.Message;
import com.popbill.api.message.MessageBriefInfo;
import com.popbill.api.message.MessageType;
import com.popbill.api.message.SenderNumber;
import com.popbill.api.message.SentMessage;

/*
 * 팝빌 문자메시지 API 예제.
 */
@Controller
public class MessageServiceExample {

    @Autowired
    private MessageService messageService;

    // 팝빌회원 사업자번호
    @Value("#{EXAMPLE_CONFIG.TestCorpNum}")
    private String testCorpNum;

    // 팝빌회원 아이디
    @Value("#{EXAMPLE_CONFIG.TestUserID}")
    private String testUserID;

    @RequestMapping(value = "/MessageService.mdo", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "Message/index";
    }

    @RequestMapping(value = "/checkSenderNumber.mdo", method = RequestMethod.GET)
    public String checkSenderNumber(Model m) {
        /*
         * 문자 발신번호 등록여부를 확인합니다.
         * c
         */
        try {

            // 확인할 발신번호
            String sender = "010-2866-8428";

            Response response = messageService.checkSenderNumber(testCorpNum, sender);

            m.addAttribute("Response", response);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/response";
    }

    @RequestMapping(value = "/getSenderNumberMgtURL.mdo", method = RequestMethod.GET)
    public String getSenderNumberMgtURL(Model m) {
        /*
         * 발신번호를 등록하고 내역을 확인하는 문자 발신번호 관리 페이지 팝업 URL을 반환합니다.
         * - 반환되는 URL은 보안 정책상 30초 동안 유효하며, 시간을 초과한 후에는 해당 URL을 통한 페이지 접근이 불가합니다.
         * - https://docs.popbill.com/message/java/api#GetSenderNumberMgtURL
         */
        try {

            String url = messageService.getSenderNumberMgtURL(testCorpNum, testUserID);

            m.addAttribute("Result", url);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/result";
    }

    @RequestMapping(value = "/getSenderNumberList.mdo", method = RequestMethod.GET)
    public String getSenderNumberList(Model m) {
        /*
         * 팝빌에 등록한 연동회원의 문자 발신번호 목록을 확인합니다.
         * - https://docs.popbill.com/message/java/api#GetSenderNumberList
         */

        try {
            SenderNumber[] senderNumberList = messageService.getSenderNumberList(testCorpNum);
            m.addAttribute("SenderNumberList", senderNumberList);
        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }
        return "Message/SenderNumber";
    }

    @RequestMapping(value = "/sendSMS.mdo", method = RequestMethod.GET)
    public String sendSMS(Model m) {
        /*
         * 최대 90byte의 단문(SMS) 메시지 1건 전송을 팝빌에 접수합니다.
         * - https://docs.popbill.com/message/java/api#SendSMS
         */

        // 발신번호 (팝빌에 등록된 발신번호만 이용가능)
        String sender = "01028668428";

        // 수신번호
        String receiver = "01028668428";

        // 수신자명
        String receiverName = "수신자명";

        // 메시지 내용, 90byte 초과된 내용은 삭제되어 전송
        // └ 한글, 한자, 특수문자 2byte / 영문, 숫자, 공백 1byte
        String content = "문자메시지\r내용";

        // 전송예약일시, null인 경우 즉시전송
        Date reserveDT = null;

        // 광고 메시지 여부 ( true , false 중 택 1)
        // └ true = 광고 , false = 일반
        Boolean adsYN = false;

        // 전송요청번호
        // 팝빌이 접수 단위를 식별할 수 있도록 파트너가 할당한 식별번호.
        // 1~36자리로 구성. 영문, 숫자, 하이픈(-), 언더바(_)를 조합하여 팝빌 회원별로 중복되지 않도록 할당.
        String requestNum = "";

        try {

            String receiptNum = messageService.sendSMS(testCorpNum, sender, receiver,
                    receiverName, content, reserveDT, adsYN, testUserID, requestNum);

            m.addAttribute("Result", receiptNum);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/result";
    }

    @RequestMapping(value = "/sendSMS_Multi.mdo", method = RequestMethod.GET)
    public String sendSMS_Multi(Model m) {
        /*
         * 최대 90byte의 단문(SMS) 메시지 다수건 전송을 팝빌에 접수합니다. (최대 1,000건)
         * - 모든 수신자에게 동일한 내용을 전송하거나(동보전송), 수신자마다 개별 내용을 전송할 수 있습니다(대량전송).
         * - https://docs.popbill.com/message/java/api#SendSMS_Multi
         */

        // [동보전송시 필수] 발신번호, 개별문자 전송정보에 발신자번호 없는 경우 적용
        String sender = "07043042991";

        // [동보전송시 필수] 메시지 내용, 개별문자 전송정보에 문자내용이 없는 경우 적용
        // └ 한글, 한자, 특수문자 2byte / 영문, 숫자, 공백 1byte
        String content = "대량문자 메시지 내용";

        // 전송 정보 배열, 최대 1000건.
        Message[] messages = new Message[2];

        Message msg1 = new Message();
        msg1.setSender("07043042991");    // 발신번호
        msg1.setSenderName("발신자1");    // 발신자명
        msg1.setReceiver("010111222");    // 수신번호
        msg1.setReceiverName("수신자1");    // 수신자명
        msg1.setContent("메시지 내용1");    // 메시지내용
        msg1.setInterOPRefKey("20221006-SMS001");    // 파트너 지정키
        messages[0] = msg1;

        Message msg2 = new Message();
        msg2.setSender("07043042991");
        msg2.setSenderName("발신자2");
        msg2.setReceiver("010333444");
        msg2.setReceiverName("수신자2");
        msg2.setContent("메시지 내용2");
        msg2.setInterOPRefKey("20221006-SMS002");
        messages[1] = msg2;

        // 전송예약일시, null인 경우 즉시전송
        Date reserveDT = null;

        // 광고 메시지 여부 ( true , false 중 택 1)
        // └ true = 광고 , false = 일반
        Boolean adsYN = false;

        // 전송요청번호
        // 팝빌이 접수 단위를 식별할 수 있도록 파트너가 할당한 식별번호.
        // 1~36자리로 구성. 영문, 숫자, 하이픈(-), 언더바(_)를 조합하여 팝빌 회원별로 중복되지 않도록 할당.
        String requestNum = "";

        try {
            String receiptNum = messageService.sendSMS(testCorpNum, sender, content,
                    messages, reserveDT, adsYN, testUserID, requestNum);

            m.addAttribute("Result", receiptNum);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/result";
    }

    @RequestMapping(value = "/sendLMS.mdo", method = RequestMethod.GET)
    public String sendLMS(Model m) {
        /*
         * 최대 2,000byte의 장문(LMS) 메시지 1건 전송을 팝빌에 접수합니다.
         *  - https://docs.popbill.com/message/java/api#SendLMS
         */

        // 발신번호 (팝빌에 등록된 발신번호만 이용가능)
        String sender = "07043042991";

        // 수신번호
        String receiver = "000111222";

        // 수신자명
        String receiverName = "수신자";

        // 문자메시지 제목
        String subject = "장문문자 제목";

        // 장문 메시지 내용, 2000byte 초과시 길이가 조정되어 전송
        // └ 한글, 한자, 특수문자 2byte / 영문, 숫자, 공백 1byte
        String content = "장문 문자메시지 내용";

        // 전송예약일시, null인 경우 즉시전송
        Date reserveDT = null;

        // 광고 메시지 여부 ( true , false 중 택 1)
        // └ true = 광고 , false = 일반
        Boolean adsYN = false;

        // 전송요청번호
        // 팝빌이 접수 단위를 식별할 수 있도록 파트너가 할당한 식별번호.
        // 1~36자리로 구성. 영문, 숫자, 하이픈(-), 언더바(_)를 조합하여 팝빌 회원별로 중복되지 않도록 할당.
        String requestNum = "";

        try {

            String receiptNum = messageService.sendLMS(testCorpNum, sender, receiver,
                    receiverName, subject, content, reserveDT, adsYN, testUserID, requestNum);

            m.addAttribute("Result", receiptNum);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/result";
    }

    @RequestMapping(value = "/sendLMS_Multi.mdo", method = RequestMethod.GET)
    public String sendLMS_Multi(Model m) {
        /*
         * 최대 2,000byte의 장문(LMS) 메시지 다수건 전송을 팝빌에 접수합니다. (최대 1,000건)
         * - 모든 수신자에게 동일한 내용을 전송하거나(동보전송), 수신자마다 개별 내용을 전송할 수 있습니다(대량전송).
         * - https://docs.popbill.com/message/java/api#SendLMS_Multi
         */

        // [동보전송시 필수] 발신번호, 개별 전송정보의 발신번호가 없는 경우 적용
        String sender = "07043042991";

        // [동보전송시 필수] 메시지 제목, 개별 전송정보의 메시지 제목이 없는 경우 적용
        String subject = "대량전송 제목";

        // [동보전송시 필수] 메시지 내용, 개별 전송정보의 메시지 내용이 없는 경우 적용
        // └ 한글, 한자, 특수문자 2byte / 영문, 숫자, 공백 1byte
        String content = "대량전송 내용";

        // 전송 정보 배열, 최대 1000건.
        Message[] messages = new Message[2];

        Message msg1 = new Message();
        msg1.setSender("07043042991");       // 발신번호
        msg1.setSenderName("발신자1");         // 발신자명
        msg1.setReceiver("010111222");       // 수신번호
        msg1.setReceiverName("수신자1");       // 수신자명
        msg1.setSubject("장문 메시지 제목");      // 문자제목
        msg1.setContent("메시지 내용1");        // 메시지내용
        msg1.setInterOPRefKey("20221006-LMS001");    // 파트너 지정키
        messages[0] = msg1;

        Message msg2 = new Message();
        msg2.setSender("07043042991");
        msg2.setSenderName("발신자2");
        msg2.setReceiver("010333444");
        msg2.setReceiverName("수신자2");
        msg2.setSubject("장문 메시지 제목");
        msg2.setContent("메시지 내용2");
        msg2.setInterOPRefKey("20221006-LMS002");
        messages[1] = msg2;

        // 전송예약일시, null인 경우 즉시전송
        Date reserveDT = null;

        // 광고 메시지 여부 ( true , false 중 택 1)
        // └ true = 광고 , false = 일반
        Boolean adsYN = false;

        // 전송요청번호
        // 팝빌이 접수 단위를 식별할 수 있도록 파트너가 할당한 식별번호.
        // 1~36자리로 구성. 영문, 숫자, 하이픈(-), 언더바(_)를 조합하여 팝빌 회원별로 중복되지 않도록 할당.
        String requestNum = "";

        try {

            String receiptNum = messageService.sendLMS(testCorpNum, sender, subject,
                    content, messages, reserveDT, adsYN, testUserID, requestNum);

            m.addAttribute("Result", receiptNum);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/result";
    }

    @RequestMapping(value = "/sendMMS.mdo", method = RequestMethod.GET)
    public String sendMMS(Model m) {
        /*
         * 최대 2,000byte의 메시지와 이미지로 구성된 포토문자(MMS) 1건 전송을 팝빌에 접수합니다.
         * - 이미지 파일 포맷/규격 : 최대 300Kbyte(JPEG, JPG), 가로/세로 1,000px 이하 권장
         * - https://docs.popbill.com/message/java/api#SendMMS
         */

        // 발신번호 (팝빌에 등록된 발신번호만 이용가능)
        String sender = "01028668428";

        // 수신번호
        String receiver = "01028668428";

        // 수신자명
        String receiverName = "수신자";

        // 메시지 제목
        String subject = "포토 문자 제목";

        // 메시지 내용
        // └ 한글, 한자, 특수문자 2byte / 영문, 숫자, 공백 1byte
        String content = "멀티 문자메시지 내용";

        // 전송할 이미지 파일 경로
        File file = new File("D:/당근라이브러리/jpeg-optimizer_5.jpg");

        // 전송예약일시, null인 경우 즉시전송
        Date reserveDT = null;

        // 광고 메시지 여부 ( true , false 중 택 1)
        // └ true = 광고 , false = 일반
        Boolean adsYN = false;

        // 전송요청번호
        // 팝빌이 접수 단위를 식별할 수 있도록 파트너가 할당한 식별번호.
        // 1~36자리로 구성. 영문, 숫자, 하이픈(-), 언더바(_)를 조합하여 팝빌 회원별로 중복되지 않도록 할당.
        String requestNum = "";

        try {

            String receiptNum = messageService.sendMMS(testCorpNum, sender, receiver,
                    receiverName, subject, content, file, reserveDT, adsYN, testUserID, requestNum);

            m.addAttribute("Result", receiptNum);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/result";
    }

    @RequestMapping(value = "/sendMMS_Multi.mdo", method = RequestMethod.GET)
    public String sendMMS_Multi(Model m) {
        /*
         * 최대 2,000byte의 메시지와 이미지로 구성된 포토문자(MMS) 다수건 전송을 팝빌에 접수합니다. (최대 1,000건)
         * - 모든 수신자에게 동일한 내용을 전송하거나(동보전송), 수신자마다 개별 내용을 전송할 수 있습니다(대량전송).
         * - 이미지 파일 포맷/규격 : 최대 300Kbyte(JPEG), 가로/세로 1,000px 이하 권장
         * - https://docs.popbill.com/message/java/api#SendMMS_Multi
         */

        // [동보전송시 필수] 발신번호, 개별 전송정보의 발신번호가 없는 경우 적용
        String sender = "07043042991";

        // [동보전송시 필수] 메시지 제목, 개별 전송정보의 메시지 제목이 없는 경우 적용
        String subject = "대량전송 제목";

        // [동보전송시 필수] 메시지 내용, 개별 전송정보의 메시지 내용이 없는 경우 적용
        // └ 한글, 한자, 특수문자 2byte / 영문, 숫자, 공백 1byte
        String content = "대량전송 메시지 내용";

        // 전송 정보 배열, 최대 1000건.
        Message[] messages = new Message[2];

        Message msg1 = new Message();
        msg1.setSender("07043042991");       // 발신번호
        msg1.setSenderName("발신자1");       // 발신자명
        msg1.setReceiver("010111222");       // 수신번호
        msg1.setReceiverName("수신자1");     // 수신자명
        msg1.setSubject("멀티 메시지 제목"); // 문자제목
        msg1.setContent("메시지 내용1");     // 메시지내용
        msg1.setInterOPRefKey("20221006-MMS001");    // 파트너 지정키
        messages[0] = msg1;

        Message msg2 = new Message();
        msg2.setSender("07043042991");
        msg2.setSenderName("발신자2");
        msg2.setReceiver("010333444");
        msg2.setReceiverName("수신자2");
        msg2.setSubject("멀티 메시지 제목");
        msg2.setContent("메시지 내용2");
        msg2.setInterOPRefKey("20221006-MMS001");
        messages[1] = msg2;

        // 전송할 이미지 파일 경로
        File file = new File("C:/test.jpg");

        // 전송예약일시, null인 경우 즉시전송
        Date reserveDT = null;

        // 광고 메시지 여부 ( true , false 중 택 1)
        // └ true = 광고 , false = 일반
        Boolean adsYN = false;

        // 전송요청번호
        // 팝빌이 접수 단위를 식별할 수 있도록 파트너가 할당한 식별번호.
        // 1~36자리로 구성. 영문, 숫자, 하이픈(-), 언더바(_)를 조합하여 팝빌 회원별로 중복되지 않도록 할당.
        String requestNum = "";

        try {
            String receiptNum = messageService.sendMMS(testCorpNum, sender, subject,
                    content, messages, file, reserveDT, adsYN, testUserID, requestNum);

            m.addAttribute("Result", receiptNum);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/result";
    }

    @RequestMapping(value = "/sendXMS.mdo", method = RequestMethod.GET)
    public String sendXMS(Model m) {
        /*
         * 메시지 길이(90byte)에 따라 단문/장문(SMS/LMS)을 자동으로 인식하여 1건의 메시지를 전송을 팝빌에 접수합니다.
         * - https://docs.popbill.com/message/java/api#SendXMS
         */
        // 발신번호 (팝빌에 등록된 발신번호만 이용가능)
        String sender = "07043042991";

        // 수신번호
        String receiver = "010111222";

        // 수신자명
        String receiverName = "수신자";

        // 문자메시지 제목
        String subject = "장문문자 제목";

        // 문자메시지 내용, 90Byte를 기준으로 단문과 장문을 자동인식하여 전송.
        // └ 한글, 한자, 특수문자 2byte / 영문, 숫자, 공백 1byte
        String content = "문자메시지 내용";

        // 전송예약일시, null인 경우 즉시전송
        Date reserveDT = null;

        // 광고 메시지 여부 ( true , false 중 택 1)
        // └ true = 광고 , false = 일반
        Boolean adsYN = false;

        // 전송요청번호
        // 팝빌이 접수 단위를 식별할 수 있도록 파트너가 할당한 식별번호.
        // 1~36자리로 구성. 영문, 숫자, 하이픈(-), 언더바(_)를 조합하여 팝빌 회원별로 중복되지 않도록 할당.
        String requestNum = "";

        try {

            String receiptNum = messageService.sendXMS(testCorpNum, sender, receiver,
                    receiverName, subject, content, reserveDT, adsYN, testUserID, requestNum);

            m.addAttribute("Result", receiptNum);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/result";
    }

    @RequestMapping(value = "/sendXMS_Multi.mdo", method = RequestMethod.GET)
    public String sendXMS_Multi(Model m) {
        /*
         * 메시지 길이(90byte)에 따라 단문/장문(SMS/LMS)을 자동으로 인식하여 다수건의 메시지 전송을 팝빌에 접수합니다. (최대 1,000건)
         * - https://docs.popbill.com/message/java/api#SendXMS_Multi
         */

        // [동보전송시 필수] 발신번호, 개별 전송정보에 발신번호가 없는 경우 적용
        String sender = "07043042991";

        // [동보전송시 필수] 문자메시지 제목, 개별 전송정보에 메시지 제목이 없는 경우 적용
        String subject = "장문문자 제목";

        // [동보전송시 필수] 문자메시지 내용, 개별 전송정보에 메시지 내용이 없는 경우 적용
        String content = "문자메시지 내용";

        // 전송 정보 배열, 최대 1000건.
        Message[] messages = new Message[2];

        Message msg1 = new Message();
        msg1.setSender("07043042991");      // 발신번호
        msg1.setSenderName("발신자1");      // 발신자명
        msg1.setReceiver("010111222");      // 수신번호
        msg1.setReceiverName("수신자1");    // 수신자명
        msg1.setContent("메시지 내용1");    // 메시지내용
        msg1.setInterOPRefKey("20221006-XMS001");   // 파트너 지정키
        messages[0] = msg1;

        Message msg2 = new Message();
        msg2.setSender("07043042991");
        msg2.setSenderName("발신자2");
        msg2.setReceiver("010333444");
        msg2.setReceiverName("수신자2");
        msg2.setContent("메시지 내용2");
        msg2.setInterOPRefKey("20221006-XMS001");
        messages[1] = msg2;

        // 전송예약일시, null인 경우 즉시전송
        Date reserveDT = null;

        // 광고 메시지 여부 ( true , false 중 택 1)
        // └ true = 광고 , false = 일반
        Boolean adsYN = false;

        // 전송요청번호
        // 팝빌이 접수 단위를 식별할 수 있도록 파트너가 할당한 식별번호.
        // 1~36자리로 구성. 영문, 숫자, 하이픈(-), 언더바(_)를 조합하여 팝빌 회원별로 중복되지 않도록 할당.
        String requestNum = "";

        try {

            String receiptNum = messageService.sendXMS(testCorpNum, sender, subject,
                    content, messages, reserveDT, adsYN, testUserID, requestNum);

            m.addAttribute("Result", receiptNum);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/result";
    }

    @RequestMapping(value = "/cancelReserve.mdo", method = RequestMethod.GET)
    public String cancelReserve(Model m) {
        /*
         * 팝빌에서 반환받은 접수번호를 통해 예약접수된 문자 메시지 전송을 취소합니다. (예약시간 10분 전까지 가능)
         * - https://docs.popbill.com/message/java/api#CancelReserve
         */

        // 예약문자 전송요청 시 팝빌로부터 반환 받은 접수번호
        String receiptNum = "022022111000000012";

        try {
            Response response = messageService.cancelReserve(testCorpNum, receiptNum);

            m.addAttribute("Response", response);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/response";
    }

    @RequestMapping(value = "/cancelReserveRN.mdo", method = RequestMethod.GET)
    public String cancelReserveRN(Model m) {
        /*
         * 파트너가 할당한 전송요청 번호를 통해 예약접수된 문자 전송을 취소합니다. (예약시간 10분 전까지 가능)
         * - https://docs.popbill.com/message/java/api#CancelReserveRN
         */

        // 예약문자 전송요청 시 파트너가 할당한 전송요청 번호
        String requestNum = "";

        try {
            Response response = messageService.cancelReserveRN(testCorpNum, requestNum);

            m.addAttribute("Response", response);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/response";
    }
    
    @RequestMapping(value = "/cancelReservebyRCV.mdo", method = RequestMethod.GET)
    public String cancelReservebyRCV(Model m) {
        /*
         * 팝빌에서 반환받은 접수번호와 수신번호를 통해 예약접수된 문자 메시지 전송을 취소합니다. (예약시간 10분 전까지 가능)
         * - https://docs.popbill.com/message/java/api#CancelReservebyRCV
         */
        
        // 예약문자 전송요청 시 팝빌로부터 반환 받은 접수번호
        String receiptNum = "";
        // 예약문자 전송요청 시 파트너가 요청한 수신번호
        String receiveNum = "";
        
        try {
            Response response = messageService.cancelReservebyRCV(testCorpNum, receiptNum, receiveNum);
            
            m.addAttribute("Response", response);
            
        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }
        
        return "Message/response";
    }
    
    @RequestMapping(value = "/cancelReserveRNbyRCV.mdo", method = RequestMethod.GET)
    public String cancelReserveRNbyRCV(Model m) {
        /*
         * 파트너가 할당한 전송요청 번호와 수신번호를 통해 예약접수된 문자 전송을 취소합니다. (예약시간 10분 전까지 가능)
         * - https://docs.popbill.com/message/java/api#CancelReserveRNbyRCV
         */
        
        // 예약문자 전송요청 시 파트너가 할당한 전송요청 번호
        String requestNum = "";
        // 예약문자 전송요청 시 파트너가 요청한 수신번호
        String receiveNum = "";
        
        try {
            Response response = messageService.cancelReserveRNbyRCV(testCorpNum, requestNum, receiveNum);
            
            m.addAttribute("Response", response);
            
        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }
        
        return "Message/response";
    }

    @RequestMapping(value = "/getMessages.mdo", method = RequestMethod.GET)
    public String getMessages(Model m) {
        /*
         * 팝빌에서 반환받은 접수번호를 통해 문자 전송상태 및 결과를 확인합니다.
         * - https://docs.popbill.com/message/java/api#GetMessages
         */

        // 문자 전송요청 시 팝빌로부터 반환 받은 접수번호
        String receiptNum = "022100616000000003";

        try {

            SentMessage[] sentMessages = messageService.getMessages(testCorpNum, receiptNum);

            m.addAttribute("SentMessages", sentMessages);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/SentMessage";
    }

    @RequestMapping(value = "/getMessagesRN.mdo", method = RequestMethod.GET)
    public String getMessagesRN(Model m) {
        /*
         * 파트너가 할당한 전송요청 번호를 통해 문자 전송상태 및 결과를 확인합니다.
         * - https://docs.popbill.com/message/java/api#GetMessagesRN
         */

        // 문자 전송요청시 파트너가 할당한 전송요청 번호
        String requestNum = "";

        try {

            SentMessage[] sentMessages = messageService.getMessagesRN(testCorpNum, requestNum);

            m.addAttribute("SentMessages", sentMessages);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/SentMessage";
    }

    @RequestMapping(value = "/search.mdo", method = RequestMethod.GET)
    public String search(Model m) {
        /*
         * 검색조건에 해당하는 문자 전송내역을 조회합니다. (조회기간 단위 : 최대 2개월)
         * - 문자 접수일시로부터 6개월 이내 접수건만 조회할 수 있습니다.
         * - https://docs.popbill.com/message/java/api#Search
         */

        // 시작일자, 날짜형식(yyyyMMdd)
        String SDate = "20221001";

        // 종료일자, 날짜형식(yyyyMMdd)
        String EDate = "20221006";

        // 전송상태 배열 ("1" , "2" , "3" , "4" 중 선택, 다중 선택 가능)
        // └ 1 = 대기 , 2 = 성공 , 3 = 실패 , 4 = 취소
        // - 미입력 시 전체조회
        String[] State = {"1.mdo", "2.mdo", "3.mdo", "4"};

        // 검색대상 배열 ("SMS" , "LMS" , "MMS" 중 선택, 다중 선택 가능)
        // └ SMS = 단문 , LMS = 장문 , MMS = 포토문자
        // - 미입력 시 전체조회
        String[] Item = {"SMS.mdo", "LMS.mdo", "MMS"};

        // 예약여부 (false , true 중 택 1)
        // └ false = 전체조회, true = 예약전송건 조회
        // - 미입력시 기본값 false 처리
        Boolean ReserveYN = false;

        // 개인조회 여부 (false , true 중 택 1)
        // └ false = 접수한 문자 전체 조회 (관리자권한)
        // └ true = 해당 담당자 계정으로 접수한 문자만 조회 (개인권한)
        // - 미입력시 기본값 false 처리
        Boolean SenderYN = false;

        // 페이지 번호
        int Page = 1;

        // 페이지당 목록개수 (최대 1000건)
        int PerPage = 20;

        // 문자 접수일시를 기준으로 하는 목록 정렬 방향 ("D" , "A" 중 택 1)
        // └ D = 내림차순(기본값) , A = 오름차순
        String Order = "D";

        // 조회하고자 하는 발신자명 또는 수신자명
        // - 미입력시 전체조회
        String QString = "";

        try {

            MSGSearchResult response = messageService.search(testCorpNum, SDate,
                    EDate, State, Item, ReserveYN, SenderYN, Page, PerPage, Order, QString);

            m.addAttribute("SearchResult", response);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }
        return "Message/SearchResult";
    }

    @RequestMapping(value = "/getSentListURL.mdo", method = RequestMethod.GET)
    public String getSentListURL(Model m) {
        /*
         * 문자 전송내역 확인 페이지의 팝업 URL을 반환합니다.
         * - 반환되는 URL은 보안 정책상 30초 동안 유효하며, 시간을 초과한 후에는 해당 URL을 통한 페이지 접근이 불가합니다.
         * - https://docs.popbill.com/message/java/api#GetSentListURL
         */
        try {

            String url = messageService.getSentListURL(testCorpNum, testUserID);

            m.addAttribute("Result", url);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/result";
    }

    @RequestMapping(value = "/autoDenyList.mdo", method = RequestMethod.GET)
    public String getAutoDenyList(Model m) {
        /*
         * 전용 080 번호에 등록된 수신거부 목록을 반환합니다.
         * - https://docs.popbill.com/message/java/api#GetAutoDenyList
         */

        try {
            AutoDeny[] autoDenyList = messageService.getAutoDenyList(testCorpNum);
            m.addAttribute("AutoDenyList", autoDenyList);
        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }
        return "Message/AutoDeny";
    }

    @RequestMapping(value = "/getUnitCost.mdo", method = RequestMethod.GET)
    public String getUnitCost(Model m) {
        /*
         * 문자 전송시 과금되는 포인트 단가를 확인합니다.
         * - https://docs.popbill.com/message/java/api#GetUnitCost
         */

        // 문자 메시지 유형, SMS-단문, LMS-장문, MMS-포토
        MessageType msgType = MessageType.SMS;

        try {

            float unitCost = messageService.getUnitCost(testCorpNum, msgType);

            m.addAttribute("Result", unitCost);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/result";
    }

    @RequestMapping(value = "/getChargeInfo.mdo", method = RequestMethod.GET)
    public String chargeInfo(Model m) {
        /*
         * 팝빌 문자 API 서비스 과금정보를 확인합니다.
         * - https://docs.popbill.com/message/java/api#GetChargeInfo
         */

        // 문자 메시지 유형, SMS-단문, LMS-장문, MMS-포토
        MessageType msgType = MessageType.SMS;

        try {

            ChargeInfo chrgInfo = messageService.getChargeInfo(testCorpNum, msgType);
            m.addAttribute("ChargeInfo", chrgInfo);

        } catch (PopbillException e) {
            m.addAttribute("Exception", e);
            return "Message/exception";
        }

        return "Message/getChargeInfo";
    }

}
