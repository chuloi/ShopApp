package com.r2s.javabackend09.repository;

import com.r2s.javabackend09.model.Products;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    List<Products> findById(int Id);
	Products findByIdAndCategoryId(int id, int categoryId);
	List<Products> findByCategory_Id(int categoryId);
	boolean existsBynameproduct(String productName);
}