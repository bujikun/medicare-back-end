package com.bujikun.fsd.capstone.eHealthcare.config.dto;

import com.bujikun.fsd.capstone.eHealthcare.entity.Category;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
    private UUID id;
    @Column("category_name")
    private String name;
    @JsonProperty("created_on")
    private String createdOn;
    @JsonProperty("updated_on")
    private String updatedOn;
    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("last_modified_by")
    private String lastModifiedBy;
    @JsonProperty("disabled")
    private Boolean deleted;

    public static Category toCategory(CategoryDTO categoryDTO, DateUtil dateUtil){
        return Category.builder()
                .name(categoryDTO.name)
                .createdOn(dateUtil.now())
                .deleted(false)
                .build();
    }

    public static CategoryDTO fromCategory(Category category,DateUtil dateUtil){
        return CategoryDTO.builder()
                .name(category.getName())
                .createdOn(dateUtil.fromInstant(category.getCreatedOn()))
                .deleted(category.getDeleted())
                .build();
    }


}
