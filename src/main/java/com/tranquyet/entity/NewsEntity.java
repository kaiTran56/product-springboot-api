package com.tranquyet.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="news")
@NoArgsConstructor
public class NewsEntity extends BasedEntity{
	
	@Column(name = "title")
	private String title;
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	@Column(name = "content", columnDefinition = "LONGTEXT")
	private String content;
	@Column(name = "thumbnail")
	private String thumbnail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_news")
	private TopicEntity topic;
	
	@ElementCollection
	@MapKeyColumn(name = "tagList")
	@Column(name = "tagList")
	@CollectionTable(name="news_tag")
	private List<String> tagList;

}
