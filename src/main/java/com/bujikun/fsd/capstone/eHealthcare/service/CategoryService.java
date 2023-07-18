package com.bujikun.fsd.capstone.eHealthcare.service;

import com.bujikun.fsd.capstone.eHealthcare.entity.Category;
import com.bujikun.fsd.capstone.eHealthcare.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
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

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public boolean update(Category category) {
        return false;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public Integer getCount() {
        return categoryRepository.getCount();
    }
}
