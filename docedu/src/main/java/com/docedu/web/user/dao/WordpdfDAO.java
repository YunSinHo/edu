package com.docedu.web.user.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.docedu.web.user.vo.Criteria;
import com.docedu.web.user.vo.WordcollectionVO;
import com.docedu.web.user.vo.WordgugudanVO;
import com.docedu.web.user.vo.WordpdfVO;

@Repository
public class WordpdfDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<WordgugudanVO> wordpdflist_page() {
		return sqlSessionTemplate.selectList("wordpdf.wordpdfviewList");
	}
	
	public List<WordcollectionVO> wordpdfread(int[] index) {
		return sqlSessionTemplate.selectList("wordpdf.wordpdfviewrandom", index);
	}

	public WordgugudanVO getGugudan(String gugudan_name) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("wordpdf.getGugudan",gugudan_name);
	}

	public WordcollectionVO getMaxSeq(int gugudan_level) {
		return sqlSessionTemplate.selectOne("wordpdf.getMaxSeq",gugudan_level);
	}

	public List<WordcollectionVO> wordpdfread(int gugudan_level) {
		return sqlSessionTemplate.selectList("wordpdf.wordpdfview", gugudan_level);
	}

	public WordcollectionVO getMaxSeq1(int gugudan_level) {
		return sqlSessionTemplate.selectOne("wordpdf.getMaxSeq1",gugudan_level);
	}

}
