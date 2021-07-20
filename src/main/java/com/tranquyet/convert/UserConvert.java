package com.tranquyet.convert;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.tranquyet.dto.UserDTO;
import com.tranquyet.entity.RoleEntity;
import com.tranquyet.entity.UserEntity;

@Component
public class UserConvert {

	public UserDTO toDTO(UserEntity user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setCreatedBy(user.getCreatedBy());
		dto.setCreatedDate(user.getCreatedDate());
		dto.setModifiedBy(user.getModifiedBy());
		dto.setModifiedDate(user.getModifiedDate());
		dto.setName(user.getName());
		dto.setAge(user.getAge());
		dto.setStatus(user.getStatus());
		dto.setRole(user.getRoleEntity().get(0).getName());
		return dto;
	}

	public UserEntity toEntity(UserDTO dto) {
		UserEntity user = new UserEntity();
		user.setId(dto.getId());
		user.setCreatedBy(dto.getCreatedBy());
		user.setCreatedDate(dto.getCreatedDate());
		user.setModifiedBy(dto.getModifiedBy());
		user.setModifiedDate(dto.getModifiedDate());
		user.setName(dto.getName());
		user.setAge(dto.getAge());
		user.setStatus(dto.getStatus());
		user.setRoleEntity(Arrays.asList(new RoleEntity(dto.getRole())));
		return user;
	}

	public UserEntity toEntity(UserDTO dto, UserEntity user) {
		user.setId(dto.getId());
		user.setCreatedBy(dto.getCreatedBy());
		user.setCreatedDate(dto.getCreatedDate());
		user.setModifiedBy(dto.getModifiedBy());
		user.setModifiedDate(dto.getModifiedDate());
		user.setName(dto.getName());
		user.setAge(dto.getAge());
		user.setStatus(dto.getStatus());
		user.setRoleEntity(Arrays.asList(new RoleEntity(dto.getRole())));
		return user;
	}

}
