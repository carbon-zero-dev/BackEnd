package com.carbonzero.error;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long categoryId) {
        super("category not found: " + categoryId);
    }
}
