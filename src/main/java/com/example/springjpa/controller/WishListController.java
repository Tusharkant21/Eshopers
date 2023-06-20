package com.example.springjpa.controller;

import com.example.springjpa.common.ApiResponse;
import com.example.springjpa.dtos.ProductDto;
import com.example.springjpa.exceptions.AuthenticationFailedException;
import com.example.springjpa.model.Product;
import com.example.springjpa.model.User;
import com.example.springjpa.model.WishList;
import com.example.springjpa.repository.WishListRepo;
import com.example.springjpa.service.AuthenticationService;
import com.example.springjpa.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    WishListService wishListService;

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    private WishListRepo wishListRepo;

    //  create  and save wish list by post
    @PostMapping("/create")
     public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String token) {
         // Authenticate tokens
         authenticationService.authenticate(token);


         // find the user
         User user = authenticationService.getUser(token);

         WishList wishList = new WishList(user, product);
        // save the wishlist
         wishListService.createNewWishList(wishList);

         ApiResponse apiResponse = new ApiResponse(true,"Added to Wishlist", wishList.getId());
         return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);




     }
    // get all wishlist itmes for a user

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) throws AuthenticationFailedException {
        // first authenticate if the token is valid
        authenticationService.authenticate(token);
        // then fetch the user linked to the token
        User user = authenticationService.getUser(token);
        // first retrieve the wishlist items
        List<ProductDto> productDtos = wishListService.getWishListForUser(user);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);

    }
}
