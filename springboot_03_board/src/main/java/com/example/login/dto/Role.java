package com.example.login.dto;

public enum Role {
	ROLE_ADMIN("관리자"), ROLE_MANAGER("매니저"), ROLE_MEMBER("일반사용자");

	private String value;

	Role(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
