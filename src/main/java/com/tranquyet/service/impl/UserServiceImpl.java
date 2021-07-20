package com.tranquyet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tranquyet.convert.UserConvert;
import com.tranquyet.dto.UserDTO;
import com.tranquyet.entity.UserEntity;
import com.tranquyet.repository.UserRepository;
import com.tranquyet.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConvert userConvert;

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			userRepository.deleteById(id);
		}
	}

	@Override
	public List<UserDTO> findAll(PageRequest page) {
		List<UserDTO> listDTO = new ArrayList<UserDTO>();
		List<UserEntity> listUser = userRepository.findAll(page).getContent();
		listUser.forEach(p -> {
			listDTO.add(userConvert.toDTO(p));
		});
		return listDTO;
	}

	@Override
	public UserDTO findById(Long id) {
		Optional<UserEntity> user = userRepository.findById(id);
		return userConvert.toDTO(user.get());
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return (int) userRepository.count();
	}

	@Override
	public UserDTO save(UserDTO user) {
		UserDTO temp = null;
		if (user.getId() != null) {
			Optional<UserEntity> userOp = userRepository.findById(user.getId());
			temp = userConvert.toDTO(userOp.get());
			temp = user;
		} else {
			temp = user;
		}
		UserEntity userTemp = userConvert.toEntity(temp);
		return userConvert.toDTO(userRepository.save(userTemp));
	}

}
