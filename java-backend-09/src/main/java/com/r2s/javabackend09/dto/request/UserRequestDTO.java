package com.r2s.javabackend09.dto.request;

import com.r2s.javabackend09.model.User;

import lombok.Data;

@Data
public class UserRequestDTO {
	private String name;
	private String userName;
	private String passWord;

	public User toUser() {
		User user = new User();
		user.setName(this.name);
		user.setUserName(this.userName);

		return user;
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

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
