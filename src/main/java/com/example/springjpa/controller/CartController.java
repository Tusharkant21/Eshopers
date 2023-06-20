package com.example.springjpa.controller;

import com.example.springjpa.common.ApiResponse;
import com.example.springjpa.dtos.ProductDto;
import com.example.springjpa.dtos.cart.CartDto;
import com.example.springjpa.model.User;
import com.example.springjpa.service.AuthenticationService;
import com.example.springjpa.service.CartService;
import com.example.springjpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
   private AuthenticationService authenticationService;


    //post cart
    @PostMapping("add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody CartDto cartDto,
                                                 @RequestParam("token") String token){
        //authenticate the user
       authenticationService.authenticate(token);

        //find the user
        User user = authenticationService.getUser(token);


        cartService.addToCart(cartDto, user);

        return  new ResponseEntity<>(new ApiResponse(true, "Added to cart", user.getId()), HttpStatus.CREATED);


    }

    // get all cart items for a user



    // delete a cart for a user;


}
