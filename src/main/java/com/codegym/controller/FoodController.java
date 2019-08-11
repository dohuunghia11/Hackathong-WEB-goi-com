package com.codegym.controller;

import com.codegym.model.Food;
import com.codegym.model.Store;
import com.codegym.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("store")
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @ModelAttribute("store")
    public Store setupCart(){
        return new Store();
    }
    @GetMapping("gioithieu")
    public ModelAndView gioithieu(){
        ModelAndView modelAndView = new ModelAndView("/gioithieu");
        return modelAndView;
    }
    @GetMapping("sanpham")
    public ModelAndView sanpham() {
        ModelAndView modelAndView = new ModelAndView("/sp");
        return modelAndView;
    }
    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView listFoods( @RequestParam("s") Optional<String> s,@PageableDefault(size = 5) Pageable pageable){
        Page<Food> foods;
        if(s.isPresent()){
            foods = foodService.findAllByNameContaining(s.get(), pageable);
        } else {
            foods = foodService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/food/list");
        modelAndView.addObject("foods", foods);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/food/create");
        modelAndView.addObject("food", new Food());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveFood(@Validated @ModelAttribute("food") Food food, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/food/create");
            return modelAndView;
        }
        foodService.save(food);
        ModelAndView modelAndView = new ModelAndView("/food/create");
        modelAndView.addObject("food", new Food());
        modelAndView.addObject("message", "New food created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Food food = foodService.findById(id);
        if(food != null) {
            ModelAndView modelAndView = new ModelAndView("/food/edit");
            modelAndView.addObject("food", food);
            return modelAndView;

        }else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateFood(@Validated @ModelAttribute("food") Food food, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/food/edit");
            return modelAndView;
        }
        foodService.save(food);
        ModelAndView modelAndView = new ModelAndView("/food/edit");
        modelAndView.addObject("food", food);
        modelAndView.addObject("message", "Food updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Food food = foodService.findById(id);
        if(food != null) {
            ModelAndView modelAndView = new ModelAndView("/food/delete");
            modelAndView.addObject("food", food);
            return modelAndView;
        }else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/delete")
    public String deleteFood(@ModelAttribute("food") Food food){
        foodService.remove(food.getId());
        return "redirect:/food/list";
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewFood(@PathVariable Long id){
        Food food = foodService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/food/view");
        modelAndView.addObject("food", food);
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public ModelAndView addToStore(@PathVariable Long id, @ModelAttribute("store") Store store, @RequestParam("action") String action, Pageable pageable){
        ModelAndView modelAndView;
        Food food = foodService.findById(id);
        if (action.equals("show")){
            modelAndView = new ModelAndView("/store/show");
        } else {
            Page<Food> foods = foodService.findAll(pageable);
            modelAndView = new ModelAndView("/food/list","foods",foods);
        }
        store.addFood(food);
        return modelAndView;
    }

    @GetMapping("/remove/{id}")
    public ModelAndView removeFromStore(@PathVariable Long id, @ModelAttribute("store") Store store, @RequestParam("action") String action, Pageable pageable){
        ModelAndView modelAndView;
        Food food = foodService.findById(id);
        if (action.equals("show")){
            modelAndView = new ModelAndView("/store/show");
        } else {
            Page<Food> foods = foodService.findAll(pageable);
            modelAndView = new ModelAndView("/food/list","foods",foods);
        }
        store.removeFood(food);
        return modelAndView;
    }

}
