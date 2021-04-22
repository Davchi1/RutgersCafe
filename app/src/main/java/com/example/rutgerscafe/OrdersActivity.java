package com.example.rutgerscafe;
/**
 * This class implements all the functions of the OrderCoffee Activity
 * @author David Okechukwu, Sajan Thornhill
 */
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Build;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class OrdersActivity extends AppCompatActivity {
    static Order myOrder = new Order();
    ListView orderItemsList;
    AlertDialog.Builder confirmationPop;
    private static ArrayList < String > tempArrayList = new ArrayList < String > ();
    private NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
    private Button addToOrderButton;
    private static double NJ_TAX = 0.06625;
    private static double subTotal = 0;
    private static double taxTotal = 0;
    private static double orderTotal = 0;
    private TextView subTotalTextView;
    private TextView taxTotalTextView;
    private TextView orderTotalTextView;
    private int orderPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayAdapter < String > ordersAdapter = new ArrayAdapter < String > (this, android.R.layout.simple_list_item_1, tempArrayList);
        confirmationPop = new AlertDialog.Builder(OrdersActivity.this);
        confirmationPop.setTitle("Cancel Item");
        confirmationPop.setMessage("Would you like to cancel this Item?");
        confirmationPop.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Object tempObj = myOrder.getMenuItems(orderPosition);

                ordersAdapter.remove(ordersAdapter.getItem(orderPosition));
                if (tempObj instanceof Coffee) {
                    Toast.makeText(getApplicationContext(), tempObj.toString() + " has been canceled!", Toast.LENGTH_SHORT).show();

                    subTotal -= myOrder.getMenuItems(orderPosition).itemPrice();
                    calculate();
                } else if (tempObj instanceof Donut) {
                    Toast.makeText(getApplicationContext(), tempObj.toString() + " donut(s) has been canceled!", Toast.LENGTH_SHORT).show();

                    tempObj.toString();
                    subTotal -= myOrder.getMenuItems(orderPosition).itemPrice() * ((Donut) myOrder.getMenuItems(orderPosition)).getQuantity();
                    calculate();
                }

                myOrder.remove(tempObj);

            }
        });

        confirmationPop.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        orderItemsList = findViewById(R.id.yourOrderListView);
        subTotalTextView = findViewById(R.id.subTotalTextViewPriceArea);
        taxTotalTextView = findViewById(R.id.taxTextViewPriceArea);
        orderTotalTextView = findViewById(R.id.totalTextViewPriceArea);
        addToOrderButton = findViewById(R.id.placeOrderButton);
        orderItemsList.setAdapter(ordersAdapter);
        calculate();

        orderItemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView < ? > parent, View view, int position, long id) {
                orderPosition = position;
                confirmationPop.show();
            }
        });
        addToOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempArrayList.isEmpty()) {
                    Toast.makeText(getApplicationContext(), " You have no orders to add to the store orders page!", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), " Your order has been added to the store orders page!", Toast.LENGTH_LONG).show();

                myOrder.setArrayAdapter(ordersAdapter);
                myOrder.setOrderTotal(orderTotal);
                StoreOrdersActivity.addToStoreOrder(myOrder, orderTotal, myOrder.getOrderNumber(), ordersAdapter);
                orderTotal = 0;
                subTotal = 0;
                taxTotal = 0;
                calculate();
                tempArrayList.clear();
                ordersAdapter.clear();
                Order tempOrder = new Order();
                myOrder = tempOrder;
            }
        });
    }

    /**
     * This static method allows other classes to add menu items to the OrdersActivity
     * @param myMenuItem
     * @param currentsubTotal
     * @param quantity
     */
    public static void addToOrder(MenuItem myMenuItem, double currentsubTotal, int quantity) {
        myOrder.add(myMenuItem);
        tempArrayList.add(myMenuItem.getMenuItemsDetails());
        subTotal += currentsubTotal;
        taxTotal += currentsubTotal * NJ_TAX;
        orderTotal = subTotal + taxTotal;
    }

    /**
     * Calculates the order and tax total for the current orders
     */
    private void calculate() {
        taxTotal = subTotal * NJ_TAX;
        orderTotal = taxTotal + subTotal;
        subTotalTextView.setText(n.format(subTotal));
        orderTotalTextView.setText(n.format(orderTotal));
        taxTotalTextView.setText(n.format(taxTotal));
    }
}