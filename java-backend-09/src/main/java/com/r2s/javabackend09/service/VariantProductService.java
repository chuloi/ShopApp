package com.r2s.javabackend09.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.r2s.javabackend09.model.VariantProduct;
import com.r2s.javabackend09.repository.VariantProductRepository;

@Service
public class VariantProductService {
    @Autowired
    private VariantProductRepository variantProductRepository;

    public List<VariantProduct> findAll(int id_product) {
        return variantProductRepository.findByProducts_Id(id_product);	
    }
}



