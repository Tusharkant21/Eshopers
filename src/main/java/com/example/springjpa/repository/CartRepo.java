package com.example.springjpa.repository;

import com.example.springjpa.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo  extends JpaRepository<Cart, Integer> {
}
