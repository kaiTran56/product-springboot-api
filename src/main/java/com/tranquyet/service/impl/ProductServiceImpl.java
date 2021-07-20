package com.tranquyet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tranquyet.convert.ProductConvert;
import com.tranquyet.dto.ProductDTO;
import com.tranquyet.entity.CategoryEntity;
import com.tranquyet.entity.ProductEntity;
import com.tranquyet.repository.CategoryRepository;
import com.tranquyet.repository.ProductRepository;
import com.tranquyet.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ProductConvert productConvert;

	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public List<ProductDTO> findAll(PageRequest page) {
		List<ProductEntity> listEntity = productRepo.findAll(page).getContent();
		List<ProductDTO> listDTO = new ArrayList<ProductDTO>();
		listEntity.forEach(p -> {
			ProductDTO dto = productConvert.toDTO(p);
			listDTO.add(dto);
		});
		return listDTO;
	}

	@Override
	public int getTotalItem() {

		return (int) productRepo.count();
	}

	@Override
	public ProductDTO findById(Long id) {
		ProductEntity productEntity = productRepo.findOneById(id);
		ProductDTO dto = productConvert.toDTO(productEntity);
		return dto;
	}

	@Override
	public ProductDTO save(ProductDTO dto) {
		CategoryEntity cate = categoryRepo.findOneByName(dto.getCategory());
		ProductEntity temp = new ProductEntity();
		if (dto.getId() != null) {
			ProductEntity old = productRepo.findOneById(dto.getId());
			old = productConvert.toEntity(dto);
			old.setCategory(cate);
			temp = old;
		} else {
			temp = productConvert.toEntity(dto);
			temp.setCategory(cate);

		}
		return productConvert.toDTO(productRepo.save(temp));
	}

	@Override
	public void delete(Long[] ids) {
		for (long id : ids) {
			productRepo.deleteById(id);
		}
	}

	@Override
	public List<ProductDTO> searchProduct(PageRequest page, String word) {

		List<ProductDTO> listDto = new ArrayList<ProductDTO>();
		Page<ProductEntity> temp = (productRepo.searchProduct(page, word));

		temp.forEach(p -> {
			listDto.add(productConvert.toDTO(p));
		});

		return listDto;
	}

}
