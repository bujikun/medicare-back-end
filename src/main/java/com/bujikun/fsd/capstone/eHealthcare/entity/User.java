package com.bujikun.fsd.capstone.eHealthcare.entity;

import com.bujikun.fsd.capstone.eHealthcare.mapping.jdbc.CustomAggregateReference;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Newton Bujiku
 * @since 2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "users")
public class User extends BaseEntity{
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    @Column("account_number")
    @JsonProperty("account_number")
    private String accountNumber;
    @Column("is_account_locked")
    private Boolean isAccountLocked;
    @Column("is_account_expired")
    private Boolean isAccountExpired;
    @Column("is_credentials_expired")
    private Boolean isCredentialsExpired;
    @Column("is_enabled")
    private Boolean isEnabled;
    private BigDecimal balance;
    @MappedCollection(idColumn = "fk_user_id",keyColumn = "fk_permission_id")
    private Set<UserPermission> userPermissions;//many to many! for user actions
    @Transient
    private Set<Permission> permissions;

    {
        userPermissions = new HashSet<>();
    }


    public void linkPermission(Permission permission){
        var userPermission = new UserPermission();
        userPermission.setPermissionId(new CustomAggregateReference<>(permission.getId()));
        userPermission.setCreatedOn(DateUtil.getNow());
        userPermissions.add(userPermission);
    }

    public void linkPermissions(Set<Permission> permissions){
        var userPermissionSet = permissions.stream()
                .map(permission->{
                    var userPerm = new UserPermission();
                    userPerm.setPermissionId(new CustomAggregateReference<>(permission.getId()));
                    userPerm.setCreatedOn(DateUtil.getNow());
                    return userPerm;
                })
                .collect(Collectors.toSet());
        userPermissions.addAll(userPermissionSet);
    }
}
