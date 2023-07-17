package com.bujikun.fsd.capstone.eHealthcare.service;

import com.bujikun.fsd.capstone.eHealthcare.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T extends BaseEntity,U> {
    List<T> findAll();
    Optional<T> findById(U u);
    T save(T t);
    boolean update(T t);
    void deleteById(U u);
}
