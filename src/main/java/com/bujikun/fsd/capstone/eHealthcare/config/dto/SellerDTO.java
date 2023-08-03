package com.bujikun.fsd.capstone.eHealthcare.config.dto;

import com.bujikun.fsd.capstone.eHealthcare.entity.Category;
import com.bujikun.fsd.capstone.eHealthcare.entity.Seller;
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
public class SellerDTO {
    private UUID id;
    @Column("seller_name")
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

    public static Seller toSeller(SellerDTO sellerDTO, DateUtil dateUtil){
        return Seller.builder()
                .name(sellerDTO.name)
                .createdOn(dateUtil.now())
                .deleted(false)
                .build();
    }

    public static SellerDTO fromSeller(Seller seller,DateUtil dateUtil){
        return SellerDTO.builder()
                .name(seller.getName())
                .createdOn(dateUtil.fromInstant(seller.getCreatedOn()))
                .deleted(seller.getDeleted())
                .build();
    }


}
