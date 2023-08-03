package com.bujikun.fsd.capstone.eHealthcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;

import javax.json.JsonPatch;
import javax.json.JsonStructure;
import javax.json.JsonValue;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @Column("deleted")
    private Boolean deleted;


    public  <T extends BaseEntity> T patch(JsonPatch jsonPatch, ObjectMapper mapper,Class<T> clazz){
        //convert entity to JSON document
        JsonStructure entityInJson = mapper.convertValue(this,JsonStructure.class);
        //apply the patch to the JSON document
        JsonValue patchedJson = jsonPatch.apply(entityInJson);
        //convert the patched json to entity and return it
        return mapper.convertValue(patchedJson,clazz);
    }

//    public  <T extends BaseEntity> T patch(JsonPatch jsonPatch, ObjectMapper mapper){
//        //convert entity to JSON document
//        JsonStructure entityInJson = mapper.convertValue(this,JsonStructure.class);
//        //apply the patch to the JSON document
//        JsonValue patchedJson = jsonPatch.apply(entityInJson);
//        //convert the patched json to entity and return it
//        return (T) mapper.convertValue(patchedJson,this.getClass());
//    }

}
