package com.carbonzero.dto;

import com.github.dozermapper.core.Mapping;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProductResponseData {

    @Mapping("id")
    private Long id;

    @Mapping("createAt")
    private LocalDateTime createAt;

    @Mapping("updateAt")
    private LocalDateTime updateAt;

    @Mapping("name")
    private String name;

    @Mapping("brand")
    private String brand;

    @Mapping("price")
    private Long price;

    @Mapping("description")
    private String description;

    @Mapping("imageLink")
    private List<String> imageLink;

    @Mapping("category")
    private String category;

    @Mapping("isEcoFriendly")
    private Boolean isEcoFriendly;

    @Mapping("carbonEmissions")
    private Integer carbonEmissions;
}
