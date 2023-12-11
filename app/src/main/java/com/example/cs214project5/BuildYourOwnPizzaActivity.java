package com.example.cs214project5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BuildYourOwnPizzaActivity extends AppCompatActivity {
    private Button byopBackButton, byopAddToOrder;
    private ListView originalToppingsListView, addedToppingListView;
    private RadioGroup byopSizeRadioGroup;
    private CheckBox byopExtraCheeseCheckBox, byopExtraSauceCheckBox;
    private Spinner byopSauceSpinner;
    private TextView byopTotalTextView;
    private EditText byopQuantityEditTextNumber;
    private RadioButton smallPizzaButton, mediumPizzaButton, largePizzaButton;
    private ArrayAdapter<String> originalTops;
    private ArrayAdapter<String> addedTops;

    /**
     * Generic android onCreate method, initializes buttons, listeners for widgets, IDs, and more.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_your_own_pizza);
        initializeIds();
        initializeButtons();
        initializeToppingPickers();
        byopSauceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calculatePizzaPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        byopQuantityEditTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                calculatePizzaPrice();
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
        originalToppingsListView = findViewById(R.id.originalToppingsListView);
        addedToppingListView = findViewById(R.id.addedToppingListView);
        byopSauceSpinner = findViewById(R.id.byopSauceSpinner);
        byopExtraCheeseCheckBox = findViewById(R.id.byopExtraCheeseCheckBox);
        byopExtraSauceCheckBox = findViewById(R.id.byopExtraSauceCheckBox);
        byopQuantityEditTextNumber = findViewById(R.id.byopQuantityEditTextNumber);
        byopTotalTextView = findViewById(R.id.byopTotalTextView);
        byopSizeRadioGroup = findViewById(R.id.byopSizeRadioGroup);
        smallPizzaButton = findViewById(R.id.smallPizzaButton);
        mediumPizzaButton = findViewById(R.id.mediumPizzaButton);
        largePizzaButton = findViewById(R.id.largePizzaButton);
        byopAddToOrder = findViewById(R.id.byopAddToOrder);
        byopBackButton = findViewById(R.id.byopBackButton);
    }

    /**
     * initializeButtons: Helper method that initializes and determines functionality for all buttons within
     * the scene.
     */
    private void initializeButtons(){
        byopSizeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            calculatePizzaPrice();
        });
        byopExtraCheeseCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            calculatePizzaPrice();
        });
        byopExtraSauceCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            calculatePizzaPrice();
        });
        byopAddToOrder.setOnClickListener(v->{
            addOrder();
        });
        byopBackButton.setOnClickListener(v->{
            Intent intent = new Intent(BuildYourOwnPizzaActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * initializeToppingPickers: Helper method that initializes the topping list view full of all the toppings, and allows
     * the user to add and remove toppings from both lists.
     */
    private void initializeToppingPickers(){
        ArrayList<String> originalToppingsList = new ArrayList<>();
        Topping[] tops = Topping.values();
        for(Topping a : tops){
            originalToppingsList.add(a.toString());
        }
        originalTops = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, originalToppingsList);
        originalToppingsListView.setAdapter(originalTops);
        addedTops = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        addedToppingListView.setAdapter(addedTops);
        originalToppingsListView.setOnItemClickListener((adapterView, view, i, l) -> {
            String selectedItem = originalTops.getItem(i);
            originalTops.remove(selectedItem);
            addedTops.add(selectedItem);
            calculatePizzaPrice();
        });
        addedToppingListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = addedTops.getItem(position);
            addedTops.remove(selectedItem);
            originalTops.add(selectedItem);
            calculatePizzaPrice();
        });
        Sauce[] sa = Sauce.values();
        List<String> sauceSpinnerItems = new ArrayList<>();
        for(Sauce a : sa){
            sauceSpinnerItems.add(String.valueOf(a));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sauceSpinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        byopSauceSpinner.setAdapter(adapter);
    }

    /**
     * checkFields: Helper method that creates a build your own pizza object depending on the fields that have been
     * checked off in the GUI, and then adds it to the current order.
     * @param selectedRadioButtonId integer that determines what size the pizza is.
     * @param currentSauce String that determines sauce
     * @param value integer that determines quantity of pizza.
     */
    private void checkFields(int selectedRadioButtonId, String currentSauce, int value){
        Pizza buildPizza = PizzaMaker.createPizza("buildyourown");
        ArrayList<String> toppingList = new ArrayList<>();
        for (int i = 0; i < addedToppingListView.getAdapter().getCount(); i++) {
            toppingList.add(addedToppingListView.getAdapter().getItem(i).toString());
        }
        buildPizza.toppings = new ArrayList<>(convertToToppings(toppingList));
        if (selectedRadioButtonId == R.id.smallPizzaButton) {
            buildPizza.size = Size.SMALL;
        } else if (selectedRadioButtonId == R.id.mediumPizzaButton) {
            buildPizza.size = Size.MEDIUM;
        } else if (selectedRadioButtonId == R.id.largePizzaButton) {
            buildPizza.size = Size.LARGE;
        }
        if(currentSauce.equals("TOMATO")){
            buildPizza.sauce = Sauce.TOMATO;
        }
        if(currentSauce.equals("ALFREDO")){
            buildPizza.sauce = Sauce.ALFREDO;
        }
        if(byopExtraSauceCheckBox.isChecked()){
            buildPizza.extraSauce = true;
        }
        if(byopExtraCheeseCheckBox.isChecked()){
            buildPizza.extraCheese = true;
        }
        if (CurrentOrdersActivity.getOrderForApproval() == null) {
            CurrentOrdersActivity.createOrder();
        }
        Order ExistingOrder = CurrentOrdersActivity.getOrderForApproval();
        for(int i = 0; i < value; i++){
            ExistingOrder.addPizza(buildPizza);
        }
        Toast.makeText(getApplicationContext(), "Pizza(s) successfully added!", Toast.LENGTH_SHORT).show();
    }

    /**
     * addOrder: Reads attributes of a given build your own pizza inputted from the GUI and generates
     * a pizza object and adds it to the current order, also handles errors.
     */
    void addOrder() {
        String currentSauce = byopSauceSpinner.getSelectedItem().toString();
        int selectedRadioButtonId = byopSizeRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == R.id.smallPizzaButton || selectedRadioButtonId == R.id.mediumPizzaButton || selectedRadioButtonId == R.id.largePizzaButton) {
            if(addedToppingListView.getAdapter().getCount() >= 3 && addedToppingListView.getAdapter().getCount() <= 7){
                if(parseInt(byopQuantityEditTextNumber.getText().toString())){
                    int value = Integer.parseInt(byopQuantityEditTextNumber.getText().toString());
                    if(value > 0){
                        checkFields(selectedRadioButtonId, currentSauce, value);
                    } else {
                        Toast.makeText(getApplicationContext(), "Must have at least 1 Pizza!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Must have at least 1 Pizza!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Not enough/too many toppings!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * convertToToppings: Helper method that converts a ArrayList<String> to an ArrayList<Topping>
     * @param toppingNames String arrayList to be converted
     * @return toppingNames as a Topping ArrayList.
     */
    public static ArrayList<Topping> convertToToppings(ArrayList<String> toppingNames) {
        ArrayList<Topping> toppings = new ArrayList<>();
        for (String toppingName : toppingNames) {
            Topping matchingTopping = findMatchingTopping(toppingName);
            if (matchingTopping != null) {
                toppings.add(matchingTopping);
            }
        }
        return toppings;
    }

    /**
     * findMatchingTopping: helper method that finds the corresponding topping based on given String.
     * @param toppingName string to match
     * @return matched topping/string.
     */
    private static Topping findMatchingTopping(String toppingName) {
        for (Topping topping : Topping.values()) {
            if (topping.name().equals(toppingName)) {
                return topping;
            }
        }
        return null;
    }
    /**
     * calculatePizzaPrice: calculates price of Pizza based on realtime inputs, and prints it to
     * the GUI whenever any feature is adjusted.
     */
    @SuppressLint("SetTextI18n")
    private void calculatePizzaPrice(){
        DecimalFormat df = new DecimalFormat("#,##0.00");
        int selectedRadioButtonId = byopSizeRadioGroup.getCheckedRadioButtonId();
        double total = 0;
        if(selectedRadioButtonId != -1) {
            if(selectedRadioButtonId == smallPizzaButton.getId()){
                total = 8.99;
            } else if(selectedRadioButtonId == mediumPizzaButton.getId()){
                total = 8.99+2;
            } else if(selectedRadioButtonId == largePizzaButton.getId()){
                total = 8.99+2+2;
            } else {
                total += 0;
            }
            if(byopExtraCheeseCheckBox.isChecked()){
                total+=1;
            }
            if(byopExtraSauceCheckBox.isChecked()){
                total+=1;
            }
            for(int i = 0; i < addedToppingListView.getAdapter().getCount(); i++){
                if(i > 2 && i < 7){
                    total += 1.49;
                }
            }
        }
        if(parseInt(byopQuantityEditTextNumber.getText().toString())){
            int value = Integer.parseInt(byopQuantityEditTextNumber.getText().toString());
            if(value > 0){
                total = total*value;
            } else {
                Toast.makeText(getApplicationContext(), "Must have at least 1 Pizza!", Toast.LENGTH_SHORT).show();
            }
        }
        byopTotalTextView.setText("Total $: "+df.format(total));
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
            return false;
        }
    }
}