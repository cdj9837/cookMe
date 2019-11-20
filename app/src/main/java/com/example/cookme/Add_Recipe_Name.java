package com.example.cookme;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
=======
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
>>>>>>> master
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

<<<<<<< HEAD
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
=======
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
>>>>>>> master

public class Add_Recipe_Name extends AppCompatActivity {

    // Writing to Database
    EditText recipe_name;
    FirebaseDatabase database;
    DatabaseReference ref;

<<<<<<< HEAD
    Recipe_Official recipe_test;


=======
    //Recipe_Official recipe_test;
    Recipe recipe_add;
    Button done_recipe_name;
>>>>>>> master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__recipe__name);
        recipe_name = (EditText)findViewById(R.id.recipe_name_edit);
<<<<<<< HEAD
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
                        ref.child(current_recipe).child("Recipe Name").setValue(current_recipe);
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
=======
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

>>>>>>> master
}