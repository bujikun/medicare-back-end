package com.bujikun.fsd.capstone.eHealthcare.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;

import java.time.Instant;
import java.util.UUID;

/** A super class for all entities which holds common properties.
 * This should not be instantiated. It's not represented by a database table
 * @author Newton Bujiku
 * @since 2023
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@SuperBuilder
public class BaseEntity {
    @Id
    private UUID id;
    @CreatedBy
    @Column("created_by")
    private String createdBy;
    @LastModifiedBy
    @Column("last_modified_by")
    private String lastModifiedBy;
    @CreatedDate
    @Column("created_on")
    private Instant createdOn;
    @LastModifiedDate
    @Column("updated_on")
    private Instant updatedOn;
    @Column("version")
    private Integer version;

}
