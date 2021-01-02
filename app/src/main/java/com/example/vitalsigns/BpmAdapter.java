package com.example.vitalsigns;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BpmAdapter extends RecyclerView.Adapter <BpmAdapter.BpmAdapterHolder> {
    private List<Bpm_model> mylist;
    public BpmAdapter(List<Bpm_model> list)
    {
        this.mylist=list;
    }

    @NonNull
    @Override
    public BpmAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater=LayoutInflater.from(parent.getContext());
        View myOwnView= myInflater.inflate(R.layout.heart_layout,parent,false);

        return new BpmAdapterHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(@NonNull BpmAdapterHolder holder, int position) {
        holder.bpm_value.setText(String.valueOf(mylist.get(position).bp_value));
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        holder.start_time.setText(df.format(mylist.get(position).start_time));
      //  holder.end_time.setText(df.format(mylist.get(position).end_time));
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class BpmAdapterHolder extends RecyclerView.ViewHolder {
        TextView bpm_value;
        TextView start_time;
        TextView end_time;
        public BpmAdapterHolder(@NonNull View itemView) {
            super(itemView);
            bpm_value = itemView.findViewById(R.id.bpm_value);
            start_time = itemView.findViewById(R.id.start_time);

        }
    }

}
