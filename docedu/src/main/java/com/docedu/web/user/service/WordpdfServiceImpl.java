package com.docedu.web.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docedu.web.user.dao.WordpdfDAO;
import com.docedu.web.user.vo.WordcollectionVO;
import com.docedu.web.user.vo.WordgugudanVO;

@Service
public class WordpdfServiceImpl implements WordpdfService{
	@Autowired
	private WordpdfDAO wordpdfDAO;
	
	@Override
	public List<WordgugudanVO> wordpdflist_page() {
		return wordpdfDAO.wordpdflist_page();
	}

	@Override
	public List<WordcollectionVO> wordpdfread(int gugudan_level) {
		return wordpdfDAO.wordpdfread(gugudan_level);
	}

	@Override
	public WordgugudanVO getGugudan(String gugudan_name) {
		return wordpdfDAO.getGugudan(gugudan_name);
	}

	@Override
	public WordcollectionVO getMaxSeq(int gugudan_level) {
		return wordpdfDAO.getMaxSeq(gugudan_level);
	}

	@Override
	public List<WordcollectionVO> wordpdfread(int[] index) {
		
		return wordpdfDAO.wordpdfread( index);
	}

	@Override
	public WordcollectionVO getMaxSeq1(int gugudan_level) {
		// TODO Auto-generated method stub
		return wordpdfDAO.getMaxSeq1(gugudan_level);
	}

	
}
