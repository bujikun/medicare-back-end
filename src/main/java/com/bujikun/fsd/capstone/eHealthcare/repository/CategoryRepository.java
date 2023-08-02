package com.bujikun.fsd.capstone.eHealthcare.repository;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.CategoryDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.SellerDTO;
import com.bujikun.fsd.capstone.eHealthcare.entity.Category;
import org.springframework.data.jdbc.repository.query.Query;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends BaseRepository<Category, UUID>{
    @Query("SELECT COUNT(*) FROM categories")
    Integer getCount();
    @Query("SELECT name AS category_name,id,created_on FROM categories")
    List<CategoryDTO> getAll();
}
