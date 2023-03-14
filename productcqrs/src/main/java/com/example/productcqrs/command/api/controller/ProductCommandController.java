package com.example.productcqrs.command.api.controller;


import com.example.productcqrs.command.api.Utility.Constants;
import com.example.productcqrs.command.api.Utility.UrlConstants;
import com.example.productcqrs.command.api.commands.CreateProductCommand;
import com.example.productcqrs.command.api.modal.ProductRestModel;
import com.example.productcqrs.command.api.modal.Response;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProductCommandController {


    Logger logger = LoggerFactory.getLogger(ProductCommandController.class);


    @Autowired
    Environment env;
    CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(value = UrlConstants.ADD_PRODUCT)
    public ResponseEntity<Object> addProduct(@RequestBody ProductRestModel productRestModel) throws Exception {
        try {
            logger.info("Request for addProduct of  ProductCommandController :{}", productRestModel);
            CreateProductCommand createProductCommand = CreateProductCommand.builder()
                    .productId(UUID.randomUUID().toString()).
                    price(productRestModel.getPrice()).name(productRestModel.getName()).quantity(productRestModel.getQuantity()).
                    build();
            logger.info("Sending command to gateway");
            Object result = commandGateway.sendAndWait(createProductCommand);
            logger.info("Result is :{}", result);
            Object response = new Response<>(result, env.getProperty(Constants.SUCCESS_CODE), env.getProperty(Constants.PRODUCT_SAVED_SUCCESFULLY));
            logger.info("Retruning response from controllrt :{}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception occured");
            throw new Exception(e.getMessage());
        }
    }

}
