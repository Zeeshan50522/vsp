package com.example.vitalsigns.meals_screen;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.vitalsigns.CommonFunction;
import com.example.vitalsigns.MealSelectRowModal;
import com.example.vitalsigns.MealToCalMap;
import com.example.vitalsigns.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Today_Meals extends Fragment {

    ArrayList<MealRowModal> meals = new ArrayList<>();

    MealsAdapter adapter;

    RecyclerView rv;
    String breakfastPic = getURLForResource(R.drawable.breakfast);
    String lunchPic = getURLForResource(R.drawable.lunch);
    String dinnerPic = getURLForResource(R.drawable.dinner);
    String lunchSnack = getURLForResource(R.drawable.lunch_snack);
    String dinnerSnack = getURLForResource(R.drawable.dinner_snack);

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://vitalsignsproject-5f6d8-default-rtdb.firebaseio.com/");
    DatabaseReference Calories = database.getReference("TodayCalories");
    DatabaseReference myRef = database.getReference("TodayMeal");
    public Today_Meals(){

    }

//    public void setMeal (String category , ArrayList <MealToCalMap> meals_data , int calories) {
//
//        if (category.equals("Breakfast"))
//        {
//            Log.d("tag" , "Data added");
//            meals.add(new MealRowModal(breakfastPic , "Breakfast", meals_data , calories));
//
//        }else if (category.equals("Lunch"))
//        {
//            meals.add(new MealRowModal(lunchPic , "Lunch", meals_data , calories));
//
//        }
//        else if (category.equals("Dinner"))
//        {
//            meals.add(new MealRowModal(dinnerPic , "Dinner", meals_data , calories));
//
//        }else if (category.equals("Lunch Snack"))
//        {
//            meals.add(new MealRowModal(lunchSnack , "Lunch Snack", meals_data , calories));
//
//        }else if (category.equals("Midnight Snack"))
//        {
//            meals.add(new MealRowModal(dinnerSnack , "Lunch Midnight", meals_data , calories));
//
//        }
//
//
//
//    }

    public String getURLForResource (int resourceId) {
        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
        return Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/" +resourceId).toString();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.today_meals_pager,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rv = view.findViewById(R.id.today_meals_rv);
        adapter = new MealsAdapter(getContext(), meals);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        myRef.child("zeeshan50522").child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<MealToCalMap> items = new ArrayList<>();
                    int total_cal=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MealSelectRowModal mealSelectRowModal = snapshot.getValue(MealSelectRowModal.class);
                        items.add(new MealToCalMap(mealSelectRowModal.getTitle(),mealSelectRowModal.getCalories()));
                        total_cal = total_cal + mealSelectRowModal.calories;
                        CommonFunction.totalCalories =CommonFunction.totalCalories + mealSelectRowModal.calories;
                }
                meals.add(new MealRowModal(breakfastPic , "Breakfast", items , total_cal));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef.child("zeeshan50522").child("Lunch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<MealToCalMap> items = new ArrayList<>();
                int total_cal=0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MealSelectRowModal mealSelectRowModal = snapshot.getValue(MealSelectRowModal.class);
                    items.add(new MealToCalMap(mealSelectRowModal.getTitle(),mealSelectRowModal.getCalories()));
                    total_cal = total_cal + mealSelectRowModal.calories;
                    CommonFunction.totalCalories =CommonFunction.totalCalories + mealSelectRowModal.calories;
                }
                meals.add(new MealRowModal(lunchPic , "Lunch", items , total_cal));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef.child("zeeshan50522").child("Dinner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<MealToCalMap> items = new ArrayList<>();
                int total_cal=0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MealSelectRowModal mealSelectRowModal = snapshot.getValue(MealSelectRowModal.class);
                    items.add(new MealToCalMap(mealSelectRowModal.getTitle(),mealSelectRowModal.getCalories()));
                    total_cal = total_cal + mealSelectRowModal.calories;
                    CommonFunction.totalCalories =CommonFunction.totalCalories + mealSelectRowModal.calories;
                }
                meals.add(new MealRowModal(dinnerPic , "Dinner", items , total_cal));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        myRef.child("zeeshan50522").child("Lunch_Snack").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<MealToCalMap> items = new ArrayList<>();
                int total_cal=0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MealSelectRowModal mealSelectRowModal = snapshot.getValue(MealSelectRowModal.class);
                    items.add(new MealToCalMap(mealSelectRowModal.getTitle(),mealSelectRowModal.getCalories()));
                    total_cal = total_cal + mealSelectRowModal.calories;
                    CommonFunction.totalCalories =CommonFunction.totalCalories + mealSelectRowModal.calories;
                }
                meals.add(new MealRowModal(lunchSnack , "Lunch_Snack", items , total_cal));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        myRef.child("zeeshan50522").child("Midnight_Snack").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<MealToCalMap> items = new ArrayList<>();
                int total_cal=0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MealSelectRowModal mealSelectRowModal = snapshot.getValue(MealSelectRowModal.class);
                    items.add(new MealToCalMap(mealSelectRowModal.getTitle(),mealSelectRowModal.getCalories()));
                    total_cal = total_cal + mealSelectRowModal.calories;
                    CommonFunction.totalCalories =CommonFunction.totalCalories + mealSelectRowModal.calories;
                }
                meals.add(new MealRowModal(dinnerSnack , "Midnight_Snack", items , total_cal));
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
