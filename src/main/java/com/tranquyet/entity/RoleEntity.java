package com.tranquyet.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class RoleEntity extends BasedEntity {

	public RoleEntity(String name) {
		super();
		this.name = name;
	}

	@Column(nullable = false, name = "name")
	private String name;

	@ManyToMany(mappedBy = "roleEntity")
	private List<UserEntity> userEntity = new ArrayList<UserEntity>();
}
