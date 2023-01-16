package com.docedu.web.user.service;

import java.util.List;

import com.docedu.web.user.vo.WordcollectionVO;
import com.docedu.web.user.vo.WordgugudanVO;

public interface WordpdfService {
	public List<WordgugudanVO> wordpdflist_page();
	public List<WordcollectionVO> wordpdfread(int[] index);
	public WordgugudanVO getGugudan(String gugudan_name);
	public WordcollectionVO getMaxSeq(int gugudan_level);
	public List<WordcollectionVO> wordpdfread(int gugudan_level);
	public WordcollectionVO getMaxSeq1(int gugudan_level);
}
