package com.example.vitalsigns.meals_screen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.vitalsigns.CommonFunction;
import com.example.vitalsigns.MealSelectRowModal;
import com.example.vitalsigns.MealToCalMap;
import com.example.vitalsigns.R;
import com.example.vitalsigns.Select_meal;
import com.example.vitalsigns.home;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Meal extends AppCompatActivity {

    ImageView back , bfast , lunch, dinner , lunch_snack , midnight_snack;

    ArrayList <MealToCalMap> meals_rcvd = new ArrayList<MealToCalMap>();
    int one_meal_calories=0;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://vitalsignsproject-5f6d8-default-rtdb.firebaseio.com/");
    DatabaseReference Calories = database.getReference("TodayCalories");


    Today_Meals today_meals = new Today_Meals();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        ArrayList<MealSelectRowModal> passedArg = (ArrayList<MealSelectRowModal>) getIntent().getSerializableExtra("list");

//        if (passedArg!=null)
//        {
//            for (int i=0; i<passedArg.size(); i++)
//            {
//                one_meal_calories += passedArg.get(i).calories;
//                meals_rcvd.add(new MealToCalMap(passedArg.get(i).title , passedArg.get(i).calories));
//            }
//            today_meals.setMeal(passedArg.get(0).category , meals_rcvd , one_meal_calories );
//        }

        back = findViewById(R.id.meal_back);
        bfast = findViewById(R.id.breakfast);

        lunch = findViewById(R.id.lunch);

        dinner = findViewById(R.id.dinner);

        lunch_snack = findViewById(R.id.lunch_snack);

        midnight_snack = findViewById(R.id.dinner_snack);

        bfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Meal.this , Select_meal.class);
                i.putExtra("arg","Breakfast");
                startActivity(i);
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Meal.this , Select_meal.class);
                i.putExtra("arg","Lunch");
                startActivity(i);
            }
        });

        dinner.setOnClickListener(v -> {
            Intent i = new Intent (Meal.this , Select_meal.class);
            i.putExtra("arg","Dinner");
            startActivity(i);
        });

        lunch_snack.setOnClickListener(v -> {
            Intent i = new Intent (Meal.this , Select_meal.class);
            i.putExtra("arg","Lunch_Snack");
            startActivity(i);
        });

        midnight_snack.setOnClickListener(v -> {
            Intent i = new Intent (Meal.this , Select_meal.class);
            i.putExtra("arg","Midnight_Snack");
            startActivity(i);
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calories.child(CommonFunction.userLogin).setValue(CommonFunction.totalCalories);
                Intent i = new Intent(Meal.this , home.class );
                startActivity(i);
            }
        });



        ViewPager viewpager;
        TabLayout tabLayout;

        tabLayout= findViewById(R.id.meals_tabLayout);

        viewpager= findViewById(R.id.meal_Viewpager);

        Meals_ViewPager_Adapter meals_viewPager_adapter;

        meals_viewPager_adapter =new Meals_ViewPager_Adapter(getSupportFragmentManager());

        meals_viewPager_adapter.addFragment(today_meals,"Today");
        meals_viewPager_adapter.addFragment(new History_Meals(),"History");
        viewpager.setAdapter(meals_viewPager_adapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Calories.child(CommonFunction.userLogin).setValue(CommonFunction.totalCalories);
    }
}