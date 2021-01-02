package com.example.vitalsigns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterTodayMeals extends BaseAdapter {

    Context context;
    public ArrayList<MealToCalMap> meals;
    LayoutInflater inflter;

    public CustomAdapterTodayMeals(Context applicationContext, ArrayList<MealToCalMap> _meals) {
        this.context = context;
        this.meals = _meals;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return meals.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.simple_meal_map_view, null);


        TextView name = (TextView) view.findViewById(R.id.name_v_meal);
        TextView cal = (TextView) view.findViewById(R.id.cal_v_meal);

        name.setText(meals.get(i).name);
        cal.setText(String.valueOf(meals.get(i).cal));
        return view;
    }
}
