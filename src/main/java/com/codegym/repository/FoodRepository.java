package com.codegym.repository;

import com.codegym.model.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FoodRepository extends PagingAndSortingRepository<Food,Long> {
    Page<Food> findAllByNameContaining(String s, Pageable pageable);
}
