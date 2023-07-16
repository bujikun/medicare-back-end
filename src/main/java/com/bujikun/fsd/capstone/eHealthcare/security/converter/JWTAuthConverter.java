package com.bujikun.fsd.capstone.eHealthcare.security.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.bujikun.fsd.capstone.eHealthcare.security.service.TokenService;


@Component
@RequiredArgsConstructor
public class JWTAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final TokenService tokenService;
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        var token = tokenService.findByValue(jwt.getTokenValue());
        if(Optional.ofNullable(token).isEmpty()){
            throw new InvalidBearerTokenException("Invalidated token");
        }

        if(token.getIsInvalidated()){
            throw new InvalidBearerTokenException("Invalidated token");
        }
        Set<SimpleGrantedAuthority> authorities = ((ArrayList<String>)jwt.getClaim("permissions")).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toUnmodifiableSet());
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwt,authorities);
        return jwtAuthenticationToken;
    }
}
