package com.carbonzero.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbonzero.domain.Category;
import com.carbonzero.dto.CategoryResponseData;
import com.carbonzero.error.CategoryNotFoundException;
import com.carbonzero.repository.CategoryRepository;

@Transactional
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * 특정 카테고리를 반환한다.
     * @param categoryId
     * @return 카테고리
     */
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
            .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    /**
     * 카테고리 목록을 반환한다.
     * @return 카테고리 목록
     */
    public List<CategoryResponseData> getCategories() {
        final List<Category> categories = categoryRepository.findAllByParentIsNull();
        return categories.stream().map(CategoryResponseData::new).collect(Collectors.toList());
    }
}
