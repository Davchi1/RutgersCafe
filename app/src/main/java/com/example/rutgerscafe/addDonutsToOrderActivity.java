package com.example.rutgerscafe;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.NumberFormat;
import java.util.Locale;

public class addDonutsToOrderActivity extends AppCompatActivity {
    Spinner mySpinner;
    TextView myTextView;
    TextView subTotalTextView;
    ListView tempListViewOrder;
    String selectedItem;
    Button addToOrderButton;
    int donutQuantity;
    private NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent in = getIntent();
        setContentView(R.layout.activity_add_donuts_to_order);

        Bundle bundle = in .getExtras();
        selectedItem = bundle.getString("selectedFlavor");
        super.onCreate(savedInstanceState);
        Integer[] donutQuantityOptions = {1, 2, 3, 4, 5};
        myTextView = findViewById(R.id.selectedDonutTextView);
        subTotalTextView = findViewById(R.id.subTotalDisplayTextView);
        addToOrderButton = findViewById(R.id.addDonutToOrderButton);

        myTextView.setText(selectedItem);

        ArrayAdapter adapter = new ArrayAdapter < Integer > (this, android.R.layout.simple_list_item_1, donutQuantityOptions);
        mySpinner = findViewById(R.id.donutQuantitySpinner);
        tempListViewOrder = findViewById(R.id.yourOrderListView);

        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Sets the subtotal dynamically on selection of a quantity from the spinner
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView < ? > parent, View view, int position, long id) {
                donutQuantity = (int) mySpinner.getSelectedItem();
                double totalDonutPrice = donutQuantity * Constants.DONUT_PRICE;
                subTotalTextView.setText(n.format(totalDonutPrice));
            }

            @Override
            public void onNothingSelected(AdapterView < ? > parent) {

            }
        });

        addToOrderButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Adds a donut given the information provided on the Gui to the orders
             * @param v
             */
            @Override
            public void onClick(View v) {
                Donut myDonut = new Donut(selectedItem, donutQuantity);
                OrdersActivity.addToOrder(myDonut, Constants.DONUT_PRICE * donutQuantity, donutQuantity);
                Toast.makeText(getApplicationContext(), "You've successfully added " + myDonut.toString() + " donut(s) to your order", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void onClick(View view) {}


}