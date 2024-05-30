package com.r2s.javabackend09.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.javabackend09.model.Category;
import com.r2s.javabackend09.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryservice;
	
	static List<Category> users = new ArrayList<>();

	@GetMapping(path ="/category")
	public List<Category> getAll() {
		return this.categoryservice.getAll();
	}
}
