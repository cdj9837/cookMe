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

public class Add_Recipe_Direction extends AppCompatActivity {

    EditText recipe_direction;
<<<<<<< HEAD
    FirebaseDatabase database;
    DatabaseReference ref;
    Recipe_Official recipe_test;
=======
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Recipe recipe_3;
>>>>>>> master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__recipe__direction);
<<<<<<< HEAD
        recipe_direction = (EditText)findViewById(R.id.recipe_direction_input);
        Button done_direction_button = (Button) findViewById(R.id.done_direction_button);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Testing_Recipe");
        recipe_test = new Recipe_Official();
        final String recipe_name = getIntent().getStringExtra("key");
=======
        recipe_3 = (Recipe)getIntent().getSerializableExtra("key2");
        recipe_direction = (EditText)findViewById(R.id.recipe_direction_input);
        Button done_direction_button = (Button) findViewById(R.id.done_direction_button);
>>>>>>> master
        done_direction_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        getValues();
                        String current_direction = recipe_direction.getText().toString();
                        ref.child(recipe_name).child("Recipe Direction").setValue(current_direction);
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
=======
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
>>>>>>> master
