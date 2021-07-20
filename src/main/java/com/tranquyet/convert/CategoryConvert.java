package com.tranquyet.convert;

import org.springframework.stereotype.Component;

import com.tranquyet.dto.CategoryDTO;
import com.tranquyet.entity.CategoryEntity;

@Component
public class CategoryConvert implements BasedConvert<CategoryDTO, CategoryEntity> {

	@Override
	public CategoryDTO toDTO(CategoryEntity entity) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity entity = new CategoryEntity();
		entity.setId(dto.getId());
		entity.setCreatedBy(dto.getCreatedBy());
		entity.setCreatedDate(dto.getCreatedDate());
		entity.setModifiedBy(dto.getModifiedBy());
		entity.setModifiedDate(dto.getModifiedDate());
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public CategoryEntity toEntity(CategoryDTO t, CategoryEntity h) {
		// TODO Auto-generated method stub
		return null;
	}

}
