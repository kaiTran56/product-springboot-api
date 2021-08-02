package com.tranquyet.service;

import java.util.List;

import com.tranquyet.dto.TopicDTO;

public interface TopicService {
	List<TopicDTO> findAll();

	TopicDTO getTopicById(long id);

	int getTotalTopic();

	TopicDTO save(TopicDTO dto);

	void remove(Long[] ids);
}
