package com.example.vitalsigns.meals_screen;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vitalsigns.CustomAdapterTodayMeals;
import com.example.vitalsigns.MealToCalMap;
import com.example.vitalsigns.R;

import java.util.ArrayList;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<MealRowModal> meals;

    public MealsAdapter(Context context, ArrayList<MealRowModal> arrayList) {
        this.context = context;
        this.meals = arrayList;
    }

    @NonNull
    @Override
    public MealsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_row, parent, false);
        return new MealsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsAdapter.MyViewHolder holder, int position) {


        LinearLayout linearLayoutToAdd;


        MealRowModal meals_Data = meals.get(position);

        ImageView profile_pic= holder.profile;
        Glide.with(context).load(meals_Data.getPic()).into(profile_pic);

        TextView meal_title= holder.title;
        meal_title.setText(meals_Data.getTitle());

        ListView lv = holder.meal_listView;

        CustomAdapterTodayMeals adapter;

        adapter = new CustomAdapterTodayMeals(context,meals_Data.items);

        lv.setAdapter(adapter);

        TextView main= holder.main;

        TextView calories= holder.calories;
        calories.setText(Integer.toString(meals_Data.getCalories()));

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView profile;
        public TextView title , main , second , third , calories;

        public  ListView meal_listView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.meals_prof_pic);

            title = itemView.findViewById(R.id.meals_title);
            meal_listView = itemView.findViewById(R.id.meals_lv);
            calories = itemView.findViewById(R.id.total_calories);

        }
    }
}