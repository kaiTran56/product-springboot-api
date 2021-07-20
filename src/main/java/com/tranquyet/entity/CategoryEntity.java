package com.tranquyet.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category")
@NoArgsConstructor
public class CategoryEntity extends BasedEntity {

	public CategoryEntity(String name) {
		super();
		this.name = name;
	}

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "category")
	private List<ProductEntity> listProduct = new ArrayList<ProductEntity>();

}
