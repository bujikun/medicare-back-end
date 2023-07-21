package com.bujikun.fsd.capstone.eHealthcare.repository;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.SellerDTO;
import com.bujikun.fsd.capstone.eHealthcare.entity.Category;
import com.bujikun.fsd.capstone.eHealthcare.entity.Seller;
import org.springframework.data.jdbc.repository.query.Query;

import java.util.List;
import java.util.UUID;

public interface SellerRepository extends BaseRepository<Seller, UUID>{
    @Query("SELECT COUNT(*) FROM sellers")
    Integer getCount();
    @Query("SELECT name AS seller_name,id FROM sellers")
    List<SellerDTO> getAll();
}
