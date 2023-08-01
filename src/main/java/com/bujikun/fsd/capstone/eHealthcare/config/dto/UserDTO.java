package com.bujikun.fsd.capstone.eHealthcare.config.dto;

import com.bujikun.fsd.capstone.eHealthcare.entity.Permission;
import com.bujikun.fsd.capstone.eHealthcare.entity.User;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private UUID id;
    private String username;
    private String firstname;
    private String lastname;
    @Column("account_number")
    @JsonProperty("account_number")
    private String accountNumber;
    private BigDecimal balance;
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

    public static  UserDTO fromUser(User user, DateUtil dateUtil){
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .accountNumber(user.getAccountNumber())
                .balance(user.getBalance())
                .createdOn(dateUtil.fromInstant(user.getCreatedOn()))
                .build();
    }
}
