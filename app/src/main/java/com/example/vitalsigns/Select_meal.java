package com.example.vitalsigns;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vitalsigns.meals_screen.Meal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Select_meal extends AppCompatActivity {

    TextView heading;

    Button add_meal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_meal);

        heading= findViewById(R.id.heading);
        add_meal = findViewById(R.id.add_new_meal);

        String passedArg = getIntent().getExtras().getString("arg");
        heading.setText(passedArg);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://vitalsignsproject-5f6d8-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference();

        ArrayList<MealSelectRowModal> meals = new ArrayList<>();

        if (passedArg.equals("Breakfast"))
        {
            meals.add(new MealSelectRowModal(passedArg,"Fried Egg","1 qty",92 ,"kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Plain Paratha","1 medium",160, "kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Aloo Paratha","1 medium",250, "kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Sandwich","1 medium",60, "kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Tea Cup with Milk and Sugar","1 medium",50, "kcal",false));

        }else if (passedArg.equals("Lunch")){

            meals.add(new MealSelectRowModal(passedArg,"Daal Mash","100 g",80 ,"kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Chana Dal","100 g",160 ,"kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Fish","250 g",350 ,"kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Grilled Chicken","1 plate",150 ,"kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Plain Roti","1",30 ,"kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Naan","1",45 ,"kcal",false));

        }
        else if (passedArg.equals("Dinner")){
            meals.add(new MealSelectRowModal(passedArg,"Daal Mash","100 g",80 ,"kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Chana Dal","100 g",160 ,"kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Fish","250 g",350 ,"kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Grilled Chicken","1 plate",150 ,"kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Plain Roti","1",30 ,"kcal",false));
            meals.add(new MealSelectRowModal(passedArg,"Naan","1",45 ,"kcal",false));

        }else if (passedArg.equals("Lunch_Snack")){

            meals.add(new MealSelectRowModal(passedArg,"Chicken Salad","1 plate",100 ,"kcal",false));

        }else if (passedArg.equals("Midnight_Snack")){

            meals.add(new MealSelectRowModal(passedArg,"Pistachios","1 plate",80 ,"kcal",false));

        }

      MealSelectRowAdapter adapter;
        RecyclerView rv;
        rv = findViewById(R.id.select_meal_rv);
        adapter = new MealSelectRowAdapter(getApplicationContext(),meals);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        add_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<MealSelectRowModal> selected = adapter.getSelected_items();
                for (int i=0 ; i<selected.size() ; i++){
                    myRef.child("TodayMeal").child("zeeshan50522").child(passedArg).push().setValue(selected.get(i));
                }
                Intent i = new Intent(Select_meal.this , Meal.class);
                startActivity(i);
            }
        });

    }
}