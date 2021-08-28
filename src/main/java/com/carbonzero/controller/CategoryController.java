package com.carbonzero.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carbonzero.dto.CategoryResponseData;
import com.carbonzero.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 목록을 반환한다.
     * @return 카테고리 리스트
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CategoryResponseData>> details() {
        final List<CategoryResponseData> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }
}

