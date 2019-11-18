package com.example.cookme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IngredientsListAdapter extends ArrayAdapter<Ingredients> {
    private static final String TAG = "IngredientsListAdapter";
    private Context mContext;
    int mResource;

    public IngredientsListAdapter(@NonNull Context context, int resource, @NonNull List<Ingredients> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the ingredient info
        String name = getItem(position).getName();
        String unit = String.valueOf(getItem(position).getUnit());
        String amount = String.valueOf(getItem(position).getAmount());
        //String notes = String.valueOf(getItem(position).getNotes());
        String item = amount + " " + unit + " " + name;

        //Create the recipe object with the info
        //RecipeTest recipeTest = new RecipeTest(name, num);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView ingredientTV = (TextView)convertView.findViewById(R.id.ingredientInfoTV);


        ingredientTV.setText(item);

        return convertView;
    }
}
