package com.example.productcqrs.query.api.projection;

import com.example.productcqrs.command.api.data.Product;
import com.example.productcqrs.command.api.modal.ProductRestModel;
import com.example.productcqrs.command.api.repository.ProductRepository;
import com.example.productcqrs.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    @Autowired
    private ProductRepository productRepository;

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery) {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(product -> ProductRestModel.builder()
                .price(product.getPrice()).name(product.getName()).quantity(product.getQuantity()).build()).collect(Collectors.toList());
    }
}
