package com.example.cookme;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.List;

public class Add_Ingredients extends AppCompatActivity {
    EditText ingredient_name;
    EditText ingredient_amount;
    EditText ingredient_unit;
    EditText ingredient_note;

    FirebaseDatabase database;
    DatabaseReference ref;
    Recipe_Official recipe_test;
    //ADDED THURSDAY
=======
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Add_Ingredients<ingredients> extends AppCompatActivity {
    /*EditText ingredient_name;
    EditText ingredient_amount;
    EditText ingredient_note;*/
    EditText ingredient_name;
    EditText ingredient_note;
    EditText ingredient_unit;
    EditText ingredient_amount;


    Ingredients ing_object;
    Recipe ingredient_add;
>>>>>>> master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__ingredients);

        ingredient_name = (EditText)findViewById(R.id.ingredient_name_edit);
        ingredient_amount = (EditText)findViewById(R.id.ingredient_amount_edit);
        ingredient_unit = (EditText)findViewById(R.id.ingredient_unit_edit);
        ingredient_note = (EditText)findViewById(R.id.ingredient_note_edit);

<<<<<<< HEAD
        Button done_ingredient_button = (Button) findViewById(R.id.done_ingredient_button);
        Button add_ingredient_button = (Button) findViewById(R.id.add_ingredient_button);

        final String recipe_name = getIntent().getStringExtra("key");

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Testing_Recipe");
        recipe_test = new Recipe_Official();
=======

        final Recipe recipe_2 = (Recipe)getIntent().getSerializableExtra("key");

        Button done_ingredient_button = (Button) findViewById(R.id.done_ingredient_button);
        Button add_ingredient_button = (Button) findViewById(R.id.add_ingredient_button);

        ingredient_add = new Recipe();

        final ArrayList<Ingredients> ingredients_temp = new ArrayList<>();

>>>>>>> master
        add_ingredient_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        getValues();
                        String current_ingredient = ingredient_name.getText().toString();
                        ref.child(recipe_name).child("Recipe's Ingredients").child(current_ingredient).setValue(recipe_test);
                        Toast.makeText(getApplicationContext(),"Ingredients Added", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
=======
                if(TextUtils.isEmpty(ingredient_name.getText().toString()) || TextUtils.isEmpty(ingredient_amount.getText().toString())
                        || TextUtils.isEmpty(ingredient_unit.getText().toString()) || TextUtils.isEmpty(ingredient_note.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Please fill in the blank", Toast.LENGTH_LONG).show();
                }
                else
                {
                    String ingredient_name2 = ingredient_name.getText().toString();
                    String ingredient_note2 = ingredient_note.getText().toString();
                    String ingredient_unit2 = ingredient_unit.getText().toString();
                    Double ingredient_amount2 = Double.parseDouble(ingredient_amount.getText().toString());
                    ing_object = new Ingredients(ingredient_name2,ingredient_note2, ingredient_unit2, ingredient_amount2);
                    ingredients_temp.add(ing_object);
                    Toast.makeText(getApplicationContext(),"Ingredients Added", Toast.LENGTH_LONG).show();
                }
>>>>>>> master
            }
        });
        done_ingredient_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Intent i = new Intent(getApplicationContext(), Add_Recipe_Direction.class);
                i.putExtra("key", recipe_name);
                startActivity(i);
=======
                if(TextUtils.isEmpty(ingredient_name.getText().toString()) || TextUtils.isEmpty(ingredient_amount.getText().toString())
                || TextUtils.isEmpty(ingredient_unit.getText().toString()) || TextUtils.isEmpty(ingredient_note.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Please fill in the blank", Toast.LENGTH_LONG).show();
                }
                else
                    {
                    recipe_2.setIngredients(ingredients_temp);
                    Intent i = new Intent(getApplicationContext(), Add_Recipe_Direction.class);
                    i.putExtra("key2", recipe_2);
                    startActivity(i);
                }
>>>>>>> master
            }
        });
    }
    private void getValues()
    {
<<<<<<< HEAD
        recipe_test.setINGREDIENT_NAME(ingredient_name.getText().toString());
        recipe_test.setINGREDIENT_AMOUNT(ingredient_amount.getText().toString());
        recipe_test.setINGREDIENT_UNIT(ingredient_unit.getText().toString());
        recipe_test.setINGREDIENT_NOTES(ingredient_note.getText().toString());
    }
}
=======
        ing_object.setName(ingredient_name.getText().toString());
        ing_object.setNotes(ingredient_note.getText().toString());
        ing_object.setUnit(ingredient_unit.getText().toString());
        ing_object.setAmount(Long.parseLong(ingredient_amount.getText().toString()));
    }
}
>>>>>>> master
