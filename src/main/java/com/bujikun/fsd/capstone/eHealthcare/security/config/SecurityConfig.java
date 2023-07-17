package com.bujikun.fsd.capstone.eHealthcare.security.config;

import com.bujikun.fsd.capstone.eHealthcare.security.converter.JWTAuthConverter;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration(proxyBeanMethods = false)
@EnableMethodSecurity
//@EnableWebSecurity
public class SecurityConfig {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain tokenSecurityFilterChain(
            HttpSecurity httpSecurity
            ) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                //.cors(cors->cors.configurationSource(corsConfigurationSource))
                .securityMatcher(new AntPathRequestMatcher("/auth/login", HttpMethod.POST.name()))
                .authorizeHttpRequests(
                        reqs -> reqs.anyRequest().authenticated()
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                )
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE+1)
    public SecurityFilterChain apiSecurityFilterChain(
            HttpSecurity httpSecurity,
            JwtDecoder jwtDecoder,
            JWTAuthConverter jwtAuthConverter
            ) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
               // .cors(cors->cors.configurationSource(corsConfigurationSource))
                .securityMatcher("/api/v1/**")
                .authorizeHttpRequests(
                        reqs -> reqs
                                .anyRequest().authenticated()
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(ors -> ors
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter)
                                .decoder(jwtDecoder)
                        )
                )
                .exceptionHandling(e -> e.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()));

        return httpSecurity.build();
    }

    @Bean
    public JwtDecoder jwtDecoder(ImmutableJWKSet<SecurityContext> immutableJWKSet) throws JOSEException {
        RSAKey key = immutableJWKSet.getJWKSet().getKeys().get(0).toRSAKey();
        return NimbusJwtDecoder.withPublicKey(key.toRSAPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(ImmutableJWKSet<SecurityContext> immutableJWKSet) {
        return new NimbusJwtEncoder(immutableJWKSet);
    }

    @Bean
    public ImmutableJWKSet<SecurityContext> immutableJWKSet() throws NoSuchAlgorithmException {
        //generate rsa key pair
        KeyPairGenerator kp = KeyPairGenerator.getInstance("RSA");
        kp.initialize(2048);
        KeyPair k = kp.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) k.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) k.getPrivate();
        JWK jwk = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        return new ImmutableJWKSet<>(new JWKSet(jwk));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService
    ) {
        var dap = new DaoAuthenticationProvider();
        dap.setPasswordEncoder(passwordEncoder);
        dap.setUserDetailsService(userDetailsService);
        return dap;
    }
//    @Bean
//    public UrlBasedCorsConfigurationSource authCors() {
//        CorsConfiguration apiCors = new CorsConfiguration();
//        apiCors.setMaxAge(0L);
//        apiCors.setAllowedMethods(List.of("GET", "POST", "DELETE"));
//        apiCors.setAllowedOrigins(List.of("http://localhost:3000","http://localhost:4173"));
//        apiCors.setAllowedHeaders(List.of("*"));
//
//        CorsConfiguration authCors = new CorsConfiguration();
//        authCors.setMaxAge(0L);
//        authCors.setAllowedMethods(List.of("POST"));
//        authCors.setAllowedOrigins(List.of("http://localhost:3000","http://localhost:4173"));
//        authCors.setAllowedHeaders(List.of("*"));
//
//        UrlBasedCorsConfigurationSource ubcs = new UrlBasedCorsConfigurationSource();
//        ubcs.registerCorsConfiguration("/api/**", apiCors);
//        ubcs.registerCorsConfiguration("/auth/**", authCors);
//        return ubcs;
//
//    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//
//        return web -> {
//            web.ignoring().requestMatchers();
//        };
//    }

}
