package com.example.productcqrs.command.api.events;


import com.example.productcqrs.command.api.data.Product;
import com.example.productcqrs.command.api.repository.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {

    Logger logger = LoggerFactory.getLogger(ProductEventsHandler.class);


    @Autowired
    Environment environment;


    @Autowired
    ProductRepository productRepository;

    @EventHandler
    public void productEvent(ProductCreatedEvent productCreatedEvent) throws Exception {
        try {
            logger.info("Inside productEvent ProductEventsHandler");
            Product product = new Product();
            BeanUtils.copyProperties(productCreatedEvent, product);
            productRepository.save(product);
            logger.info("Data saved successfully in database");
            throw new Exception("Testing exception");
        } catch (Exception e) {
            logger.error("Exception occured");
            throw new Exception(e.getMessage());
        }

    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw new Exception(exception.getMessage());
    }

}
