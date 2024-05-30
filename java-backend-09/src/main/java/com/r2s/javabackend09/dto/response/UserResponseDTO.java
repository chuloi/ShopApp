package com.r2s.javabackend09.dto.response;

import com.r2s.javabackend09.model.User;

import lombok.Data;

@Data
public class UserResponseDTO {
	private Integer id;
	private String name;
	private String userName;


	public UserResponseDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.userName = user.getUserName();
		}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
