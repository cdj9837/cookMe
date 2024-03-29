package com.random.temp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Add_Recipe_Name extends AppCompatActivity {

    // Writing to Database
    EditText recipe_name;
    FirebaseDatabase database;
    DatabaseReference ref;

    Recipe_Official recipe_test;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__recipe__name);
        recipe_name = (EditText)findViewById(R.id.recipe_name_edit);
        Button done_recipe_name = (Button) findViewById(R.id.add_recipe_name_button);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Testing_Recipe");

        recipe_test = new Recipe_Official();
        done_recipe_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String current_recipe = recipe_name.getText().toString();
                        getValues();
                        ref.child(current_recipe).child("Recipe Name").setValue(recipe_test);
                        Toast.makeText(getApplicationContext(),"Recipe's Name Added", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent i = new Intent(getApplicationContext(), Add_Ingredients.class);
                String pass_recipe_name = recipe_name.getText().toString();
                i.putExtra("key", pass_recipe_name);
                startActivity(i);
            }
        });
    }
    private void getValues()
    {
        recipe_test.setRECIPE_NAME(recipe_name.getText().toString());
    }
}



