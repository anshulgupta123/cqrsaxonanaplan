package com.example.productcqrs.query.api.controller;

import com.example.productcqrs.command.api.Utility.Constants;
import com.example.productcqrs.command.api.Utility.UrlConstants;
import com.example.productcqrs.command.api.modal.ProductRestModel;
import com.example.productcqrs.command.api.modal.Response;
import com.example.productcqrs.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductQueryController {


    Logger logger = LoggerFactory.getLogger(ProductQueryController.class);

    @Autowired
    Environment env;


    @Autowired
    QueryGateway queryGateway;

    @GetMapping(UrlConstants.GET_ALL_PRODUCTS)
    public ResponseEntity<Object> getAllProducts() {
        logger.info("Inside getAllProducts of ProductQueryController");
        GetProductsQuery getProductsQuery = new GetProductsQuery();
        List<ProductRestModel> responseData = queryGateway.query(getProductsQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
        Object response = new Response<>(responseData, env.getProperty(Constants.SUCCESS_CODE), env.getProperty(Constants.PRODUCT_FETCHED_SUCCESFULLY));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
