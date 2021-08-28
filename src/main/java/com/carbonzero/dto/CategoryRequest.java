package com.carbonzero.dto;

import com.github.dozermapper.core.Mapping;
import com.sun.istack.Nullable;
import lombok.Data;

@Data
public class CategoryRequest {
    private String name;

    @Mapping("parentId")
    private Long parentId;
}
