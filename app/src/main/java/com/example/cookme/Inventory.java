package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Inventory extends AppCompatActivity
{
    private static final String TAG = "Inventory";
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    Button addButton;
    Button backButton;
    Button addUser;
    ListView inventoryLV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        addButton = (Button) findViewById(R.id.addItem);
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

        addUser = (Button) findViewById(R.id.addUser);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddUser();
            }
        });

        inventoryLV = (ListView) findViewById(R.id.itemList);
        final ArrayList<Ingredients> ingredientList = new ArrayList<>();

        //TODO This .child("1") is current group. Will need to be passed in from another activity or gotten from accessing currently logged in UID and getting it's group number.
        mRootRef.child("Inventory").child("1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot inventorySnapShot : dataSnapshot.getChildren()) {
                        Ingredients r = inventorySnapShot.getValue(Ingredients.class);
                        ingredientList.add(r);
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "onCancelled: ", databaseError.toException() );
            }
        });

        IngredientsListAdapter adapter = new IngredientsListAdapter(this,R.layout.ingredient_list_layout,ingredientList);
        inventoryLV.setAdapter(adapter);

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
