package com.bujikun.fsd.capstone.eHealthcare.config.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private UUID id;
    private String username;
    private String firstname;
    private String lastname;
    @Column("account_number")
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
}
