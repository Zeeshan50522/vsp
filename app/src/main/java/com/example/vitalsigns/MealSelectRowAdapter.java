package com.example.vitalsigns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MealSelectRowAdapter extends RecyclerView.Adapter<MealSelectRowAdapter.MyViewHolder>  {

    private Context context;
    private ArrayList<MealSelectRowModal> meal_select_list;

    public  ArrayList<MealSelectRowModal> selected_items;

    public MealSelectRowAdapter(Context context, ArrayList<MealSelectRowModal> arrayList) {
        this.context = context;
        this.meal_select_list = arrayList;
        selected_items = new ArrayList<MealSelectRowModal>();
    }

    @NonNull
    @Override
    public MealSelectRowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.select_meal_row, parent, false);
        return new MealSelectRowAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealSelectRowAdapter.MyViewHolder holder, int position) {

        MealSelectRowModal history_Data = meal_select_list.get(position);


        TextView meal_title= holder.meal_title;
        meal_title.setText(history_Data.getTitle());

        TextView meal_qty= holder.meal_qty;
        meal_qty.setText(history_Data.getQuantity());

        TextView meal_calories= holder.meal_calories;
        meal_calories.setText(String.valueOf(history_Data.getCalories()));

        TextView meal_unit= holder.meal_calUnit;
        meal_unit.setText(history_Data.getUnit());

        CheckBox checkBox = holder.meal_selected;
        checkBox.setChecked(history_Data.getCheck());

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
            {
                selected_items.add(history_Data);
            }
        });


    }

    public ArrayList<MealSelectRowModal> getSelected_items() {
        return selected_items;
    }

    @Override
    public int getItemCount() {
        return meal_select_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView meal_title , meal_qty , meal_calories , meal_calUnit;
        public CheckBox meal_selected;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            meal_title = itemView.findViewById(R.id.title);
            meal_qty = itemView.findViewById(R.id.qty);
            meal_calories = itemView.findViewById(R.id.kcal);
            meal_calUnit = itemView.findViewById(R.id.kcal_unit);

            meal_selected = itemView.findViewById(R.id.check);

        }
    }

}
