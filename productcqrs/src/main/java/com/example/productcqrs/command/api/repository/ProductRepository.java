package com.example.productcqrs.command.api.repository;

import com.example.productcqrs.command.api.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
