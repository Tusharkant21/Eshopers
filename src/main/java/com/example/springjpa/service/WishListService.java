package com.example.springjpa.service;

import com.example.springjpa.dtos.ProductDto;
import com.example.springjpa.model.Product;
import com.example.springjpa.model.User;
import com.example.springjpa.model.WishList;
import com.example.springjpa.repository.WishListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {
    @Autowired
    WishListRepo wishListRepo;

    @Autowired
    ProductService productService;
    public void createNewWishList(WishList wishList) {
        wishListRepo.save(wishList);
    }


    public List<ProductDto> getWishListForUser(User user) {
        final List<WishList> wishLists= wishListRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for(WishList wishList:wishLists){
            productDtos.add(productService.getProductDto(wishList.getProduct()));
        }

        return  productDtos;
    }
}
