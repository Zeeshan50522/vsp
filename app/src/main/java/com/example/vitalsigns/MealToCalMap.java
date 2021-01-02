package com.example.vitalsigns;

public class MealToCalMap {

    public String name;
    public  int cal;

    public  MealToCalMap (String a , int b){
        name = a;
        cal = b;
    }

    public void setMapping (String a , int b){
        this.name =a;
        this.cal =b;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }

    public String getName() {
        return name;
    }

    public int getCal() {
        return cal;
    }

}
