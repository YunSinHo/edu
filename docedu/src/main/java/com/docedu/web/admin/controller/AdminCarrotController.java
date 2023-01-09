package com.docedu.web.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.docedu.web.admin.service.StudentService;
import com.docedu.web.admin.vo.AdminVO;
import com.docedu.web.admin.vo.CarrotVO;
import com.docedu.web.admin.vo.StudentVO;
import com.docedu.web.admin.vo.UpdatorVO;
import com.docedu.web.user.service.CarrotService;

@Controller
public class AdminCarrotController {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CarrotService carrotService;
	@RequestMapping(value="/carrotList.mdo" ,method= {RequestMethod.GET ,RequestMethod.POST})
	public ModelAndView carrotList(@RequestParam("student_parentnumber")String student_parentnumber) {
		ModelAndView mav=new ModelAndView();
		List<CarrotVO> carrotVO=new ArrayList();
		carrotVO=studentService.carrotList1(student_parentnumber);
		StudentVO studentVO=new StudentVO();
		studentVO=studentService.studentList1(student_parentnumber);
		mav.addObject("studentList",studentVO);
		mav.addObject("carrotList",carrotVO);
		mav.setViewName("carrotList");
		
		
		return mav;
	}
	@RequestMapping(value="/carrotListOne.mdo" ,method= {RequestMethod.GET ,RequestMethod.POST})
	public ModelAndView carrotListOne(@RequestParam("carrot_seq")int carrot_seq) {
		ModelAndView mav=new ModelAndView();
		CarrotVO carrotVO=new CarrotVO();
		carrotVO= carrotService.carrotread(carrot_seq);
		UpdatorVO updatorVO=new UpdatorVO();
		updatorVO=carrotService.getUpdator(carrot_seq);
		mav.addObject("carrot_view",carrotVO);
		mav.setViewName("carrotListOne");
		mav.addObject("updatorList",updatorVO);
		return mav;
	}
	@RequestMapping(value="/updateCarrot.mdo" ,method= {RequestMethod.GET ,RequestMethod.POST})
	public String updateCarrot(@ModelAttribute CarrotVO carrotVO ,@RequestParam("carrot_number1") String carrot_number1,
			HttpServletRequest request, Model model,
			@RequestParam("updator_name")String updator_name,
			@RequestParam("getting") String getting) {
		if(carrotVO.getCarrot_getting().equals("대분류 우선 선택"))carrotVO.setCarrot_getting(getting);
		HttpSession session=request.getSession ();
		AdminVO adminVO=(AdminVO)session.getAttribute("admin");
		if(adminVO==null)return "adminlogin";
		UpdatorVO updatorVO=new UpdatorVO();
		updator_name=updator_name+adminVO.getAdmin_name();
		sqlSessionTemplate.update("updator.updateUpdator",updatorVO);
		sqlSessionTemplate.update("carrot.updateCarrot",carrotVO);
		model.addAttribute("updatorList",updator_name);
		System.out.println(updator_name);
		return "redirect:/carrotList.mdo?student_parentnumber="+carrot_number1;
	}
}
