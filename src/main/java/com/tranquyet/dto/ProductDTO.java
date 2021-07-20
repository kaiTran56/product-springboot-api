package com.tranquyet.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO extends BasedDTO<ProductDTO>{
	
	@NotEmpty
	@NotNull
	private String title;
	@NotEmpty
	@NotNull
	private String content;
	private double price;
	@NotEmpty
	@NotNull
	private String category;
	
	private List<String> listImage;

}
