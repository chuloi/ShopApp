package com.r2s.javabackend09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.r2s.javabackend09.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
