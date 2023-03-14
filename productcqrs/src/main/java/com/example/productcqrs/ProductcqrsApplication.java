package com.example.productcqrs;

import com.example.productcqrs.command.api.exception.ProductServiceExceptionHandler;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductcqrsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductcqrsApplication.class, args);
    }

    @Autowired
    public void configure(EventProcessingConfigurer eventProcessingConfigurer) {
        eventProcessingConfigurer.registerListenerInvocationErrorHandler("product", configuration -> new ProductServiceExceptionHandler());

    }


}
