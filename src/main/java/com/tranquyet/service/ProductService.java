package com.tranquyet.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.tranquyet.dto.ProductDTO;

public interface ProductService {
	
	List<ProductDTO> findAll(PageRequest page);
	
	List<ProductDTO> searchProduct(PageRequest page,String word);
	
	int getTotalItem();
	
	ProductDTO findById(Long id);
	
	ProductDTO save(ProductDTO dto);
	
	void delete(Long[] ids);
	
	

}
