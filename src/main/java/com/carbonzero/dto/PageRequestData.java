package com.carbonzero.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestData {

    @ApiParam(required = true, defaultValue = "0", example = "0", type = "integer")
    @PositiveOrZero
    private int page;
    @ApiParam(required = true, defaultValue = "20", example = "20", type = "integer")
    @Positive
    private int size;
    @ApiParam(required = true, defaultValue = "createAt", example = "createAt", type = "string")
    @NotBlank
    private String sortBy = "createAt";

    public PageRequest convertToPageRequest() {
        return PageRequest.of(this.getPage(), this.getSize(), Sort.Direction.DESC, sortBy.split(","));
    }
}
