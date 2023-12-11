package com.example.cs214project5;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class CurrentOrdersActivity extends AppCompatActivity {

    private TextView totalTextyView, orderNumberTextView, subTotalTextView, salesTaxTextView;
    private ListView orderListView;
    private Button removePizzaButton, placeOrderButton, currentOrdersBackButton;
    private static Order orderForApproval;
    private int indexToDelete = -1;
    final Handler handler = new Handler();
    /**
     * Generic android onCreate method, initializes buttons, listeners for widgets, IDs, and more.
     * @param savedInstanceState
     */
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StoreOrders storeOrders = new StoreOrders().getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_orders);
        initializeIds();
        initializeButtons();
        initializeRefreshService();


    }
    /**
     * initializeIds: Helper method to initialize all IDs to corresponding variable.
     */
    private void initializeIds(){
        totalTextyView = findViewById(R.id.totalTextyView);
        orderNumberTextView = findViewById(R.id.orderNumberTextView);
        removePizzaButton = findViewById(R.id.removePizzaButton);
        orderListView = findViewById(R.id.orderListView);
        subTotalTextView = findViewById(R.id.subTotalTextView);
        salesTaxTextView = findViewById(R.id.salesTaxTextView);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        currentOrdersBackButton = findViewById(R.id.currentOrdersBackButton);
    }
    /**
     * initializeButtons: Helper method that initializes and determines functionality for all buttons within
     * the scene. Also handles various Listeners that control what happen when other widgets are interacted with via click.
     */
    @SuppressLint("SetTextI18n")
    private void initializeButtons(){
        StoreOrders storeOrders = new StoreOrders().getInstance();
        currentOrdersBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(CurrentOrdersActivity.this, MainActivity.class);
            startActivity(intent);
        });
        removePizzaButton.setOnClickListener(v -> {
            showConfirmationDialog();
        });
        placeOrderButton.setOnClickListener(v -> {
            if (orderForApproval != null && !orderForApproval.getPizzas().isEmpty()){
                orderNumberTextView.setText("Order Number #"+orderForApproval.getNextOrderNumber());
                subTotalTextView.setText("Subtotal $");
                salesTaxTextView.setText("Sales Tax $");
                totalTextyView.setText("Total $");
                storeOrders.placeOrder(orderForApproval);

                ArrayList<String> pizzaList = new ArrayList<>();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(CurrentOrdersActivity.this, android.R.layout.simple_list_item_1, pizzaList);
                orderListView.setAdapter(adapter);
                adapter.clear();

                Toast.makeText(getApplicationContext(), "Order Placed!", Toast.LENGTH_SHORT).show();
                orderForApproval = null;
            } else {
                Toast.makeText(getApplicationContext(), "Order is Empty!", Toast.LENGTH_SHORT).show();
            }
        });

        orderListView.setOnItemClickListener((parent, view, position, id) -> {
            indexToDelete = position;
        });
    }
    /**
     * initializeRefreshService: Initializes refresh service that periodically refreshes current order to account for
     * any changes made to Current Order through different the various GUIs.
     */
    private void initializeRefreshService() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        final Handler handler = new Handler();
        Runnable refreshRunnable = new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                if(orderForApproval != null){
                    ArrayList<String> pizzaList = new ArrayList<>();
                    for(Pizza s : orderForApproval.getPizzas()){
                        pizzaList.add(s.toString());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(CurrentOrdersActivity.this, android.R.layout.simple_list_item_1, pizzaList);
                    ArrayList<String> check = new ArrayList<>();
                    if(orderListView.getAdapter() != null){
                        for(int i = 0; i < orderListView.getAdapter().getCount(); i++){
                            check.add(orderListView.getAdapter().getItem(i).toString());
                        }
                        if(check.equals(pizzaList)){

                        } else {
                            orderListView.setAdapter(adapter);
                        }
                    } else {
                        orderListView.setAdapter(adapter);
                    }
                    orderNumberTextView.setText("Order Number #"+orderForApproval.getOrderNumber());
                    subTotalTextView.setText("Subtotal $"+df.format(orderForApproval.calculateSubTotalPrice()));
                    salesTaxTextView.setText("Sales Tax $"+df.format(orderForApproval.calculateSalesTax()));
                    totalTextyView.setText("Total $"+df.format(orderForApproval.calculateTotal()));
                }
                handler.postDelayed(this, 800);
            }
        };
        handler.postDelayed(refreshRunnable, 800);
    }
    /**
     * createOrder: creates an instance of orderForApproval to be passed through different controllers for approval
     */
    public static void createOrder() {
        orderForApproval = new Order();
    }
    /**
     * getOrderForApproval: static method using in other classes to return an Order for approval through
     * different controllers.
     * @return orderForApproval
     */
    public static Order getOrderForApproval() {
        return orderForApproval;
    }

    /**
     * showConfirmationDialog: Method that uses AlertDialog to ask for confirmation when deleting a selected pizza
     * from the current orders available. Does nothing when no pizza is selected or there is no pizzas in the order.
     */
    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Pizza");
        builder.setMessage("Are you sure you want to remove this pizza?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            if(indexToDelete != -1){
                for(int i = 0; i < orderForApproval.getPizzas().size(); i++){
                    if(i == indexToDelete){
                        orderForApproval.removePizza(indexToDelete);
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "No pizza to delete!", Toast.LENGTH_SHORT).show();
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