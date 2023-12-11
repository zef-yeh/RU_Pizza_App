package com.example.cs214project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import com.example.cs214project5.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private Button SpecialtyPizzaButton, BuildYourOwnButton, CurrentOrderButton, StoreOrdersButton;
    private AppBarConfiguration appBarConfiguration;

    /**
     * Generic android onCreate method, initializes buttons, listeners for widgets, IDs, and more. In this case
     * initializes buttons for traveling to other GUIs.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpecialtyPizzaButton = findViewById(R.id.SpecialtyPizzaButton);
        SpecialtyPizzaButton.setOnClickListener(v -> openSpecialty());

        BuildYourOwnButton = findViewById(R.id.BuildYourOwnButton);
        BuildYourOwnButton.setOnClickListener(v -> openBYOP());

        CurrentOrderButton = findViewById(R.id.CurrentOrderButton);
        CurrentOrderButton.setOnClickListener(v -> openCurrentOrders());

        StoreOrdersButton = findViewById(R.id.StoreOrdersButton);
        StoreOrdersButton.setOnClickListener(v -> openStoreOrders());
    }

    /**
     * openSpecialty: Method that instantiates new specialty pizza activity from the main branch.
     */
    public void openSpecialty () {
        Intent intent = new Intent(MainActivity.this, SpecialtyPizzaActivity.class);
        startActivity(intent);
    }
    /**
     * openBYOP: Method that instantiates new build your own pizza activity from the main branch.
     */
    public void openBYOP () {
        Intent intent = new Intent(MainActivity.this, BuildYourOwnPizzaActivity.class);
        startActivity(intent);
    }
    /**
     * openCurrentOrders: Method that instantiates the current orders activity from the main branch.
     */
    public void openCurrentOrders () {
        Intent intent = new Intent(MainActivity.this, CurrentOrdersActivity.class);
        startActivity(intent);
    }
    /**
     * openStoreOrders: Method that instantiates new store orders activity from the main branch.
     */
    public void openStoreOrders () {
        Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
        startActivity(intent);
    }
}