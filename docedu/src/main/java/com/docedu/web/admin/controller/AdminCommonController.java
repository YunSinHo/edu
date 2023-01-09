package com.docedu.web.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.docedu.web.admin.service.AcademyService;
import com.docedu.web.admin.service.StudentService;
import com.docedu.web.admin.vo.StudentVO;

@Controller
public class AdminCommonController {
	@Autowired
	private  StudentService studentService;

	@GetMapping("/adminmenu.mdo")
	public String adminmenuGet() {
		return "adminmenu";
	}
	@GetMapping("/accessadmin.mdo")
	public String accessadminGet() {
		return "accessadmin";
	}
	@GetMapping("/assignmentclass.mdo")
	public String assignmentclassGet() {
		return "assignmentclass";
	}
	@GetMapping("/sendmessage.mdo")
	public String sendmessageGet() {
		return "sendmessage";
	}
	@GetMapping("/supplement.mdo")
	public String supplementGet() {
		return "supplement";
	}
	@GetMapping("/tables.mdo")
	public ModelAndView tablesGet() {
		
		ModelAndView mav=new ModelAndView();
		List<StudentVO> studentVO=new ArrayList();
		studentVO=studentService.studentList();
		mav.addObject("studentList",studentVO);
		mav.setViewName("tables");
		return mav;
	}
}