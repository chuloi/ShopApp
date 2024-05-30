package com.r2s.javabackend09.controller;
import com.r2s.javabackend09.exception.ProductAlreadyExistsException;
import com.r2s.javabackend09.exception.ProductNotFoundException;
import com.r2s.javabackend09.model.Products;
import com.r2s.javabackend09.service.ProductsService;
import com.r2s.javabackend09.utils.ResponseCode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {
    
    @Autowired
    private ProductsService productsService;
    @GetMapping("/products/{categoryId}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable int categoryId) {
        try {
            List<Products> listProducts = productsService.findByCategory_Id(categoryId);

            if (listProducts != null && !listProducts.isEmpty()) {
                return BaseResponseController.success(listProducts);
            } else {
                throw new ProductNotFoundException();
            }
            
        } catch (ProductNotFoundException e) {
            return BaseResponseController.fail(ResponseCode.PRODUCT_NOT_FOUND.getCode(),
                    ResponseCode.PRODUCT_NOT_FOUND.getMessage());
        }
    }

    @GetMapping("/products/productId/{Id}")
    public ResponseEntity<?> getproProducts(@PathVariable int Id) {
    	try {
            List<Products> listProducts = productsService.findById(Id);;

            if (listProducts != null && !listProducts.isEmpty()) {
                return BaseResponseController.success(listProducts);
            } else {
                throw new ProductNotFoundException();
            }
            
        } catch (ProductNotFoundException e) {
            return BaseResponseController.fail(ResponseCode.PRODUCT_NOT_FOUND.getCode(),
                    ResponseCode.PRODUCT_NOT_FOUND.getMessage());
        }
    }
    
   
    
    @PostMapping("/admin/products/{categoryId}")
    public ResponseEntity<?> addProduct(@PathVariable int categoryId, @RequestBody Products product) {
        try {
            boolean exists = productsService.existsByName(product.getName_product());
            
            if (exists) {
                return BaseResponseController.fail(ResponseCode.PRODUCT_ALREADY_EXISTS.getCode(),
                		ResponseCode.PRODUCT_ALREADY_EXISTS.getMessage());
            }

            Products addedProduct = productsService.addProduct(product, categoryId);

            if (addedProduct != null) {
                return BaseResponseController.success(addedProduct);
            } else {
                throw new ProductAlreadyExistsException();
            }
        } catch (ProductAlreadyExistsException e) {
            return BaseResponseController.fail(ResponseCode.PRODUCT_ALREADY_EXISTS.getCode(),
                                               "add product fail");
        } 
    }

    
    @PutMapping("/admin/products/{id}/{categoryId}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @PathVariable int categoryId, @RequestBody Products productDetails) {
            Products updatedProduct = productsService.updateProduct(id, productDetails, categoryId);
            return BaseResponseController.success(updatedProduct);
        
    }
}