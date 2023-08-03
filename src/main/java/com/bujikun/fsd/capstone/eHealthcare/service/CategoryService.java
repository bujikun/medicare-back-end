package com.bujikun.fsd.capstone.eHealthcare.service;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.CategoryDTO;
import com.bujikun.fsd.capstone.eHealthcare.config.dto.SellerDTO;
import com.bujikun.fsd.capstone.eHealthcare.entity.Category;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.BaseException;
import com.bujikun.fsd.capstone.eHealthcare.repository.CategoryRepository;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService implements IBaseService<Category, UUID>{
    private final CategoryRepository categoryRepository;
    private final DateUtil dateUtil;

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> findById(UUID uuid) {
        return categoryRepository.findById(uuid);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    public Category create(CategoryDTO categoryDTO) {
        var category = CategoryDTO.toCategory(categoryDTO,  dateUtil);
        return categoryRepository.save(category);
    }

    @Override
    public boolean update(Category category) {
        return false;
    }

    @Override
    public void deleteById(UUID uuid) {

    }
    public List<CategoryDTO> getAll(){
        return categoryRepository.getAll();
    }
    @Override
    public Integer getCount() {
        return categoryRepository.getCount();
    }
}
