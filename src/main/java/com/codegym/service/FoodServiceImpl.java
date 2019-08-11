package com.codegym.service;

import com.codegym.model.Food;
import com.codegym.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Page<Food> findAll(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    @Override
    public Food findById(Long id) {
        return foodRepository.findOne(id);
    }

    @Override
    public void save(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void remove(Long id) {
        foodRepository.delete(id);
    }

    @Override
    public Page<Food> findAllByNameContaining(String s, Pageable pageable){
        return foodRepository.findAllByNameContaining(s,pageable);
    }

}
