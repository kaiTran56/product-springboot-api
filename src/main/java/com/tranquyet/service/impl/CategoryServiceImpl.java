package com.tranquyet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.convert.CategoryConvert;
import com.tranquyet.dto.CategoryDTO;
import com.tranquyet.entity.CategoryEntity;
import com.tranquyet.repository.CategoryRepository;
import com.tranquyet.repository.ProductRepository;
import com.tranquyet.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private CategoryConvert categoryConvert;

	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryDTO> listDTO = new ArrayList<CategoryDTO>();
		List<CategoryEntity> listEntity = categoryRepo.findAll();
		listEntity.forEach(p -> {
			listDTO.add(categoryConvert.toDTO(p));
		});
		return listDTO;
	}

	@Override
	public CategoryDTO findById(Long id) {

		return categoryConvert.toDTO(categoryRepo.findOneById(id));
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return (int) categoryRepo.count();
	}

	@Override
	public CategoryDTO save(CategoryDTO dto) {
		CategoryEntity temp = new CategoryEntity();
		if (dto.getId() != null) {
			CategoryEntity old = categoryRepo.findOneById(dto.getId());
			old = categoryConvert.toEntity(dto);
			temp = old;
		} else {
			temp = categoryConvert.toEntity(dto);
		}
		return categoryConvert.toDTO(categoryRepo.save(temp));
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			if (id != 6 && id == productRepo.getIdCategory(id)) {
				productRepo.editCategoryProduct(id);
			}
			categoryRepo.deleteById(id);
		}

	}

}
