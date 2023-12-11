package com.example.cs214project5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PizzaAdapter extends ArrayAdapter<Pizza> {

    /**
     * PizzaAdapter: Adapter used to format the recyclerView in a specific format, with the given toppings, pizza types,
     * etc.
     * @param context context of given object
     * @param resource various resources to use.
     * @param pizzas list of specialty Pizzas.
     */
    public PizzaAdapter(@NonNull Runnable context, int resource, @NonNull List<Pizza> pizzas) {
        super((Context) context, resource, pizzas);
    }

    /**
     * getView Override: formats the view of the recyclerView to display pizza image, followed by toppings, then prices
     * @param position
     * @param convertView
     * @param parent
     * @return View object containing the view of how a specialty pizza would look like with its description.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Pizza pizza = getItem(position);

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(pizza.toString());

        return convertView;
    }
}