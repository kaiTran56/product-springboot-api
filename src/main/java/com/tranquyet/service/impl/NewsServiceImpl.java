package com.tranquyet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tranquyet.convert.NewsConvert;
import com.tranquyet.dto.NewsDTO;
import com.tranquyet.entity.NewsEntity;
import com.tranquyet.entity.TopicEntity;
import com.tranquyet.repository.NewsRepository;
import com.tranquyet.repository.TopicRepository;
import com.tranquyet.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private NewsConvert newsConvert;

	@Override
	public List<NewsDTO> findAll(PageRequest page) {
		List<NewsEntity> listEntity = newsRepository.findAll(page).getContent();
		List<NewsDTO> listDTO = new ArrayList<NewsDTO>();
		listEntity.stream().forEach(p -> {
			listDTO.add(newsConvert.toDTO(p));
		});
		return listDTO;
	}

	@Override
	public int getTotalNews() {
		// TODO Auto-generated method stub
		return (int) newsRepository.count();
	}

	@Override
	public NewsDTO getNewsById(Long id) {
		NewsEntity newsEntity = newsRepository.findOneById(id);
		if (newsEntity != null)
			return newsConvert.toDTO(newsEntity);
		return null;
	}

	@Override
	public NewsDTO save(NewsDTO dto) {
		TopicEntity topicEntity = topicRepository.findOneByName(dto.getTopic());
		NewsEntity newsTemp = null;
		if (dto.getId() != null) {
			newsTemp = newsRepository.findOneById(dto.getId());
			newsTemp = newsConvert.toEntity(dto);
			newsTemp.setTopic(topicEntity);
		} else {
			newsTemp = newsConvert.toEntity(dto);
			newsTemp.setTopic(topicEntity);
		}
		return newsConvert.toDTO(newsRepository.save(newsTemp));
	}

	@Override
	public void remove(long[] ids) {
		for(long id : ids) {
			newsRepository.deleteById(id);
		}
	}

}
