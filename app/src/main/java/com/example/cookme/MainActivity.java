package com.example.cookme;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button inventory;
    Button recipe;
    Button grocery;

    Button logout;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference mDatabase;
    String userID;
    public static String groupID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button recipe_button = (Button) findViewById(R.id.recipe);
        recipe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Recipe_Main.class);
                startActivity(i);
            }
        });

        inventory = (Button) findViewById(R.id.inventory);
        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInventory();
            }
        });

        recipe = (Button) findViewById(R.id.recipe);
        recipe.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                openRecipe();
            }
        });

        grocery = (Button) findViewById(R.id.grocery);
        grocery.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                openGrocery();
            }
        });

        logout = (Button) findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });



        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            userID = firebaseUser.getUid();
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("groupID").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                groupID = String.valueOf(dataSnapshot.getValue()) ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        System.out.println(groupID);

    }

    public void openInventory()
    {
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }

    public void openRecipe()
    {
        Intent intent = new Intent(this, Recipe_Main.class);
        startActivity(intent);
    }

    public void logOut(){
        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(MainActivity.this);
        logoutDialog.setMessage("Do you want to Log out?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        firebaseAuth.signOut();
                        openLogin();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog logout = logoutDialog.create();
        logout.show();
    }

    public void openLogin(){
        Intent g = new Intent(this, Login.class);
        startActivity(g);
    }

    public void openGrocery(){
        Intent g = new Intent(this, Grocery.class);
        startActivity(g);
    }
}
