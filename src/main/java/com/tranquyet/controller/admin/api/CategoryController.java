package com.tranquyet.controller.admin.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.dto.CategoryDTO;
import com.tranquyet.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController(value = "category")
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@GetMapping("/list")
	public ResponseEntity<CategoryDTO> getAll() {
		CategoryDTO cate = new CategoryDTO();
		List<CategoryDTO> listDTO = categoryService.findAll();
		cate.setListResult(listDTO);
		log.info("-->Cate: " + cate.getListResult());
		return new ResponseEntity<>(cate, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getById(@PathVariable(name = "id") Long id) {
		CategoryDTO category = categoryService.findById(id);

		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<CategoryDTO> addProduct(@RequestBody CategoryDTO dto) {
		CategoryDTO temp = dto;
		categoryService.save(dto);
		return new ResponseEntity<>(temp, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") Long id) {
		Long[] ids = { id };
		log.info("===>"+id);
		categoryService.delete(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/edit")
	public ResponseEntity<CategoryDTO> editProduct(@RequestBody CategoryDTO dto) {
		CategoryDTO temp = dto;
		log.info("--> Edit: "+temp.getId());
		categoryService.save(temp);
		return new ResponseEntity<CategoryDTO>(temp, HttpStatus.ACCEPTED);
	}

}
