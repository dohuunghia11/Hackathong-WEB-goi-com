package com.codegym.service;

import com.codegym.model.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodService {
    Page<Food> findAll(Pageable pageable);

    Food findById(Long id);

    void save(Food food);

    void remove(Long id);

    Page<Food> findAllByNameContaining(String s, Pageable pageable);
}
