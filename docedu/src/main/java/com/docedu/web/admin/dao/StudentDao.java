package com.docedu.web.admin.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.docedu.web.admin.vo.CarrotVO;
import com.docedu.web.admin.vo.StudentVO;
@Repository
public class StudentDao {
@Autowired
private SqlSessionTemplate sqlSessionTemplate;
	public List<StudentVO> studentList() {
		return sqlSessionTemplate.selectList("student.studentList");
	}
	public List<CarrotVO> carrotList1(String student_parentnumber) {
		return sqlSessionTemplate.selectList("carrot.carrotList1",student_parentnumber);
	}
	public StudentVO studentList1(String student_parentnumber) {
		return sqlSessionTemplate.selectOne("student.studentList1",student_parentnumber);
	}

}
