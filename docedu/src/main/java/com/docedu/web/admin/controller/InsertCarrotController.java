package com.docedu.web.admin.controller;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docedu.web.admin.service.StudentService;
import com.docedu.web.admin.vo.CarrotVO;
import com.docedu.web.admin.vo.StudentVO;

@Controller
public class InsertCarrotController {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	private StudentService studentService;
	@GetMapping("/sendcarrot.mdo")
	public String sendcarrotGet(@RequestParam("student_parentnumber") String student_parentnumber,
			Model model) {
		 StudentVO studentVO=new StudentVO();
		studentVO=studentService.studentList1(student_parentnumber);
		model.addAttribute("studentList",studentVO);
		return "sendcarrot";
	}
	@ResponseBody
    @RequestMapping(value = "/insertcarrot.mdo", method = {RequestMethod.POST,RequestMethod.GET})
	public String insertcarrotGet(@ModelAttribute CarrotVO carrotVO) {
		sqlSessionTemplate.insert("carrot.carrotinsert",carrotVO);
		return "redirect:/carrotList.mdo?student_parentnumber="+carrotVO.getCarrot_number();
	}
	
}
