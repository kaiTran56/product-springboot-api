package com.tranquyet.controller.admin.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.dto.ProductDTO;
import com.tranquyet.service.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController(value = "product")
@RequestMapping("/api/product")
@Validated
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<ProductDTO> getAll(@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "limit", required = false) Optional<Integer> limit,
			@RequestParam(name = "sort", required = false) Optional<String> sort) {

		ProductDTO dto = new ProductDTO();

		dto.setPage(page.orElse(0));
		dto.setLimit(limit.orElse(2));
		dto.setSortBy(sort.orElse(""));

		Sort sortObj = Sort.by(sort.orElse("id")).descending();

		PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, limit.orElse(2), sortObj);

		dto.setTotalItem(productService.getTotalItem());

		int total = ((int) Math.ceil(dto.getTotalItem() / dto.getLimit())) + 1;

		dto.setTotalPage(total);

		dto.setCurrentPage(page.orElse(0));

		dto.setListResult(productService.findAll(pageRequest));

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable(name = "id", required = true) @Min(0) Long id) {
		ProductDTO dto = productService.findById(id);
		log.info("----> " + dto.toString());
		return new ResponseEntity<>(dto, HttpStatus.OK);

	}

	@GetMapping("/search")
	public ResponseEntity<ProductDTO> searchProduct(@RequestParam(name = "word", required = false) String word,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "limit", required = false) Optional<Integer> limit,
			@RequestParam(name = "sort", required = false) Optional<String> sort) {

		ProductDTO dto = new ProductDTO();
		dto.setPage(page.orElse(0));
		dto.setLimit(limit.orElse(2));
		dto.setSortBy(sort.orElse(""));
		Sort sortObj = Sort.by(sort.orElse("id")).descending();

		PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, limit.orElse(5), sortObj);

		List<ProductDTO> listTemp = productService.searchProduct(pageRequest, word);
		dto.setListResult(listTemp);

		dto.setTotalItem(productService.getTotalItem());

		int total = ((int) Math.ceil(dto.getTotalItem() / dto.getLimit())) + 1;

		dto.setTotalPage(total);

		dto.setCurrentPage(page.orElse(0));

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO dto) {
		ProductDTO temp = dto;
		log.info("save Product: " + dto.toString());
		if (!dto.getTitle().isEmpty()) {
			productService.save(dto);
		}

		return new ResponseEntity<>(temp, HttpStatus.CREATED);
	}

	@PutMapping("/edit")
	public ResponseEntity<ProductDTO> editProduct(@RequestBody @Valid ProductDTO dto) {
		ProductDTO temp = dto;
		log.info("Edit Product: "+dto.toString());
		productService.save(temp);
		return new ResponseEntity<>(temp, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		Long[] ids = { id };
		productService.delete(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
