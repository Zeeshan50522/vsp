package com.example.vitalsigns.meals_screen;

public class HistoryRowModal {

    public  String pic , day_title , calorie_count;

    HistoryRowModal (String _pic , String _dayTitle , String _calories){

        pic = _pic;
        day_title = _dayTitle;
        calorie_count = _calories;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setDay_title(String day_title) {
        this.day_title = day_title;
    }

    public void setCalorie_count(String calorie_count) {
        this.calorie_count = calorie_count;
    }

    public String getPic() {
        return pic;
    }

    public String getDay_title() {
        return day_title;
    }

    public String getCalorie_count() {
        return calorie_count;
    }
}
