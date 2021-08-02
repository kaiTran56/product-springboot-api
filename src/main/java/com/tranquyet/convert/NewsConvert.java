package com.tranquyet.convert;

import org.springframework.stereotype.Component;

import com.tranquyet.dto.NewsDTO;
import com.tranquyet.entity.NewsEntity;
import com.tranquyet.entity.TopicEntity;

@Component
public class NewsConvert implements BasedConvert<NewsDTO, NewsEntity>{

	@Override
	public NewsDTO toDTO(NewsEntity entity) {
		NewsDTO dto = new NewsDTO();
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setDescription(entity.getDescription());
		dto.setTopic(entity.getTopic().getName());
		dto.setThumbnail(entity.getThumbnail());
		dto.setContent(entity.getContent());
		dto.setTagList(entity.getTagList());
		return dto;
	}

	@Override
	public NewsEntity toEntity(NewsDTO dto) {
		NewsEntity entity = new NewsEntity();
		entity.setId(dto.getId());
		entity.setCreatedBy(dto.getCreatedBy());
		entity.setCreatedDate(dto.getCreatedDate());
		entity.setModifiedBy(dto.getModifiedBy());
		entity.setModifiedDate(dto.getModifiedDate());
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setDescription(dto.getDescription());
		entity.setTopic(new TopicEntity(dto.getTopic()));
		entity.setThumbnail(dto.getThumbnail());
		entity.setContent(dto.getContent());
		entity.setTagList(dto.getTagList());
		return entity;
	}

	@Override
	public NewsEntity toEntity(NewsDTO t, NewsEntity h) {
		// TODO Auto-generated method stub
		return null;
	}

}
