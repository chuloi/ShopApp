package com.r2s.javabackend09.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VariantProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name_Variant;
    private String desc_Variant;
    private int memory;
    private int price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

	public VariantProduct(int id, String name_Variant, String desc_Variant, int memory, int price,
			Products products) {
		this.id = id;
		this.name_Variant = name_Variant;
		this.desc_Variant = desc_Variant;
		this.memory = memory;
		this.price = price;
		this.products = products;
	}

	public VariantProduct() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName_Variant() {
		return name_Variant;
	}

	public void setName_Variant(String name_Variant) {
		this.name_Variant = name_Variant;
	}

	public String getDesc_Variant() {
		return desc_Variant;
	}

	public void setDesc_Variant(String desc_Variant) {
		this.desc_Variant = desc_Variant;
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}
	
	
    
    
    
    

 
}
