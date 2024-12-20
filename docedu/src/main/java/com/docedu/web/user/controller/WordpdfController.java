package com.docedu.web.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.docedu.web.user.service.WordpdfService;
import com.docedu.web.user.vo.WordcollectionVO;
import com.docedu.web.user.vo.WordgugudanVO;

@Controller
public class WordpdfController {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	private final WordpdfService wordpdfService;
	
	@Inject
	public WordpdfController(WordpdfService wordpdfService) {
		this.wordpdfService = wordpdfService;
	}
	@GetMapping("/wordinsert.do")
	public String wordinsertGet() {
		return "wordinsert";
	}
	@GetMapping("/wordpdflist.do")
	public ModelAndView wordpdflistGet(Model model) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<WordgugudanVO> showList = new ArrayList<WordgugudanVO>();
		showList = wordpdfService.wordpdflist_page();
		mav.addObject("WordpdfList", showList);
		mav.setViewName("wordpdflist");
		return mav;
	}
	@GetMapping("/wordpdf.do")
	public ModelAndView wordpdfGet(@RequestParam("gugudan_name") String gugudan_name) {
		ModelAndView mav = new ModelAndView();
		WordgugudanVO wordgugudanVO=new WordgugudanVO();
		wordgugudanVO=wordpdfService.getGugudan(gugudan_name);
		List<WordcollectionVO> wordVO= new ArrayList();
		wordVO = wordpdfService.wordpdfread(wordgugudanVO.getGugudan_level());
		mav.addObject("wpdf_view", wordVO);
		return mav;
	}
	@GetMapping("/wordpdftest.do")
	public ModelAndView wordpdftestGet(@RequestParam("gugudan_name") String gugudan_name) {
		ModelAndView mav = new ModelAndView();
		WordgugudanVO wordgugudanVO=new WordgugudanVO();
		wordgugudanVO=wordpdfService.getGugudan(gugudan_name);
		List<WordcollectionVO> wordVO= new ArrayList();
		
		
		//랜덤반복을 위해 seq 끝값 찾기
		WordcollectionVO wordcollectionVO=new WordcollectionVO();
		WordcollectionVO wordcollectionVO1=new WordcollectionVO();
		
		wordcollectionVO=wordpdfService.getMaxSeq(wordgugudanVO.getGugudan_level());
		
		int min1;	
		min1=wordgugudanVO.getGugudan_level()-1;
		if(wordcollectionVO.getWord_difficulty()==1)min1=1;
		wordcollectionVO1=wordpdfService.getMaxSeq1(min1);
		int i1=wordcollectionVO.getWord_seq();
		int i2=wordcollectionVO1.getWord_seq();
		if(wordcollectionVO.getWord_difficulty()==1)i2=0;
		int index[]=new int[60];
		
		for(int i=0;i<=59;i++) {
			index[i]=(int)((Math.random()*(i1-i2)+i2+1));
			for(int j=0;j<i;j++) {
				if(index[i]==index[j])i--;
			}
		}
		
		String index1[]=new String[index.length];
		for(int i=0;i<=59;i++) {
			index1[i]=String.valueOf(index[i]);
		}
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < index1.length; i++) {
		  stringBuilder.append(index1[i]+" ");
		}
		String strArrayToString = stringBuilder.toString();
		strArrayToString=strArrayToString.replaceAll(" ","|");
		
		wordVO = wordpdfService.wordpdfread(index);
		mav.addObject("wpdf_view", wordVO);
		System.out.println(index+" "+strArrayToString);
		return mav;
	}
	@ResponseBody
    @RequestMapping(value = "/insertdata.do", method = RequestMethod.POST)
	public List<Map<String, Object>> test(@RequestBody List<Map<String, Object>> param) {
		sqlSessionTemplate.insert("wordpdf.wordpdfinsert",param);
		return param;
	}
}
