package com.example.springjpa.service;

import com.example.springjpa.common.ApiResponse;
import com.example.springjpa.common.ApiResponseforCreate;
import com.example.springjpa.dtos.ProductDto;
import com.example.springjpa.exceptions.ProductNotExistException;
import com.example.springjpa.model.Category;
import com.example.springjpa.model.Product;
import com.example.springjpa.repository.CategoryRepo;
import com.example.springjpa.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public ResponseEntity<ApiResponseforCreate> createProduct(ProductDto productDto){
        Category category = categoryRepo.findById(productDto.getCategoryId()).orElse(null);
        Product product = new Product();


        if(category!=null) {
            product.setName(productDto.getName());
            product.setImageUrl(productDto.getImageUrl());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setCategory(category);
            productRepo.save(product);
            return new ResponseEntity<>(new ApiResponseforCreate(true,"product added successfully"), HttpStatus.CREATED);
        }
return new ResponseEntity<>(new ApiResponseforCreate(false,"Category does not found"),HttpStatus.BAD_REQUEST);
    }

    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setId(product.getId());
        return productDto;
    }
    public List<ProductDto> getAllProducts(){
        List<Product> allProducts = productRepo.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product : allProducts){
            productDtoList.add(getProductDto(product));
        }
        return productDtoList;
    }
    public ResponseEntity<ApiResponse> updateProduct(int productId, ProductDto updateProduct){

        Product product1 = productRepo.findById(productId).orElse(null);
        if(product1!=null){
            product1.setName(updateProduct.getName());
            product1.setImageUrl(updateProduct.getImageUrl());
            product1.setPrice(updateProduct.getPrice());
            productRepo.save(product1);
            return new ResponseEntity<>(new ApiResponse(true, "updated Product id is ", productId), HttpStatus.CREATED);

        }
        return new ResponseEntity<>(new ApiResponse(false, "Product is not found by Id " , productId), HttpStatus.BAD_REQUEST);



    }

    public Product findById(Integer product_id) throws ProductNotExistException {
       Optional<Product> optionalProduct =productRepo.findById(product_id);
       if (optionalProduct.isEmpty()){
           throw new ProductNotExistException("product is invalid with id " +product_id);
       }
       return optionalProduct.get();
    }
}
