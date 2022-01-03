package com.saeyan.dto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class MovieVO {

	private Integer code;
	private String title;
	private Integer price;
	private String director;
	private String actor;
	private String poster;
	private String synopsis;
}
