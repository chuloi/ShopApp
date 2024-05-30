package com.r2s.javabackend09.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	private String nameCategory;
	
	public Category(int id, String nameCategory) {
		this.id = id;
		this.nameCategory = nameCategory;
	}
	
	public Category() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return nameCategory;
	}

	public void setName(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	
	
	
}
