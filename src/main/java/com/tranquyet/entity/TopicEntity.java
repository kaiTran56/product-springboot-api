package com.tranquyet.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "topic")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity extends BasedEntity {

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "topic")
	private List<NewsEntity> listNews = new ArrayList<>();

	public TopicEntity(String name) {
		super();
		this.name = name;
	}

}
