package com.tranquyet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.convert.TopicConvert;
import com.tranquyet.dto.TopicDTO;
import com.tranquyet.entity.TopicEntity;
import com.tranquyet.repository.TopicRepository;
import com.tranquyet.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private TopicConvert topicConvert;

	@Override
	public List<TopicDTO> findAll() {

		List<TopicEntity> listTopic = topicRepository.findAll();
		List<TopicDTO> listDTO = new ArrayList<>();
		listTopic.forEach(p -> {
			listDTO.add(topicConvert.toDTO(p));
		});

		return listDTO;
	}

	@Override
	public TopicDTO getTopicById(long id) {
		TopicEntity temp = topicRepository.findOneById(id);
		return topicConvert.toDTO(temp);
	}

	@Override
	public int getTotalTopic() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TopicDTO save(TopicDTO dto) {
		TopicEntity temp = new TopicEntity();
		if (dto.getId() != null) {
			temp = topicRepository.findOneById(dto.getId());
			temp = topicConvert.toEntity(dto);
		} else {
			temp = topicConvert.toEntity(dto);
		}

		return topicConvert.toDTO(topicRepository.save(temp));
	}

	@Override
	public void remove(Long[] ids) {
		for (long id : ids) {
			topicRepository.deleteById(id);
		}
	}

}
