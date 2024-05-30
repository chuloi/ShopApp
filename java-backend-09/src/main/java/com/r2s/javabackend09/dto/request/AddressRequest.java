package com.r2s.javabackend09.dto.request;


import lombok.Data;

@Data
public class AddressRequest {
	private int user_id;
    private String street;
    private String city;

    public AddressRequest() {
    }

    public AddressRequest(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
