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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="product")
@NoArgsConstructor
public class ProductEntity extends BasedEntity{
	
	@Column(name="title", columnDefinition="TEXT")
	private String title;
	@Column(name="content", columnDefinition="LONGTEXT")
	private String content;
	@Column(name = "price")
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_category")
	private CategoryEntity category;
	
	@ElementCollection
	
	@MapKeyColumn(name = "listImage", columnDefinition = "LONGTEXT")
	@Column(name = "listImage", columnDefinition = "LONGTEXT")
	@CollectionTable(name="product_image")
	private List<String> listImage;

}
