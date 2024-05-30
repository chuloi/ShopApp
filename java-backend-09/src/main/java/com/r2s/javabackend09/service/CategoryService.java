package com.r2s.javabackend09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.javabackend09.model.Category;
import com.r2s.javabackend09.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAll() {
		return this.categoryRepository.findAll();
	}
	
}
