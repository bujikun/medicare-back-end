package com.bujikun.fsd.capstone.eHealthcare.security.repository;

import com.bujikun.fsd.capstone.eHealthcare.entity.User;
import com.bujikun.fsd.capstone.eHealthcare.repository.BaseRepository;
import com.bujikun.fsd.capstone.eHealthcare.security.model.SecurityUserDetails;
import com.bujikun.fsd.capstone.eHealthcare.security.model.mapping.SecurityUserDetailsResultSetExtractor;
import org.springframework.data.jdbc.repository.query.Query;

import java.util.Optional;
import java.util.UUID;

public interface SecurityUserRepository extends BaseRepository<User, UUID> {
    @Query(value = """
            SELECT username,
                   password,
                   is_account_expired,
                   is_account_locked,
                   is_credentials_expired,
                   is_enabled,
                   p.name as permission_name
            FROM users
                     JOIN users_permissions up ON users.id = up.fk_user_id
                     JOIN permissions p ON up.fk_permission_id = p.id
            WHERE users.username = :username;
            """, resultSetExtractorClass = SecurityUserDetailsResultSetExtractor.class)
    Optional<SecurityUserDetails> loadUserByUsername(String username);
}
