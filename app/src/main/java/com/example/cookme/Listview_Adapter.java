package com.example.cookme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
public class Listview_Adapter extends ArrayAdapter<Recipe> {

    private Context mContext;
    int mResource;
    public Listview_Adapter(@NonNull Context context, int resource, @NonNull List<Recipe> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the recipe information
        String name = getItem(position).getRecipeName();
        String num = String.valueOf(getItem(position).getRecipeID());

        //create the recipe object with the info
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView)convertView.findViewById(R.id.recipe_name_detail_view);
        TextView tvMissing_ingredient = (TextView)convertView.findViewById(R.id.missing_ingredient_view);

        tvName.setText(name);
        tvMissing_ingredient.setText(num);
        return convertView;

    }


}
