package com.example.springjpa.service;

import com.example.springjpa.dtos.ProductDto;
import com.example.springjpa.dtos.cart.CartDto;
import com.example.springjpa.model.Cart;
import com.example.springjpa.model.Product;
import com.example.springjpa.model.User;
import com.example.springjpa.repository.CartRepo;
import com.example.springjpa.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ProductService productService;
    @Autowired
    private CartRepo cartRepo;

    public void addToCart(CartDto cartDto, User user) {
        // validate if the product id is valid;
       Product product =  productService.findById(cartDto.getProduct_id());


       // save the cart
       Cart cart = new Cart();
       cart.setProduct(product);
       cart.setUser(user);
       cart.setQuantity(cartDto.getQuantity());
       cart.setCreatedDate(new Date());
       cartRepo.save(cart);




    }
}
