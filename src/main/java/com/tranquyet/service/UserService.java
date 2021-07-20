package com.tranquyet.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.tranquyet.dto.UserDTO;

public interface UserService {

	List<UserDTO> findAll(PageRequest page);

	UserDTO findById(Long id);

	int getTotalItem();

	UserDTO save(UserDTO user);

	void delete(long[] ids);

}
