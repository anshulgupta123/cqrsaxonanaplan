package com.example.productcqrs.command.api.exception;


import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public class ProductServiceExceptionHandler implements ListenerInvocationErrorHandler {

    Logger logger= LoggerFactory.getLogger(ProductServiceExceptionHandler.class);
    @Override
    public void onError(@Nonnull Exception e, @Nonnull EventMessage<?> eventMessage, @Nonnull EventMessageHandler eventMessageHandler) throws Exception {
        logger.info("Inside ProductServiceExceptionHandler");
        throw new Exception(e.getMessage());
    }
}
