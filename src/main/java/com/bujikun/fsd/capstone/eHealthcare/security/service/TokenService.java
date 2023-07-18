package com.bujikun.fsd.capstone.eHealthcare.security.service;

import com.bujikun.fsd.capstone.eHealthcare.security.dto.AuthDTO;
import com.bujikun.fsd.capstone.eHealthcare.security.entity.Token;
import com.bujikun.fsd.capstone.eHealthcare.security.model.SecurityUserDetails;
import com.bujikun.fsd.capstone.eHealthcare.security.repository.TokenRepository;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final TokenRepository tokenRepository;
    private final DateUtil dateUtil;
    public AuthDTO generateToken(Authentication authentication){
        var ud = (SecurityUserDetails) authentication.getPrincipal();
        JwtClaimsSet claims  =  JwtClaimsSet.builder()
                .issuer("http://localhost:8080")
                .subject(ud.getUsername())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(30, ChronoUnit.MINUTES))
                .audience(Collections.singletonList("http://localhost:8080"))
                .claim("permissions",ud.getAuthorities().stream().map(s-> s.getAuthority()).toList())
                .build();
        String jwtTokenValue =  jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        Token token = Token.builder()
                .value(jwtTokenValue)
                .username(ud.getUsername())
                .isInvalidated(false)
                .createdOn(dateUtil.now())
                .version(1)
                .build();
        tokenRepository.save(token);
        return AuthDTO.builder()
                .token(jwtTokenValue)
                .email(ud.getEmail())
                .fullname(ud.getFullname())
                .accountNumber(ud.getAccountNumber())
                .role((String) ud.getPermissionNames().toArray()[0])
                .build();
    }

    public Token findByValue(String value){
        return tokenRepository.findTokenByValue(value);
    }

    public Token findByUsername(String username){
        return tokenRepository.findTokenByValue(username);
    }

    public void invalidate(String username){
        tokenRepository.invalidateUserTokens(username,false);
    }


}
