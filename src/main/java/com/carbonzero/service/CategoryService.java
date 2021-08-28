package com.carbonzero.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbonzero.domain.Category;
import com.carbonzero.dto.CategoryResponseData;
import com.carbonzero.repository.CategoryRepository;

@Transactional
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponseData> getCategories() {
        final List<Category> categories = categoryRepository.findAllByParentIsNull();
        return categories.stream().map(CategoryResponseData::new).collect(Collectors.toList());
    }
}
