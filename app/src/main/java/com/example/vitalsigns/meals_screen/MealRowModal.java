package com.example.vitalsigns.meals_screen;

import com.example.vitalsigns.MealToCalMap;

import java.util.ArrayList;

public class MealRowModal {

        public  String pic ,title ;
        public  int calories ;
        public ArrayList<MealToCalMap> items ;

        MealRowModal(String _pic , String _title , ArrayList<MealToCalMap> _items , int _calories) {

            pic = _pic;
            title = _title;
            items = _items;
            calories =  _calories;

        }


    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setItems(ArrayList<MealToCalMap> main_item) {
        this.items = main_item;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }


    public String getPic() {
        return pic;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList <MealToCalMap> getItems() {
        return items;
    }

    public int getCalories() {
        return calories;
    }
}
