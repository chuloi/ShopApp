package com.r2s.javabackend09.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductResponeDTO {
	private String nameproduct;
	private long price_product;
	public String getNameproduct() {
		return nameproduct;
	}
	public void setNameproduct(String nameproduct) {
		this.nameproduct = nameproduct;
	}
	public long getPrice_product() {
		return price_product;
	}
	public void setPrice_product(long price_product) {
		this.price_product = price_product;
	}
	

}
