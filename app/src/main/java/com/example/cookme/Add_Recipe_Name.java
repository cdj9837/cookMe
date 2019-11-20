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

public class Add_Recipe_Name extends AppCompatActivity {

    // Writing to Database
    EditText recipe_name;
    FirebaseDatabase database;
    DatabaseReference ref;

    //Recipe_Official recipe_test;
    Recipe recipe_add;
    Button done_recipe_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__recipe__name);
        recipe_name = (EditText)findViewById(R.id.recipe_name_edit);
        recipe_add = new Recipe();
        done_recipe_name  = (Button)findViewById(R.id.add_recipe_name_button);

        done_recipe_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(recipe_name.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Please fill in the blank", Toast.LENGTH_LONG).show();
                }
                else {
                    getValues();
                    Intent i = new Intent(getApplicationContext(), Add_Ingredients.class);
                    i.putExtra("key", recipe_add);
                    Toast.makeText(getApplicationContext(),"Recipe's Name", Toast.LENGTH_LONG).show();
                    startActivity(i);
                }
            }
        });

    }
    private void getValues()
    {
        recipe_add.setRecipeName(recipe_name.getText().toString());
    }

}