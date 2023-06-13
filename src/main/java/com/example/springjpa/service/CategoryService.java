package com.example.springjpa.service;

import com.example.springjpa.common.ApiResponse;
import com.example.springjpa.model.Category;
import com.example.springjpa.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    public void createCategory(Category category) throws Exception{
        categoryRepo.save(category);
    }

    public List<Category> listCategory(){
        return categoryRepo.findAll();

    }
    public ResponseEntity<ApiResponse> updateCategory(int categoryId, Category updateCategory){

        Category category1 = categoryRepo.findById(categoryId).orElse(null);
        if(category1!=null){
            category1.setCategoryName(updateCategory.getCategoryName());
            category1.setDescription(updateCategory.getDescription());
            category1.setImageUrl(updateCategory.getImageUrl());
            categoryRepo.save(category1);
            return new ResponseEntity<>(new ApiResponse(true, "updated Category id is ", categoryId), HttpStatus.CREATED);

        }
        return new ResponseEntity<>(new ApiResponse(false, "Category is not found by Id " , categoryId), HttpStatus.BAD_REQUEST);



    }
}
