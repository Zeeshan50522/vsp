package com.example.vitalsigns.meals_screen;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vitalsigns.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<HistoryRowModal> hist_meals;

    public HistoryAdapter(Context context, ArrayList<HistoryRowModal> arrayList) {
        this.context = context;
        this.hist_meals = arrayList;
    }

    @NonNull
    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_row, parent, false);
        return new HistoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyViewHolder holder, int position) {

        HistoryRowModal history_Data = hist_meals.get(position);

        ImageView profile_pic= holder.profile;
        Glide.with(context).load(history_Data.getPic()).into(profile_pic);


        TextView day_title= holder.day_title;
        day_title.setText(history_Data.getDay_title());

        TextView calories= holder.calories;
        calories.setText(history_Data.getCalorie_count());

    }

    @Override
    public int getItemCount() {
        return hist_meals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView profile;
        public TextView day_title, calories;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.hist_prof_pic);
            day_title = itemView.findViewById(R.id.day_title);
            calories = itemView.findViewById(R.id.calory_count);
        }
    }
}