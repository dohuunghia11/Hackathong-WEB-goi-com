package com.codegym.model;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<Food,Integer> foods = new HashMap<>();

    public Store() {
    }

    public Store(Map<Food,Integer> foods) {
        this.foods = foods;
    }

    public Map<Food,Integer> getFoods() {
        return foods;
    }

    private boolean checkItemInStore(Food food){
        for (Map.Entry<Food, Integer> entry : foods.entrySet()) {
            if(entry.getKey().getId().equals(food.getId())){
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Food, Integer> selectItemInStore(Food food){
        for (Map.Entry<Food, Integer> entry : foods.entrySet()) {
            if(entry.getKey().getId().equals(food.getId())){
                return entry;
            }
        }
        return null;
    }

    public void addFood(Food food){
        if (!checkItemInStore(food)){
            foods.put(food,1);
        } else {
            Map.Entry<Food, Integer> itemEntry = selectItemInStore(food);
            Integer newQuantity = itemEntry.getValue() + 1;
            foods.replace(itemEntry.getKey(),newQuantity);
        }
    }

    public void removeFood(Food food){
        if (checkItemInStore(food)){
            Map.Entry<Food, Integer> itemEntry = selectItemInStore(food);
            if (itemEntry.getValue() > 1){
                Integer newQuantity = itemEntry.getValue() - 1;
                foods.replace(itemEntry.getKey(),newQuantity);
            } else {
                foods.remove(itemEntry.getKey());
            }
        }
    }

    public Integer countFoodQuantity(){
        Integer foodQuantity = 0;
        for (Map.Entry<Food, Integer> entry : foods.entrySet()) {
            foodQuantity+= entry.getValue();
        }
        return foodQuantity;
    }

    public Integer countItemQuantity(){
        return foods.size();
    }

    public Float countTotalPayment(){
        float payment = 0;
        for (Map.Entry<Food, Integer> entry : foods.entrySet()) {
            payment += entry.getKey().getPrice() * (float) entry.getValue();
        }
        return payment;
    }

    @Override
    public String toString() {
        return "Store" +
                ", foods=" + foods +
                '}';
    }
}
