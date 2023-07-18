package com.bujikun.fsd.capstone.eHealthcare.repository;

import com.bujikun.fsd.capstone.eHealthcare.entity.Category;
import org.springframework.data.jdbc.repository.query.Query;

import java.util.UUID;

public interface CategoryRepository extends BaseRepository<Category, UUID>{
    @Query("SELECT COUNT(*) FROM categories")
    Integer getCount();
}
