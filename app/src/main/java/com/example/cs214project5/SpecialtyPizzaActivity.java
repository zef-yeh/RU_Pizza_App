package com.example.cs214project5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpecialtyPizzaActivity extends AppCompatActivity implements specialtyPizzaAdapter.OnItemClickListener{

    private TextView pizzaPicker;
    private Toast toast;
    private RadioGroup sizeRadioGroup;
    private RadioButton smallButton;
    private TextView totalTextView, sauceTextView;
    private String currentPizzaPickerString;
    private CheckBox extraCheese;
    private CheckBox extraSauce;
    private ListView toppingsListView;
    private Button addButton, specialtyBackButton;
    private RecyclerView specialtyRecycleView;
    private EditText quantityEditText;

    /**
     * Generic android onCreate method, initializes buttons, listeners for widgets, IDs, and more.
     * @param savedInstanceState
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StoreOrders storeOrders = new StoreOrders().getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty_pizza2);
        initializeIds();
        initializeRecyclerView();
        initializeButtons();
        quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                calculatePizzaPrice(currentPizzaPickerString);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    /**
     * initializeIds: Helper method to initialize all IDs to corresponding variable.
     */
    private void initializeIds(){
        totalTextView = findViewById(R.id.totalTextView);
        sizeRadioGroup = findViewById(R.id.sizeRadioGroup);
        extraCheese = findViewById(R.id.extraCheese);
        extraSauce = findViewById(R.id.extraSauce);
        toppingsListView = findViewById(R.id.toppingsListView);
        addButton = findViewById(R.id.addButton);
        sauceTextView = findViewById(R.id.sauceTextView);
        specialtyBackButton = findViewById(R.id.specialtyBackButton);
        pizzaPicker = findViewById(R.id.pizzaPicker);
        smallButton = findViewById(R.id.smallButton);
        quantityEditText = findViewById(R.id.quantityEditText);
    }

    /**
     * initializeRecyclerView: Method that initializes all necessary files for the recyclerView to function properly,
     * such as importing various Strings and images.
     */
    private void initializeRecyclerView(){
        String s1[], s2[], s3[];
        int images[] = {R.drawable.del, R.drawable.suprem, R.drawable.meats, R.drawable.yummupiza, R.drawable.pep,
                R.drawable.stronteroni, R.drawable.hawaiian, R.drawable.greenbacon, R.drawable.stracon, R.drawable.strimp};
        specialtyRecycleView = findViewById(R.id.specialtyRecycleView);
        s1 = getResources().getStringArray(R.array.specialty_pizzas);
        s2 = getResources().getStringArray(R.array.description);
        s3 = getResources().getStringArray(R.array.prices);

        specialtyPizzaAdapter adapter = new specialtyPizzaAdapter(this, s1, s2, s3, images, this);

        specialtyRecycleView.setAdapter(adapter);
        specialtyRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * initializeButtons: Helper method that initializes and determines functionality for all buttons within
     * the scene.
     */
    private void initializeButtons(){
        sizeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            if (radioButton != null) {
                if (currentPizzaPickerString != null) {
                    calculatePizzaPrice(currentPizzaPickerString);
                }
            }
        });
        extraCheese.setOnCheckedChangeListener((buttonView, isChecked) -> {
            calculatePizzaPrice(currentPizzaPickerString);
        });

        extraSauce.setOnCheckedChangeListener((buttonView, isChecked) -> {
            calculatePizzaPrice(currentPizzaPickerString);
        });
        addButton.setOnClickListener(v -> {
            addOrder();
        });
        specialtyBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(SpecialtyPizzaActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * onItemClick() Override: Overridden method from adapter to handle functionality whenever an item from the
     * RecyclerView is clicked. Determines type of specialty pizza upon click here.
     * @param specialtyPizza String name of specialty pizza selected.
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onItemClick(String specialtyPizza) {
        smallButton.setChecked(true); quantityEditText.setText("1");
        currentPizzaPickerString = specialtyPizza.toLowerCase(); String special = specialtyPizza.toLowerCase();
        getToppingsStringListAndUpdateView(specialtyPizza);
        if ("deluxe".equals(special)) {
            pizzaPicker.setText("Selected Pizza: Deluxe");
            sauceTextView.setText("Sauce: Tomato");
        } else if ("supreme".equals(special)) {
            pizzaPicker.setText("Selected Pizza: Supreme");
            sauceTextView.setText("Sauce: Tomato");
        } else if ("meatzza".equals(special)) {
            pizzaPicker.setText("Selected Pizza: Meatzza");
            sauceTextView.setText("Sauce: Tomato");
        } else if ("seafood".equals(special)) {
            pizzaPicker.setText("Selected Pizza: Seafood");
            sauceTextView.setText("Sauce: Alfredo");
        } else if ("pepperoni".equals(special)) {
            pizzaPicker.setText("Selected Pizza: Pepperoni");
            sauceTextView.setText("Sauce: Tomato");
        } else if ("stronteroni".equals(special)) {
            pizzaPicker.setText("Selected Pizza: Stronteroni");
            sauceTextView.setText("Sauce: Tomato");
        } else if ("hawaiian".equals(special)) {
            pizzaPicker.setText("Selected Pizza: Hawaiian");
            sauceTextView.setText("Sauce: Tomato");
        } else if ("gracon".equals(special)) {
            pizzaPicker.setText("Selected Pizza: Gracon");
            sauceTextView.setText("Sauce: Tomato");
        } else if ("stracon".equals(special)) {
            pizzaPicker.setText("Selected Pizza: Stracon");
            sauceTextView.setText("Sauce: Tomato");
        } else if ("strimp".equals(special)) {
            pizzaPicker.setText("Selected Pizza: Strimp");
            sauceTextView.setText("Sauce: Tomato");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                calculatePizzaPrice(special);
            }
        }, 100);
    }

    /**
     * pizzaChoose: Helper method that chooses what pizza to call PizzaMaker with given a string.
     *
     * @param pizzaType type of specialty pizza.
     * @return specialty pizza of corresponding type.
     */
    private Pizza pizzaChoose(String pizzaType) {
        if ("deluxe".equals(pizzaType)) {
            return PizzaMaker.createPizza("deluxe");
        } else if ("supreme".equals(pizzaType)) {
            return PizzaMaker.createPizza("supreme");
        } else if ("meatzza".equals(pizzaType)) {
            return PizzaMaker.createPizza("meatzza");
        } else if ("seafood".equals(pizzaType)) {
            return PizzaMaker.createPizza("seafood");
        } else if ("pepperoni".equals(pizzaType)) {
            return PizzaMaker.createPizza("pepperoni");
        } else if ("stronteroni".equals(pizzaType)) {
            return PizzaMaker.createPizza("stronteroni");
        } else if ("hawaiian".equals(pizzaType)) {
            return PizzaMaker.createPizza("hawaiian");
        } else if ("gracon".equals(pizzaType)) {
            return PizzaMaker.createPizza("gracon");
        } else if ("stracon".equals(pizzaType)) {
            return PizzaMaker.createPizza("stracon");
        } else if ("strimp".equals(pizzaType)) {
            return PizzaMaker.createPizza("strimp");
        } else {
            return null;
        }
    }

    /**
     * checkButtons: Helper method that creates a build your own pizza object depending on the fields that have been
     * checked off in the GUI.
     * @param specialPizza specialty pizza object to be added to order.
     * @param pizzaType String describing type of specialty pizza.
     */
    private void checkButtons(Pizza specialPizza, String pizzaType){
        int selectedRadioButtonId = sizeRadioGroup.getCheckedRadioButtonId();
        if (specialPizza != null) {
            if (selectedRadioButtonId == R.id.smallButton) {
                specialPizza.size = Size.SMALL;
            } else if (selectedRadioButtonId == R.id.mediumButton) {
                specialPizza.size = Size.MEDIUM;
            } else if (selectedRadioButtonId == R.id.largeButton) {
                specialPizza.size = Size.LARGE;
            }
            if (extraCheese.isChecked()) {
                specialPizza.extraCheese = true;
            }
            if (extraSauce.isChecked()) {
                specialPizza.extraSauce = true;
            }
            if (getToppingsList(pizzaType) != null) {
                specialPizza.toppings = getToppingsList(pizzaType);
            }
            if (CurrentOrdersActivity.getOrderForApproval() == null) {
                CurrentOrdersActivity.createOrder();
            }
        }
    }
    /**
     * addOrder: Reads attributes of a given build your own pizza inputted from the GUI and generates
     * a pizza object and adds it to the current order, also handles errors.
     */
    void addOrder() {
        if (currentPizzaPickerString != null) {
            int selectedRadioButtonId = sizeRadioGroup.getCheckedRadioButtonId();
            if (selectedRadioButtonId == R.id.smallButton || selectedRadioButtonId == R.id.mediumButton || selectedRadioButtonId == R.id.largeButton) {
                String pizzaType = currentPizzaPickerString;
                Pizza specialPizza = null;
                specialPizza = pizzaChoose(pizzaType);
                if (specialPizza != null) {
                    checkButtons(specialPizza, pizzaType);
                    Order ExistingOrder = CurrentOrdersActivity.getOrderForApproval();
                    if(parseInt(quantityEditText.getText().toString())){
                        int value = Integer.parseInt(quantityEditText.getText().toString());
                        if(value > 0){
                            for(int i = 0; i < value; i++){
                                ExistingOrder.addPizza(specialPizza);
                            }
                            Toast.makeText(getApplicationContext(), "Pizza(s) successfully added!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Must have at least 1 Pizza!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "Select a size!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Select a pizza!", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * getToppingsList: Helper method used to generate a String Arraylist full of toppings contained within a
     * certain specialty pizza.
     * @param pizzaType type of specialty pizza
     * @return list of pizza toppings of pizza otherwise null.
     */
    private ArrayList<Topping> getToppingsList(String pizzaType){
        pizzaType = pizzaType.toLowerCase();
        ArrayList<Topping> tops = null;
        if ("deluxe".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM));
        } else if ("supreme".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.HAM, Topping.GREEN_PEPPER, Topping.ONION, Topping.BLACK_OLIVE, Topping.MUSHROOM));
        } else if ("meatzza".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF, Topping.HAM));
        } else if ("seafood".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.SHRIMP, Topping.SQUID, Topping.CRAB_MEATS));
        } else if ("pepperoni".equals(pizzaType)) {
            tops = new ArrayList<>(Collections.singletonList(Topping.PEPPERONI));
        } else if ("stronteroni".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.STRONTIUM90, Topping.PEPPERONI));
        } else if ("hawaiian".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.PINEAPPLE, Topping.HAM));
        } else if ("gracon".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.GREEN_PEPPER, Topping.BACON));
        } else if ("stracon".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.STRONTIUM90, Topping.BACON));
        } else if ("strimp".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.STRONTIUM90, Topping.SHRIMP));
        }
        return tops;
    }

    /**
     * getToppingsList: Helper method used to generate a String Arraylist full of toppings contained within a
     * certain specialty pizza.
     * @param pizzaType type of specialty pizza
     * @return list of pizza toppings of pizza otherwise null.
     */
    private void getToppingsStringListAndUpdateView(String pizzaType){
        pizzaType = pizzaType.toLowerCase();
        ArrayList<String> tops = null;
        if ("deluxe".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE.ToppingName, Topping.PEPPERONI.ToppingName, Topping.GREEN_PEPPER.ToppingName, Topping.ONION.ToppingName, Topping.MUSHROOM.ToppingName));
        } else if ("supreme".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE.ToppingName, Topping.PEPPERONI.ToppingName, Topping.HAM.ToppingName, Topping.GREEN_PEPPER.ToppingName, Topping.ONION.ToppingName, Topping.BLACK_OLIVE.ToppingName, Topping.MUSHROOM.ToppingName));
        } else if ("meatzza".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.SAUSAGE.ToppingName, Topping.PEPPERONI.ToppingName, Topping.BEEF.ToppingName, Topping.HAM.ToppingName));
        } else if ("seafood".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.SHRIMP.ToppingName, Topping.SQUID.ToppingName, Topping.CRAB_MEATS.ToppingName));
        } else if ("pepperoni".equals(pizzaType)) {
            tops = new ArrayList<>(Collections.singletonList(Topping.PEPPERONI.ToppingName));
        } else if ("stronteroni".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.STRONTIUM90.ToppingName, Topping.PEPPERONI.ToppingName));
        } else if ("hawaiian".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.PINEAPPLE.ToppingName, Topping.HAM.ToppingName));
        } else if ("gracon".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.GREEN_PEPPER.ToppingName, Topping.BACON.ToppingName));
        } else if ("stracon".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.STRONTIUM90.ToppingName, Topping.BACON.ToppingName));
        } else if ("strimp".equals(pizzaType)) {
            tops = new ArrayList<>(Arrays.asList(Topping.STRONTIUM90.ToppingName, Topping.SHRIMP.ToppingName));
        } else {
            return;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tops);
        toppingsListView.setAdapter(adapter);
    }

    /**
     * calculatePizzaPrice: calculates price of specialty Pizza based on realtime inputs, and prints it to
     * the GUI whenever any feature is adjusted.
     * @param selectedPizza specialty pizza type in string form to perform calculations.
     */
    @SuppressLint("SetTextI18n")
    private void calculatePizzaPrice(String selectedPizza) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        double total = 0;
        if (selectedPizza != null) {
            total = numberCruncher(selectedPizza);
            int selectedRadioButtonId = sizeRadioGroup.getCheckedRadioButtonId();
            if (selectedRadioButtonId == R.id.smallButton) {
                total +=0;
            } else if (selectedRadioButtonId == R.id.mediumButton) {
                total += 2.00;
            } else if (selectedRadioButtonId == R.id.largeButton) {
                total += 4;
            } else {
                total += 0;
            }
            CheckBox extraCheese = findViewById(R.id.extraCheese);
            CheckBox extraSauce = findViewById(R.id.extraSauce);
            if(extraCheese.isChecked()) {
                total+=1;
            }
            if(extraSauce.isChecked()){
                total += 1;
            }
            if(parseInt(quantityEditText.getText().toString())){
                int value = Integer.parseInt(quantityEditText.getText().toString());
                if(value > 0){
                    total = total*value;
                } else {
                    Toast.makeText(getApplicationContext(), "Must have at least 1 Pizza!", Toast.LENGTH_SHORT).show();
                }
            }
            totalTextView.setText("Total: $"+df.format(total));
        }
    }

    /**
     * Helper method used to calculate pizza price depending on its specialty type.
     * @param selectedPizza type of specialty pizza in as string
     * @return base price of specific specialty pizza.
     */
    private double numberCruncher(String selectedPizza){
        if ("deluxe".equals(selectedPizza)) {
            return 14.99;
        } else if ("supreme".equals(selectedPizza)) {
            return 15.99;
        } else if ("meatzza".equals(selectedPizza)) {
            return 16.99;
        } else if ("seafood".equals(selectedPizza)) {
            return 17.99;
        } else if ("pepperoni".equals(selectedPizza)) {
            return 10.99;
        } else if ("stronteroni".equals(selectedPizza)){
            return 1001.99;
        } else if ("hawaiian".equals(selectedPizza)){
            return 14.99;
        } else if ("gracon".equals(selectedPizza)){
            return 11.99;
        } else if ("stracon".equals(selectedPizza)){
            return 200.99;
        } else if ("strimp".equals(selectedPizza)){
            return 301.99;
        }
        return 0;
    }
    /**
     * parseInt(): Helper method used to determine whether a String can be converted to int.
     * @param text String to parse as int.
     * @return true if able, false otherwise
     */
    private boolean parseInt(String text){
        try {
            int value = Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid number entered", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}