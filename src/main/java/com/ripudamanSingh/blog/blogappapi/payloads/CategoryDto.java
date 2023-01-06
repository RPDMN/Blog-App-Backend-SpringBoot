package com.ripudamanSingh.blog.blogappapi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;

    @NotBlank
    @Size(min=4,message = "category Title should be of size 4 or more")
    private String categoryTitle;

    @Size(max = 10,message = "can be of max size 10")
    private String categoryDescription;
}
