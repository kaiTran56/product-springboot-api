package com.tranquyet.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO extends BasedDTO<NewsDTO>{
	private String title;
	
	private String description;
	
	private String thumbnail;
	
	private String content;
	
	private String topic;
	
	private List<String> tagList;
}
