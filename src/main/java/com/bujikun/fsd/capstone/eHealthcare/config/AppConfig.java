package com.bujikun.fsd.capstone.eHealthcare.config;

import com.bujikun.fsd.capstone.eHealthcare.config.http.converter.JsonPatchHttpMessageConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class AppConfig {
    @Bean
    public JsonPatchHttpMessageConverter jsonPatchHttpMessageConverter(){
        return  new JsonPatchHttpMessageConverter();
    }

    @Bean
    @Qualifier("JSON_PATCH_OBJECT_MAPPER")
    public ObjectMapper jsonPatchObjectMapper(){
        return new ObjectMapper()

                .setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JSR353Module())
                .registerModule(new JavaTimeModule());
    }
}
