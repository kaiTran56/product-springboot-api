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

import com.tranquyet.dto.UserDTO;
import com.tranquyet.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Validated
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@GetMapping(value = "/listUser")
	public ResponseEntity<List<UserDTO>> getShowUser(
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "limit", required = false) Optional<Integer> limit,
			@RequestParam(name = "sortBy", required = false) Optional<String> sortBy) {

		UserDTO dto = new UserDTO();
		dto.setLimit(limit.orElse(2));
		dto.setPage(page.orElse(0));
		dto.setCurrentPage(page.orElse(0));

		Sort sort = Sort.by(Sort.Order.asc(sortBy.orElse("")));

		PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, limit.orElse(2), sort);

		dto.setListResult(userService.findAll(pageRequest));
		dto.setTotalItem(userService.getTotalItem());
		int total = (int) Math.ceil(dto.getTotalItem() / dto.getLimit());
		dto.setTotalPage(total);
		logger.info("----------------Show Information------------");
		logger.info(dto.getListResult().toString());
		return new ResponseEntity<>(dto.getListResult(), HttpStatus.OK);
	}

	@GetMapping(value = "/user/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") @Min(0) Long id) {
		UserDTO user = userService.findById(id);

		logger.info("----------------Show USer------------");
		logger.info(user.toString());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping(value = "/add")
	public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO user) {

		logger.info(user.toString());

		UserDTO userTemp = userService.save(user);
		Optional<HttpStatus> status = Optional.of(HttpStatus.CREATED);
		return new ResponseEntity<>(userTemp, status.orElse(HttpStatus.NO_CONTENT));
	}

	@PutMapping(value = "/update")
	public ResponseEntity<UserDTO> updateEntity(@Valid @RequestBody UserDTO user) {

		UserDTO userTemp = userService.save(user);
		logger.info(user.toString());
		return new ResponseEntity<UserDTO>(userTemp, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id")  long[] id) {
		logger.info(id + "");
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
