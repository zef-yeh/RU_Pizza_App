package com.example.cs214project5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView storeOrderTotalTextView;
    private ListView storeOrdersListView;
    private Button cancelStoreOrderButton, storeOrdersBackButton;
    private Spinner orderNumberPicker;

    private int indexToDelete = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        StoreOrders storeOrders = new StoreOrders().getInstance();
        initializeIds();
        initializeButtons();
        buildOrderPicker();
    }
    /**
     * initializeIds: Helper method to initialize all IDs to corresponding variable.
     */
    private void initializeIds(){
        storeOrderTotalTextView = findViewById(R.id.storeOrderTotalTextView);
        storeOrdersListView = findViewById(R.id.storeOrdersListView);
        cancelStoreOrderButton = findViewById(R.id.cancelStoreOrderButton);
        storeOrdersBackButton = findViewById(R.id.storeOrdersBackButton);
    }
    /**
     * initializeButtons: Helper method that initializes and determines functionality for all buttons within
     * the scene.
     */
    private void initializeButtons(){
        cancelStoreOrderButton.setOnClickListener(v -> {
            cancelOrder();
        });
        storeOrdersBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(StoreOrdersActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * onItemSelected Override: overridden method that determines behavior for selected an item within the order number
     * Spinner. When
     * @param parent Spinner object
     * @param view View of spinner object
     * @param position index of Order selected
     * @param id row of item selected.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        StoreOrders storeOrders = new StoreOrders().getInstance();
        Order selectedOrder = storeOrders.getAllOrders().get(position);
        indexToDelete = position;
        if(selectedOrder != null){
            ArrayList<String> pizzaList = new ArrayList<>();
            pizzaList.add(selectedOrder.toString());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(StoreOrdersActivity.this, android.R.layout.simple_list_item_1, pizzaList);
            storeOrdersListView.setAdapter(adapter);
            storeOrderTotalTextView.setText(df.format(selectedOrder.calculateTotal()));
        }
    }

    /**
     * onNothingSelected Override: defines no behavior for nothing selected.
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    /**
     * buildOrderPicker: Generates the order number spinner with orders that have been pushed to store orders from
     * current orders.
     */
    public void buildOrderPicker() {
        StoreOrders storeOrders = new StoreOrders().getInstance();
        List<Order> orderList = storeOrders.getAllOrders();
        if(orderList != null){
            List<Integer> orderNumberList = new ArrayList<>();
            for(Order a : orderList){
                orderNumberList.add(a.getOrderNumber());
            }
            orderNumberPicker = findViewById(R.id.orderNumberPicker);
            ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNumberList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            orderNumberPicker.setAdapter(adapter);
            orderNumberPicker.setOnItemSelectedListener(this);
        }
    }

    /**
     * cancelOrder: Method used to cancel the order selected in the listViewer and remove it from
     * the store orders instance, asks for confirmation using alertdialog before deleting.
     */
    public void cancelOrder(){
        showConfirmationDialog();
    }
    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancel Order");
        builder.setMessage("Are you sure you want to cancel this order?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            StoreOrders storeOrders = new StoreOrders().getInstance();
            if(storeOrders != null && !storeOrders.getAllOrders().isEmpty()){
                if(indexToDelete != -1){
                    storeOrders.cancelOrder(storeOrders.getAllOrders().get(indexToDelete));
                    ArrayList<String> orderList = new ArrayList<>();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(StoreOrdersActivity.this, android.R.layout.simple_list_item_1, orderList);
                    storeOrdersListView.setAdapter(adapter);
                    adapter.clear();
                    buildOrderPicker();
                }
            }
            dialog.dismiss();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}