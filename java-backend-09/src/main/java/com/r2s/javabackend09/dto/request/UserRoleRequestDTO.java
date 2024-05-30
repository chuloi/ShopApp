package com.r2s.javabackend09.dto.request;

import lombok.Data;

@Data
public class UserRoleRequestDTO {
	private Integer userId;
	private Integer roleId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
