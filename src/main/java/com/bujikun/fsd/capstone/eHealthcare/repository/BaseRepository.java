package com.bujikun.fsd.capstone.eHealthcare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T,U> extends PagingAndSortingRepository<T,U>, CrudRepository<T,U> {
    List<T> findAll();
}
