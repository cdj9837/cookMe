package com.example.cookme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
public class Listview_Adapter extends ArrayAdapter<MissingIngredientFromRecipe> { //uses new class "MissingIngredientFromRecipe.java"

    private static final String TAG = "RecipeListAdapter";
    private Context mContext;
    int mResource;

    public Listview_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<MissingIngredientFromRecipe> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the recipe info
        String name = getItem(position).getRecipeName();
        String num = String.valueOf(getItem(position).getNumMissingIngredients());

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView)convertView.findViewById(R.id.recipe_name_detail_view);
        TextView tvNum = (TextView)convertView.findViewById(R.id.missing_ingredient_view);

        tvName.setText(name);
        tvNum.setText(num);

        return convertView;
    }
}
