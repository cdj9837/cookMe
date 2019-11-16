package com.example.cookme;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button inventory;
    Button recipe;
    Button grocery;

    Button logout;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        logout = (Button) findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
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
}
