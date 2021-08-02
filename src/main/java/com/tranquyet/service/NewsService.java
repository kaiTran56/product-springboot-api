package com.tranquyet.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.tranquyet.dto.NewsDTO;

public interface NewsService {
	List<NewsDTO> findAll(PageRequest page);
	
	int getTotalNews();
	
	NewsDTO getNewsById(Long id);
	
	NewsDTO save(NewsDTO dto);
	
	void remove(long[] ids);
}
