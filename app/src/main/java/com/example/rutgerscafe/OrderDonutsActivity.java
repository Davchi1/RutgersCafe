package com.example.rutgerscafe;
/**
 * This class implements all the functions of the OrderDonuts Activity
 * @author David Okechukwu, Sajan Thornhill
 */
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class OrderDonutsActivity extends AppCompatActivity {
    String[] donutTypes = {
            "Chocolate",
            "Bostom Cream",
            "Jelly",
            "Blueberry"
    };
    String selectedItem = "";
    ListView donutListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_donuts);
        donutListView = findViewById(R.id.donutListView);
        ArrayAdapter adapter = new ArrayAdapter < String > (this, android.R.layout.simple_list_item_1, donutTypes);
        donutListView.setAdapter(adapter);
        donutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView < ? > parent, View view, int position, long id) {
                setTitle("Order Donuts");
                Intent intent = new Intent(donutListView.getContext(), addDonutsToOrderActivity.class);
                switch (position) {
                    case 0:
                        selectedItem = "Chocolate";
                        intent.putExtra("selectedFlavor", selectedItem);
                        startActivity(intent);

                        break;
                    case 1:
                        selectedItem = "Boston Cream";
                        intent.putExtra("selectedFlavor", selectedItem);

                        startActivity(intent);

                        break;
                    case 2:
                        selectedItem = "Jelly";
                        intent.putExtra("selectedFlavor", selectedItem);

                        startActivity(intent);

                        break;
                    case 3:
                        selectedItem = "Blueberry";
                        intent.putExtra("selectedFlavor", selectedItem);

                        startActivity(intent);

                        break;
                }
            }

        });
    }

}