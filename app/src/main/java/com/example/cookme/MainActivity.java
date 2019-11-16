package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button inventory;
    Button recipe;
    Button grocery;

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
    }

    public void openInventory()
    {
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }
}
