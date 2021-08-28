package com.carbonzero.dto;

import com.carbonzero.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CategoryResponseData {

    private Long id;
    private String name;
    private String codeName;
    private Boolean isActive = true;
    private List<CategoryResponseData> subCategoryList;

    public static CategoryResponseData convertToCategoryResponseData(Category category){
        return CategoryResponseData.builder()
                .id(category.getId())
                .name(category.getName())
                .codeName(category.getName())
                .isActive(category.getIsActive())
                .subCategoryList(category.getSubCategoryList().stream()
                        .map(CategoryResponseData::convertToCategoryResponseData)
                        .collect(Collectors.toList()))
                .build();
    }
}
