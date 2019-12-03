package com.example.cookme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Grocery extends AppCompatActivity {
    private static final String TAG = "Grocery";
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    Button addButton;
    Button backButton;
    ListView inventoryLV;
    //String Uid;
    String groupId;
    final ArrayList<Ingredients> ingredientList = new ArrayList<>();
    int key=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        inventoryLV = (ListView) findViewById(R.id.itemList);


        groupId = MainActivity.groupID;
        final IngredientsListAdapter adapter = new IngredientsListAdapter(this,R.layout.ingredient_list_layout,ingredientList);

        //TODO This .child("1") is current group. Will need to be passed in from another activity or gotten from accessing currently logged in UID and getting it's group number.
        mRootRef.child("Grocery").child(groupId).addListenerForSingleValueEvent(new ValueEventListener() {
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


        inventoryLV.setAdapter(adapter);


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


        inventoryLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = "testing";
                String name = ingredientList.get(position).getName();;

                for(int i=0; i<ingredientList.size(); i++)
                {
                    if(ingredientList.get(i).getName() == name)
                    {
                        key = i;
                        Intent intent = new Intent(Grocery.this, GroceryInfo.class);
                        intent.putExtra("IngredientObj", ingredientList.get(i));
                        startActivityForResult(intent,1);
                        break;
                    }
                }
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != 1) {
            Bundle extract = data.getExtras();

            Ingredients ingred = (Ingredients) extract.getSerializable("IngrOBJ");
            String message = ingred.getName();

            ingredientList.get(key).setName(ingred.getName());
            ingredientList.get(key).setAmount(ingred.getAmount());
            ingredientList.get(key).setUnit(ingred.getUnit());

            mRootRef.child("Grocery").child(groupId).setValue(ingredientList);

            if(ingredientList.get(key).getAmount()==0.0)
            {
                ArrayList<Ingredients> copy = new ArrayList<>();
                for(int i=0; i<ingredientList.size(); i++)
                {
                    if(i==key)
                    {
                        i++;
                    }
                    copy.add(ingredientList.get(i));

                }

                mRootRef.child("Grocery").child(groupId).setValue(copy);
            }

        }

        Intent intent = new Intent(this, Grocery.class);
        startActivity(intent);

    }
}