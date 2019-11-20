package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class Recipe_Main extends AppCompatActivity {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        final ArrayList<Recipe> recipe_list = new ArrayList<>();
        /*recipe_list.add("Fries");*/
       ref.child("Recipe").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for(DataSnapshot recipeSnapshot : dataSnapshot.getChildren()){
                        Recipe r = recipeSnapshot.getValue(Recipe.class);
                        recipe_list.add(r);
                        //Toast.makeText(getApplicationContext(),"Recipe added to List", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        setContentView(R.layout.activity_recipe__main);
        Button add_recipe_button = (Button) findViewById(R.id.add_button);
        ListView recipe_list_view = (ListView) findViewById(R.id.recipe_listview);
        add_recipe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Add_Recipe_Name.class);
                startActivity(i);
            }
        });
        Listview_Adapter adapter = new Listview_Adapter(this, R.layout.main_recipe_layout, recipe_list);
        recipe_list_view.setAdapter(adapter);

    }
}
