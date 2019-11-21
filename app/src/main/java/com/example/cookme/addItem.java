package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class addItem extends AppCompatActivity {
    private static final String TAG = "addItem";

    Button doneButton;
    Button backButton;
    EditText name,amount, unit;
    String Name, Amount, Unit;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        String groupId = MainActivity.groupID;
        final ArrayList<Ingredients> ingredientList = new ArrayList<>();
        final IngredientsListAdapter adapter = new IngredientsListAdapter(this,R.layout.ingredient_list_layout,ingredientList);

        mRootRef.child("Inventory").child(groupId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot inventorySnapShot : dataSnapshot.getChildren()) {
                        Ingredients r = inventorySnapShot.getValue(Ingredients.class);
                        ingredientList.add(r);
                    }
                    adapter.notifyDataSetChanged();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "onCancelled: ", databaseError.toException() );
            }
        });

        doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivity2(ingredientList);
            }
        });

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

    }

    public void openActivity1 ()
    {
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }

    public void openActivity2 (final ArrayList<Ingredients> ingredientList)
    {
        name = (EditText)findViewById(R.id.itemNameEdit);
        amount =(EditText)findViewById(R.id.itemAmountEdit);
        unit = (EditText)findViewById(R.id.itemUnitEdit);

        Name = name.getText().toString();
        Amount = amount.getText().toString();
        Unit = unit.getText().toString();

        long LAmount = Long.parseLong(Amount);
        //Ingredient take in: String name, String unit, long amount
        Ingredients adding = new Ingredients(Name, Unit, LAmount);




        Intent intent = new Intent(this, addItem.class);
        startActivity(intent);
    }
}