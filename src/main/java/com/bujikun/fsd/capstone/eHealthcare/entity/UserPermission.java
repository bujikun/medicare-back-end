package com.bujikun.fsd.capstone.eHealthcare.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
@Builder
@Table(name = "users_permissions")
public class UserPermission {
    @Column("fk_permission_id")
    private AggregateReference<Permission, UUID> permissionId;
    @CreatedDate
    @Column("created_on")
    private LocalDateTime createdOn;
    @LastModifiedDate
    @Column("updated_on")
    private LocalDateTime updatedOn;
}
