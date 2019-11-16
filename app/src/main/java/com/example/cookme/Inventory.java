package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class Inventory extends AppCompatActivity
{
    Button addButton;
    Button backButton;
    Button addUser;
    ListView list;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openAddItem();
            }
        });

        backButton =(Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBack();
            }
        });

        addUser = (Button) findViewById(R.id.newUser);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddUser();
            }
        });


    }

    public void openBack ()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openAddItem ()
    {
        Intent intent = new Intent(this, addItem.class);
        startActivity(intent);
    }

    public  void openAddUser()
    {
        Intent intent = new Intent(this, addUser.class);
        startActivity(intent);
    }
}
