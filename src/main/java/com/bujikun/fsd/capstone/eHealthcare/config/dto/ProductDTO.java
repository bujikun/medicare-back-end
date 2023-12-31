package com.bujikun.fsd.capstone.eHealthcare.config.dto;

import com.bujikun.fsd.capstone.eHealthcare.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private UUID id;
    private String name;
    private BigDecimal price;
    @Column("img_url")
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("description")
    private String description;
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
    @JsonProperty("seller_id")
    @Column("seller_id")
    private UUID sellerId;
    @Column("seller_name")
    @JsonProperty("seller_name")
    private String seller_name;
    @Column("category_id")
    @JsonProperty("category_id")
    private UUID categoryId;
    @Column("category_name")
    @JsonProperty("category_name")
    private String categoryName;
    @Transient
    private Integer count;

}
