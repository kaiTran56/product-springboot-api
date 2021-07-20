package com.tranquyet.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDTO extends BasedDTO<CategoryDTO>{
	private String name;
}
