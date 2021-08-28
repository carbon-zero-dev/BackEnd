package com.carbonzero.repository;

import com.carbonzero.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findTop5ByIsActiveAndCategoryIdAndIsEcoFriendlyOrderByCarbonEmissionsAsc(boolean active,Long categoryId,boolean isEcoFriendly);
}
