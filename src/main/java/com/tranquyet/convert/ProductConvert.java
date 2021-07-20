package com.tranquyet.convert;

import org.springframework.stereotype.Component;

import com.tranquyet.dto.ProductDTO;
import com.tranquyet.entity.CategoryEntity;
import com.tranquyet.entity.ProductEntity;

@Component
public class ProductConvert implements BasedConvert<ProductDTO, ProductEntity> {

	@Override
	public ProductDTO toDTO(ProductEntity entity) {
		ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setPrice(entity.getPrice());
		dto.setCategory(entity.getCategory().getName());
		dto.setListImage(entity.getListImage());
		
		return dto;
	}

	@Override
	public ProductEntity toEntity(ProductDTO dto) {
		ProductEntity entity = new ProductEntity();
		entity.setId(dto.getId());
		entity.setCreatedBy(dto.getCreatedBy());
		entity.setCreatedDate(dto.getCreatedDate());
		entity.setModifiedBy(dto.getModifiedBy());
		entity.setModifiedDate(dto.getModifiedDate());
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setPrice(dto.getPrice());
		entity.setCategory(new CategoryEntity(dto.getCategory()));
		entity.setListImage(dto.getListImage());
		return entity;
	}

	@Override
	public ProductEntity toEntity(ProductDTO dto, ProductEntity entity) {
		entity.setId(dto.getId());
		entity.setCreatedBy(dto.getCreatedBy());
		entity.setCreatedDate(dto.getCreatedDate());
		entity.setModifiedBy(dto.getModifiedBy());
		entity.setModifiedDate(dto.getModifiedDate());
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setPrice(dto.getPrice());
		entity.setCategory(new CategoryEntity(dto.getCategory()));
		entity.setListImage(dto.getListImage());
		return entity;
	}

}
