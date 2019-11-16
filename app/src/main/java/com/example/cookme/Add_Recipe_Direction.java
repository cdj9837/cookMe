package com.example.cookme;

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

public class Add_Recipe_Direction extends AppCompatActivity {

    EditText recipe_direction;
    FirebaseDatabase database;
    DatabaseReference ref;
    Recipe_Official recipe_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__recipe__direction);
        recipe_direction = (EditText)findViewById(R.id.recipe_direction_input);
        Button done_direction_button = (Button) findViewById(R.id.done_direction_button);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Testing_Recipe");
        recipe_test = new Recipe_Official();
        final String recipe_name = getIntent().getStringExtra("key");
        done_direction_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        getValues();
                        ref.child(recipe_name).child("Recipe Direction").setValue(recipe_test);
                        Toast.makeText(getApplicationContext(),"Recipe's Direction Added", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent i = new Intent(getApplicationContext(),Recipe_Main.class);
                startActivity(i);
            }
        });
    }
    private void getValues()
    {
        recipe_test.setRECIPE_DIRECTION(recipe_direction.getText().toString());
    }
}
