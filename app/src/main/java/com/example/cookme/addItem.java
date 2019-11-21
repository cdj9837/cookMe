package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class addItem extends AppCompatActivity {
    private static final String TAG = "addItem";

    Button doneButton;
    Button backButton;
    EditText name,amount, unit;
    String Name, Amount, Unit;
    String groupId = MainActivity.groupID;

    DatabaseReference mRootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        name = (EditText)findViewById(R.id.itemNameEdit);
        amount =(EditText)findViewById(R.id.itemAmountEdit);
        unit = (EditText)findViewById(R.id.itemUnitEdit);
        doneButton = (Button) findViewById(R.id.doneButton);
        backButton = (Button) findViewById(R.id.backButton);

        final String groupId = MainActivity.groupID;
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

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRootRef.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        if(TextUtils.isEmpty(name.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(), "Please fill in the blank", Toast.LENGTH_LONG).show();
                        }
                        else if(TextUtils.isEmpty(amount.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(), "Please fill in the blank", Toast.LENGTH_LONG).show();
                        }
                        else
                        {

                            Name = name.getText().toString();
                            Amount = amount.getText().toString();
                            Unit = unit.getText().toString();
                            long LAmount = Long.parseLong(Amount);

                            try {
                                //recipe_3.setDirections(recipe_direction.getText().toString());
                                Ingredients newIngr = new Ingredients(Name, Unit, LAmount);
                                int key=-1;
                                String name = newIngr.getName().toLowerCase();
                                for(int i = 0; i<ingredientList.size(); i++)
                                {
                                    if(name.equals(ingredientList.get(i).getName().toLowerCase())) {
                                        key = i;
                                        break;
                                    }
                                }



                                if(key!=-1)
                                {
                                    double currAmount = ingredientList.get(key).getAmount();
                                    double newAmount = currAmount + LAmount;
                                    //update data syntax:
                                    //mDatabase.child("users").child(userId).child("username").setValue(name);
                                    ingredientList.get(key).setAmount((Long)newAmount);

                                    mRootRef.child("Inventory").child(groupId).setValue(ingredientList);
                                }

                                else
                                {
                                    mRootRef.child("Inventory").child(groupId).push().setValue(newIngr);
                                    Toast.makeText(getApplicationContext(), "Added new item", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), Inventory.class);
                                    startActivity(i);
                                }
                            }
                            catch (Exception e)
                            {

                            }
                        }
                        openActivity2();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {

                    }
                });
            }
        });


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

    public void openActivity2 ()
    {
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }
}