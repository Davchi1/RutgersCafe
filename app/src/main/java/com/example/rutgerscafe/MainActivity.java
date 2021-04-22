package com.example.rutgerscafe;
/**
 * This class defines the main page for the application, allows the user to go to different gui's
 * @author David Okechukwu, Sajan Thornhill
 */
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.donutButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderDonuts();
            }
        });
        button = (Button) findViewById(R.id.coffeeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderCoffee();
            }
        });
        button = findViewById(R.id.orderButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrders();
            }
        });
        button = findViewById(R.id.storeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStoreOrders();
            }
        });
    }

    /**
     * Opens the OrderCoffee Gui
     */
    public void openOrderCoffee() {
        Intent intent = new Intent(this, OrderCoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the OrderDonuts Gui
     */
    public void openOrderDonuts() {
        Intent intent = new Intent(this, OrderDonutsActivity.class);

        startActivity(intent);
    }

    /**
     * Opens the Orders Gui
     */
    public void openOrders() {
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the StoreOrders Gui
     */
    public void openStoreOrders() {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        startActivity(intent);
    }
}