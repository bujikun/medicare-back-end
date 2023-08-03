package com.bujikun.fsd.capstone.eHealthcare.entity;

import com.bujikun.fsd.capstone.eHealthcare.mapping.jdbc.CustomAggregateReference;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Newton Bujiku
 * @since 2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "users_permissions")
public class UserPermission extends NonAggregateEntity<Permission,UUID>{
    @Column("fk_permission_id")
    private CustomAggregateReference<Permission, UUID> permissionId;
}
