package com.docedu.web.admin.service;

import java.util.List;

import com.docedu.web.admin.vo.CarrotVO;
import com.docedu.web.admin.vo.StudentVO;

public interface StudentService {

	List<StudentVO> studentList();

	List<CarrotVO> carrotList1(String student_parentnumber);

	StudentVO studentList1(String student_parentnumber);

}
