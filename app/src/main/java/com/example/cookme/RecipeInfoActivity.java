package com.example.cookme;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RecipeInfoActivity extends AppCompatActivity {

    ListView ingredientLV;
    String groupId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);
        final DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

        groupId=MainActivity.groupID;

        final Recipe r = (Recipe) getIntent().getSerializableExtra("RecipeObj");

        ingredientLV = (ListView)findViewById(R.id.ingredientLV);
        TextView recipeInfoNameTV = (TextView)findViewById(R.id.recipeInfoNameTV);
        TextView recipeInfoDirectionsTV = (TextView)findViewById(R.id.recipeInfoDirectionsTV);
        Button makeButton = (Button)findViewById(R.id.makeBTN);
        Button addToGroceryButton = (Button)findViewById(R.id.addToGroceryBTN);

        recipeInfoNameTV.setText(r.RecipeName);
        recipeInfoDirectionsTV.setText(r.Directions);

        recipeInfoDirectionsTV.setMovementMethod(new ScrollingMovementMethod());

        IngredientsListAdapter adapter = new IngredientsListAdapter(this, R.layout.ingredient_list_layout, r.Ingredients);
        ingredientLV.setAdapter(adapter);

        addToGroceryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int test = 0;
                for(Ingredients i : r.Ingredients){
                    test = 0;
                    for(Ingredients j : Recipe_Main.ingredientsList){
                        if(i.getName().toLowerCase().equals(j.getName().toLowerCase())){
                            test = 1;
                        }
                    }
                    if(test == 0){
                        mRootRef.child("Grocery").child(groupId).push().setValue(i);
                    }
                }
            }
        });
    }
}
