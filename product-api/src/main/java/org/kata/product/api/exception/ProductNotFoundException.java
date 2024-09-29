package org.kata.product.api.exception;

public class ProductNotFoundException extends ResourceNotFoundException {
    private static final String MESSAGE_TEMPLATE = "Product with id %s cannot be found";

    public ProductNotFoundException(Integer productId) {
        super(String.format(MESSAGE_TEMPLATE, productId));
    }
}
