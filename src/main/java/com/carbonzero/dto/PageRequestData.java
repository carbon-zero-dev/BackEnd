package com.carbonzero.dto;

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
    private int page;
    @ApiParam(required = true, defaultValue = "20", example = "20", type = "integer")
    private int size;
    @ApiParam(required = true, defaultValue = "createAt", example = "createAt", type = "string")
    private String sortBy = "createAt";

    public PageRequest convertToPageRequest() {
        return PageRequest.of(this.getPage(), this.getSize(), Sort.Direction.DESC, sortBy.split(","));
    }
}
