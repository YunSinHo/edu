package com.docedu.web.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docedu.web.admin.dao.StudentDao;
import com.docedu.web.admin.vo.CarrotVO;
import com.docedu.web.admin.vo.StudentVO;
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	@Override
	public List<StudentVO> studentList() {
		return studentDao.studentList();
	}
	@Override
	public List<CarrotVO> carrotList1(String student_parentnumber) {
		return studentDao.carrotList1(student_parentnumber);
	}
	@Override
	public StudentVO studentList1(String student_parentnumber) {
		// TODO Auto-generated method stub
		return studentDao.studentList1(student_parentnumber);
	}

}
