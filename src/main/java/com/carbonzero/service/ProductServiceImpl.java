package com.carbonzero.service;

import com.carbonzero.dto.CategoryRequest;
import com.carbonzero.domain.Category;
import com.carbonzero.dto.CategoryResponseData;
import com.carbonzero.error.CategoryNotFoundException;
import com.carbonzero.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carbonzero.domain.Product;
import com.carbonzero.dto.ProductRequestData;
import com.carbonzero.dto.ProductResponseData;
import com.carbonzero.error.ProductNotFoundException;
import com.carbonzero.repository.ProductRepository;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.carbonzero.dto.CategoryResponseData.convertToCategoryResponseData;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final Mapper mapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(Mapper mapper, ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * 상품을 생성한다.
     * @param product
     * @return 생성된 상품
     */
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * 상품 목록을 반환한다.
     * @return 상품 목록
     */
    @Override
    public Page<ProductResponseData> getProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);

        return products.map((product) -> mapper.map(product, ProductResponseData.class));
    }

    /**
     * 특정 상품을 반환한다.
     * @param id 상품 아이디
     * @return 조회할 상품
     */
    @Override
    public Product getProduct(Long id) {
        return findProduct(id);
    }

    /**
     *  상품을 업데이트한다.
     * @param id,productRequestData
     * @return int
     */
    @Override
    public Product updateProduct(Long id, ProductRequestData productRequestData) {
        Product updatedProduct = mapper.map(productRequestData, Product.class);
        Product originalProduct = findProduct(id);
        originalProduct.changeWith(updatedProduct);
        return originalProduct;
    }

    /**
     * 상품을 삭제한다.
     * @param id 삭제할 상품의 아이디
     */
    @Override
    public Product deleteProduct(Long id) {
        Product product = findProduct(id);
        productRepository.delete(product);
        return product;
    }

    /**
     * 특정한 상품을 조회하여 존재한다면 반환한다.
     * @param id 검색할 상품 아이디
     * @return 상품
     */
    public Product findProduct(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<ProductResponseData> recommend(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id));

        Category category = categoryRepository.findByIdAndIsActive(product.getCategory().getId(), true).orElseThrow(
                () -> new CategoryNotFoundException(product.getCategory().getId()));


        List<Product> productList = productRepository.findTop5ByIsActiveAndCategoryIdAndIsEcoFriendlyOrderByCarbonEmissionsAsc(true, category.getId(), true);

        return productList.stream().map(ProductResponseData::convertToProductResponseData).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseData> getCategories() {
        List<Category> categories = categoryRepository.findAllByIsActive(true);
        List<Category> list = categories.stream().filter(category -> category.getParentCategory() == null)
                .collect(Collectors.toList());
        return list.stream().map(CategoryResponseData::convertToCategoryResponseData).collect(Collectors.toList());
    }

    @Transactional
    public CategoryResponseData createCategory(CategoryRequest categoryRequest){
        Category parent = null;
        if(categoryRequest.getParentId() != null){
            parent = categoryRepository.findById(categoryRequest.getParentId()).orElseThrow();
        }

        Category category = categoryRepository.save(Category.builder()
                .isActive(true)
                .name(categoryRequest.getName())
                .parentCategory(parent)
                .subCategoryList(new ArrayList<>())
                .build());

        if(parent != null) parent.getSubCategoryList().add(category);

        return convertToCategoryResponseData(category);
    }
}
