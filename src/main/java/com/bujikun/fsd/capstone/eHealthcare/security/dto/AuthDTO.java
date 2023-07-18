package com.bujikun.fsd.capstone.eHealthcare.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthDTO {
    private String username;
    private String password;
    private String token;
    @JsonProperty("name")
    private String fullname;
    private String role;
    private String email;
    @JsonProperty("account_number")
    private String accountNumber;

}
