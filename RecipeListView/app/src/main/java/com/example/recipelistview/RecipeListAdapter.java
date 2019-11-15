package com.example.recipelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RecipeListAdapter extends ArrayAdapter<Recipe>{

    private static final String TAG = "RecipeListAdapter";
    private Context mContext;
    int mResource;

    public RecipeListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Recipe> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the recipe info
        String name = getItem(position).getRecipeName();
        String num = String.valueOf(getItem(position).getRecipeID());

        //Create the recipe object with the info
        RecipeTest recipeTest = new RecipeTest(name, num);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView)convertView.findViewById(R.id.recipeName);
        TextView tvNum = (TextView)convertView.findViewById(R.id.missingTV);

        tvName.setText(name);
        tvNum.setText(num);

        return convertView;
    }
}
