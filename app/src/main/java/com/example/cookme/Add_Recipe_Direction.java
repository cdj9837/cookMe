package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Recipe_Direction extends AppCompatActivity {

    EditText recipe_direction;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Recipe recipe_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__recipe__direction);
        recipe_3 = (Recipe)getIntent().getSerializableExtra("key2");
        recipe_direction = (EditText)findViewById(R.id.recipe_direction_input);
        Button done_direction_button = (Button) findViewById(R.id.done_direction_button);
        done_direction_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(recipe_direction.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Please fill in the blank", Toast.LENGTH_LONG).show();
                }
                else {
                    recipe_3.setDirections(recipe_direction.getText().toString());
                    ref.child("Recipe").push().setValue(recipe_3);
                    Toast.makeText(getApplicationContext(), "Recipe's Direction Added", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), Recipe_Main.class);
                    startActivity(i);
                }
            }
        });
    }
}
