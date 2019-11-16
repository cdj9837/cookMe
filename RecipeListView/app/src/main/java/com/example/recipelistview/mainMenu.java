package com.example.recipelistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class mainMenu extends AppCompatActivity {

    Button inventory;
    Button recipe;
    Button grocery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
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
    }

    public void openInventory()
    {
        //Intent intent = new Intent(this, Inventory.class);
        //startActivity(intent);
    }

    public void openRecipe()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}