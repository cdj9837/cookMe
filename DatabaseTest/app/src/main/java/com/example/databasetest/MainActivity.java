package com.example.databasetest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    TextView mRecipeNameTextView;
    Button mRecipe1Button;
    Button mRecipe2Button;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mtestRef = mRootRef.child("TestString");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipeNameTextView = (TextView)findViewById(R.id.recipeNameText);
        mRecipe1Button = (Button)findViewById(R.id.recipe1Button);
        mRecipe2Button = (Button) findViewById(R.id.recipe2Button);
    }

    @Override
    protected void onStart(){
        super.onStart();

        mtestRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mRecipeNameTextView.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRecipe2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtestRef.setValue("Recipe 2");
            }
        });

        mRecipe1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtestRef.setValue("Recipe 1");
            }
        });
    }
}
