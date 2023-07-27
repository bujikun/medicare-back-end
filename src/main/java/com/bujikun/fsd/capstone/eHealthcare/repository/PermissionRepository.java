package com.bujikun.fsd.capstone.eHealthcare.repository;

import com.bujikun.fsd.capstone.eHealthcare.entity.Permission;

import java.util.UUID;

public interface PermissionRepository extends BaseRepository<Permission, UUID>{
    //Set<Perm>
    Permission findByName(String name);
}
