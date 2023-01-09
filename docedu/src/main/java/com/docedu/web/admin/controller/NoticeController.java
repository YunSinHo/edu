package com.docedu.web.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.docedu.web.admin.service.NoticeService;
import com.docedu.web.admin.vo.NoticeVO;
import com.docedu.web.user.vo.Criteria;

@Controller
public class NoticeController {

	@Autowired
	private final NoticeService noticeService;

	@Inject
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	@RequestMapping(value = "/noticelist.mdo")
	public ModelAndView noticelistGet(Model model,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("admin")== null) {
			mav.setViewName("/adminlogin");
		}else {
			mav.setViewName("/noticelist");
		}
		Criteria cr = null;
		List<NoticeVO> showList = new ArrayList<NoticeVO>();
		showList = noticeService.noticelist(cr);
		mav.addObject("NoticeList", showList);
		return mav;
	}
	@GetMapping("/noticeinsert.mdo")
	public String noticeinsertGet() {
		return "noticeinsert";
	}
	@RequestMapping(value = "/insertnotice.mdo", method = RequestMethod.POST)
	public String notice_insertGet(@ModelAttribute NoticeVO noticeVO) {
		noticeService.noticeInsert(noticeVO);
//		System.out.println(ad_encyVO.toString());
		return "redirect:/noticelist.mdo";
	}
	@RequestMapping("/noticeview.mdo")
	public ModelAndView getnoticeGet(@RequestParam int notice_seq,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("admin")== null) {
			mav.setViewName("/adminlogin");
		}else {
			mav.setViewName("/noticeview");
		}
		NoticeVO noticeVO;
		noticeVO = noticeService.noticeread(notice_seq);
		mav.addObject("notice_view", noticeVO);
		return mav;
	}
}