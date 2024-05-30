package com.r2s.javabackend09.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.r2s.javabackend09.model.Products;
import com.r2s.javabackend09.model.VariantProduct;
import com.r2s.javabackend09.repository.VariantProductRepository;

@RestController
public class VariantProductController {

    @Autowired
    private VariantProductRepository variantProductRepository;

    @GetMapping("/variantproducts/{id_product}")
    public List<VariantProduct> getVariantProducts(@PathVariable int id_product) {
        return variantProductRepository.findByProducts_Id(id_product);
    }

    @PostMapping("/variantproducts/addproduct/{id_product}")
    public ResponseEntity<VariantProduct> addVariantProduct(@PathVariable int id_product, @RequestBody VariantProduct variantProduct) {
        if (variantProduct.getProducts() == null) {
            Products product = new Products();
            variantProduct.setProducts(product);
        }
        variantProduct.getProducts().setId(id_product);
        VariantProduct savedVariantProduct = variantProductRepository.save(variantProduct);
        if (savedVariantProduct != null) {
            return new ResponseEntity<>(savedVariantProduct, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/variantproducts/update/{id}")
    public ResponseEntity<VariantProduct> updateVariantProduct(@PathVariable int id, @RequestBody VariantProduct updatedVariantProduct) {
        VariantProduct existingVariantProduct = variantProductRepository.findById(id);
        if (existingVariantProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedVariantProduct.setId(id); 
        VariantProduct updatedProduct = variantProductRepository.save(updatedVariantProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
