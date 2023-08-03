package com.bujikun.fsd.capstone.eHealthcare.service;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.CategoryDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.SellerDTO;
import com.bujikun.fsd.capstone.eHealthcare.entity.Category;
import com.bujikun.fsd.capstone.eHealthcare.entity.Seller;
import com.bujikun.fsd.capstone.eHealthcare.repository.CategoryRepository;
import com.bujikun.fsd.capstone.eHealthcare.repository.SellerRepository;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SellerService implements IBaseService<Seller, UUID>{
    private final SellerRepository sellerRepository;
    private final DateUtil dateUtil;

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public Optional<Seller> findById(UUID uuid) {
        return sellerRepository.findById(uuid);
    }

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }
    public Seller create(SellerDTO sellerDTO) {
        var seller = SellerDTO.toSeller(sellerDTO,  dateUtil);
        return sellerRepository.save(seller);
    }

    @Override
    public boolean update(Seller seller) {
        return false;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    public List<SellerDTO> getAll(){
        return sellerRepository.getAll();
    }
    @Override
    public Integer getCount() {
        return sellerRepository.getCount();
    }
}
