package com.bujikun.fsd.capstone.eHealthcare.config.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDTO {
    @JsonProperty("items")
    private List<ProductDTO> items;
    private Integer totalItemsCount;
    private BigDecimal totalPrice;
    private String name;
    private String username;
}
