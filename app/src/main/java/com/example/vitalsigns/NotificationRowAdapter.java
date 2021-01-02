package com.example.vitalsigns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vitalsigns.meals_screen.MealRowModal;

import java.util.ArrayList;

public class NotificationRowAdapter extends RecyclerView.Adapter<NotificationRowAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<NotificationRowModal> notify_data;

    public NotificationRowAdapter(Context context, ArrayList<NotificationRowModal> arrayList) {
        this.context = context;
        this.notify_data = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notify_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {




        NotificationRowModal meals_Data = notify_data.get(position);

        TextView meal_title= holder.data;
        meal_title.setText(meals_Data.content);

        TextView priority_notify= holder.priority;
        priority_notify.setText(meals_Data.priority);
    }

    @Override
    public int getItemCount() {
        return notify_data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView data, priority;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            data = itemView.findViewById(R.id.content_notify);
            priority = itemView.findViewById(R.id.priority_notify);

        }
    }
}
