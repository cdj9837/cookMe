package com.example.cookme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Favorite_Recipe extends AppCompatActivity {
    private static final String TAG = "RecipeActivity";
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    ListView recipe_listview;
    final static ArrayList<Ingredients> ingredientsList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite__recipe);
        Button all_rep = (Button) findViewById(R.id.all_recipe_button);
        Button fav_rep = (Button) findViewById(R.id.favorite_recipe_button);
        Button home = (Button) findViewById(R.id.home_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        all_rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Recipe_Main.class);
                startActivity(i);
            }
        });
        fav_rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Favorite_Recipe.class);
                startActivity(i);
            }
        });
        recipe_listview = (ListView)findViewById(R.id.recipe_listview);

        final ArrayList<Recipe> recipeList = new ArrayList<>();
        final ArrayList<MissingIngredientFromRecipe> missingList = new ArrayList<>();


        final Listview_Adapter adapter = new Listview_Adapter(this, R.layout.main_recipe_layout, missingList); //pass missingList just for display purposes.

        mRootRef.child("Inventory").child("1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot ingredientSnapShot : dataSnapshot.getChildren()) {
                        Ingredients ing = ingredientSnapShot.getValue(Ingredients.class);
                        ingredientsList.add(ing);

                    }
                } catch(Exception e){
                    e.printStackTrace();
                }

                mRootRef.child("Favorite").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            for (DataSnapshot recipeSnapShot : dataSnapshot.getChildren()) {
                                Recipe r = recipeSnapShot.getValue(Recipe.class);
                                recipeList.add(r);
                                MissingIngredientFromRecipe m = new MissingIngredientFromRecipe(r.getRecipeName(), recipeSnapShot.getKey());
                                missingList.add(m);
                            }

                            int tell=-1;
                            for(int i = 0; i<recipeList.size(); i++){
                                for(int j = 0; j < recipeList.get(i).getIngredients().size(); j++) {
                                    tell=-1;
                                    for(Ingredients ing: ingredientsList){
                                        if (ing.getName().toLowerCase() == recipeList.get(i).getIngredients().get(j).getName().toLowerCase()) {
                                            tell =1;
                                        }

                                    }
                                    if(tell!=1)
                                        missingList.get(i).incrementNum();
                                }
                            }

                            Collections.sort(missingList, new Sortbymissing());
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


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recipe_listview.setAdapter(adapter);

        //what do on click of item
        recipe_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = missingList.get(position).getRecipeName();
                for(int i=0; i < recipeList.size(); i++){
                    if(recipeList.get(i).getRecipeName() == name){
                        openRecipeInfoActivity(recipeList.get(i));
                        //position is index of clicked item in list view
                        break;
                    }
                }
            }
        });
    }


    public void openRecipeInfoActivity(Recipe obj){
        Intent intent = new Intent(this, RecipeInfoActivity.class);
        intent.putExtra("RecipeObj", obj);
        startActivity(intent);
    }


}
