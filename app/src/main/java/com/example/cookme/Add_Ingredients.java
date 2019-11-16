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

public class Add_Ingredients extends AppCompatActivity {
    EditText ingredient_name;
    EditText ingredient_amount;
    EditText ingredient_note;

    FirebaseDatabase database;
    DatabaseReference ref;
    Recipe_Official recipe_test;
    //ADDED THURSDAY
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__ingredients);

        ingredient_name = (EditText)findViewById(R.id.ingredient_name_edit);
        ingredient_amount = (EditText)findViewById(R.id.ingredient_amount_edit);
        ingredient_note = (EditText)findViewById(R.id.ingredient_note_edit);

        Button done_ingredient_button = (Button) findViewById(R.id.done_ingredient_button);
        Button add_ingredient_button = (Button) findViewById(R.id.add_ingredient_button);

        final String recipe_name = getIntent().getStringExtra("key");

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Testing_Recipe");
        recipe_test = new Recipe_Official();
        add_ingredient_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
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
            }
        });
        done_ingredient_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Add_Recipe_Direction.class);
                i.putExtra("key", recipe_name);
                startActivity(i);
            }
        });
    }
    private void getValues()
    {
        recipe_test.setINGREDIENT_NAME(ingredient_name.getText().toString());
        recipe_test.setINGREDIENT_AMOUNT(ingredient_amount.getText().toString());
        recipe_test.setINGREDIENT_NOTES(ingredient_note.getText().toString());
    }
}
