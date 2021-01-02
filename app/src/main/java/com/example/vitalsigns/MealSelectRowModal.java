package com.example.vitalsigns;

import java.io.Serializable;

public class MealSelectRowModal implements Serializable {

    public  String title , quantity , unit , category;
    public int calories;
    public  boolean check;

    public MealSelectRowModal(){}

    public MealSelectRowModal (String _category ,String _title , String _qty , int _calories, String _unit, boolean _check) {

        category = _category;
        title = _title;
        quantity = _qty;
        calories = _calories;
        unit = _unit;
        check = _check;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getQuantity() {
        return quantity;
    }

    public int getCalories() {
        return calories;
    }

    public String getUnit() {
        return unit;
    }

    public boolean getCheck() {
        return check;
    }
}
