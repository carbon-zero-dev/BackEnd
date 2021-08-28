package com.carbonzero.repository;

import com.carbonzero.domain.Category;
import com.carbonzero.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByIdAndIsActive(Long categoryId, int i);

    Optional<Category> findByIdAndIsActive(Long categoryId, boolean active);

    List<Category> findAllByIsActive(boolean active);
}
