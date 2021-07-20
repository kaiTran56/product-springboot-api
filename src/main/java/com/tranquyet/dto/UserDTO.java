package com.tranquyet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO extends BasedDTO<UserDTO> {
	private String name;

	private int age;

	private String status;

	private String role;

}
