package com.r2s.javabackend09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.r2s.javabackend09.model.VariantProduct;
import java.util.List;

public interface VariantProductRepository extends JpaRepository<VariantProduct, Integer> {
    List<VariantProduct> findByProducts_Id(int id_product);

    VariantProduct findById(int id);

}
