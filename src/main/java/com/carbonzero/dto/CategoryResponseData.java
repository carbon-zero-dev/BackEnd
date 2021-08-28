package com.carbonzero.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.carbonzero.domain.Category;

import lombok.Getter;

@Getter
public class CategoryResponseData {
    private Long id;
    private String name;
    private List<CategoryResponseData> children;

    public CategoryResponseData(final Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.children = category.getChildren()
            .stream()
            .map(CategoryResponseData::new)
            .collect(Collectors.toList());
    }
}
