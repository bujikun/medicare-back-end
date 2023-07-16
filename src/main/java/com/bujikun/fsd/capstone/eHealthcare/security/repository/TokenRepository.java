package com.bujikun.fsd.capstone.eHealthcare.security.repository;

import com.bujikun.fsd.capstone.eHealthcare.repository.BaseRepository;
import com.bujikun.fsd.capstone.eHealthcare.security.entity.Token;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;

public interface TokenRepository extends BaseRepository<Token, UUID> {
    Token findTokenByValue(@Param("value") String value);
    Token findTokenByUsername(@Param("username") String username);
    @Modifying
    @Query("""
            UPDATE tokens
            SET invalidated = :invalidated, version = (version+1)
            WHERE username = :username;
            """)
    void invalidateUserTokens(String username,Boolean invalidated);
}
