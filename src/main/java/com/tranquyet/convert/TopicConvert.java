package com.tranquyet.convert;

import org.springframework.stereotype.Component;

import com.tranquyet.dto.TopicDTO;
import com.tranquyet.entity.TopicEntity;

@Component
public class TopicConvert implements BasedConvert<TopicDTO, TopicEntity >{

	@Override
	public TopicDTO toDTO(TopicEntity entity) {
		TopicDTO dto = new TopicDTO();
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public TopicEntity toEntity(TopicDTO dto) {
		TopicEntity entity = new TopicEntity();
		entity.setId(dto.getId());
		entity.setCreatedBy(dto.getCreatedBy());
		entity.setCreatedDate(dto.getCreatedDate());
		entity.setModifiedBy(dto.getModifiedBy());
		entity.setModifiedDate(dto.getModifiedDate());
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public TopicEntity toEntity(TopicDTO t, TopicEntity h) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
