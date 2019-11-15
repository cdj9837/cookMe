package com.example.recipelistview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    private static final String TAG = "MainActivity";
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    ListView recipeLV;
    long recipeCount;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeLV = (ListView)findViewById(R.id.recipeLV);

        /*
        RecipeTest r1 = new RecipeTest("Chili", "3");
        RecipeTest r2 = new RecipeTest("Nugget", "1");
        RecipeTest r3 = new RecipeTest("Egg", "0");
        RecipeTest r4 = new RecipeTest("Chili", "3");
        RecipeTest r5 = new RecipeTest("Nugget", "1");
        RecipeTest r6 = new RecipeTest("Egg", "0");
        RecipeTest r7 = new RecipeTest("Chili", "3");
        RecipeTest r8 = new RecipeTest("Nugget", "1");
        RecipeTest r9 = new RecipeTest("Egg", "0");
        RecipeTest r10 = new RecipeTest("Chili", "3");
        RecipeTest r11 = new RecipeTest("Nugget", "1");
        RecipeTest r12 = new RecipeTest("Egg", "0");

        ArrayList<RecipeTest> recipeList = new ArrayList<>();
        recipeList.add(r1);
        recipeList.add(r2);
        recipeList.add(r3);
        recipeList.add(r4);
        recipeList.add(r5);
        recipeList.add(r6);
        recipeList.add(r7);
        recipeList.add(r8);
        recipeList.add(r9);
        recipeList.add(r10);
        recipeList.add(r11);
        recipeList.add(r12);

        //RecipeListAdapter adapter = new RecipeListAdapter(this, R.layout.recipe_list_item_layout, recipeList);
        //recipeLV.setAdapter(adapter);

         */
        final ArrayList<Recipe> recipeList = new ArrayList<>();

        mRootRef.child("Recipe").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot recipeSnapShot : dataSnapshot.getChildren()) {
                        Recipe r = recipeSnapShot.getValue(Recipe.class);
                        recipeList.add(r);
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

        RecipeListAdapter adapter = new RecipeListAdapter(this, R.layout.recipe_list_item_layout, recipeList);
        recipeLV.setAdapter(adapter);

        //what do on click of item
        recipeLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openRecipeInfoActivity(recipeList.get(position));
                //position is index of clicked item in list view

            }
        });
}

public void openRecipeInfoActivity(Recipe obj){
        Intent intent = new Intent(this, RecipeInfoActivity.class);
        intent.putExtra("RecipeObj", obj);
        startActivity(intent);
}
//TODO create favorite button and add to recipe_item_list_layout



//code block above for on data change was in onStart and would not work. Moved to onCreate and now it works.

/*
    @Override
    protected void onStart() {
        super.onStart();

        mRootRef.child("Recipe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recipeCount = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final ArrayList<Recipe> recipeList = new ArrayList<>();

        mRootRef.child("Recipe").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot recipeSnapShot : dataSnapshot.getChildren()) {
                        Recipe r = recipeSnapShot.getValue(Recipe.class);
                        recipeList.add(r);
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


        TODO: need to be done:
        //get data for inventory and then compare inventory to each recipe
        //can be it's own method to return array with index == recipeID and value of # of missing ingredients

        RecipeListAdapter adapter = new RecipeListAdapter(this, R.layout.recipe_list_item_layout, recipeList);
        recipeLV.setAdapter(adapter);

    }

 */
}
