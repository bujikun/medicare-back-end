package com.bujikun.fsd.capstone.eHealthcare.security.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Component
public class Permissions {
    private final String userReadAll = "user:read:all";
}
