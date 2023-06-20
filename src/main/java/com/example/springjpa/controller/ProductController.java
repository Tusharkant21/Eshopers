package com.example.springjpa.controller;

import com.example.springjpa.common.ApiResponse;
import com.example.springjpa.common.ApiResponseforCreate;
import com.example.springjpa.dtos.ProductDto;
import com.example.springjpa.model.Category;
import com.example.springjpa.model.Product;
import com.example.springjpa.repository.CategoryRepo;
import com.example.springjpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepo categoryRepo;
    @PostMapping("/add")
     public ResponseEntity<ApiResponseforCreate> createProduct(@RequestBody ProductDto productDto){
       // Optional<Category> optionalCategory=categoryRepo.findById(productDto.getCategoryId());
//        if(optionalCategory.isPresent()){
//            return new ResponseEntity<>(new ApiResponseforCreate(false,"category does not exist"), HttpStatus.BAD_REQUEST);
//
//        }

       return  productService.createProduct(productDto);
      //  return new ResponseEntity<>(new ApiResponseforCreate(false,"product is added"), HttpStatus.CREATED);

    }
    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>>getProducts(){
        List<ProductDto> products= productService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);


    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("productId") int productId, @RequestBody ProductDto productDto){
        return productService.updateProduct(productId, productDto);

    }



}
