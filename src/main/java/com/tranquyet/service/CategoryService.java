package com.tranquyet.service;

import java.util.List;

import com.tranquyet.dto.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> findAll();

	CategoryDTO findById(Long id);

	int getTotal();

	CategoryDTO save(CategoryDTO dto);

	void delete(Long[] ids);

}
