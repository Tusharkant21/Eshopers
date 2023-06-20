package com.example.springjpa.controller;
import com.example.springjpa.common.ApiResponse;
import com.example.springjpa.common.ApiResponseforCreate;
import com.example.springjpa.model.Category;
import com.example.springjpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")

public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponseforCreate> createCategory(@RequestBody Category category) throws Exception{
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponseforCreate(true, "created a new Category"), HttpStatus.CREATED);

    }
    @GetMapping("/list")
    public List<Category> listCategory(){
       return categoryService.listCategory();
    }

  @PostMapping("update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category){
       return categoryService.updateCategory(categoryId, category);

  }
}
