package com.bujikun.fsd.capstone.eHealthcare.repository;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.UserDTO;
import com.bujikun.fsd.capstone.eHealthcare.entity.User;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.user.UserExistsException;
import org.springframework.data.jdbc.repository.query.Query;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BaseRepository<User, UUID> {
    Optional<User> findUserByUsername(String username);
    boolean existsByUsername(String username);
    @Query("SELECT COUNT(*) FROM users")
    Integer getCount();
}
