package com.cs2340.spacetrader.views;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.cs2340.spacetrader.adapter.MarketItemAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import com.cs2340.spacetrader.model.Item;
import com.cs2340.spacetrader.R;
import com.cs2340.spacetrader.repository.Repository;

import android.widget.Button;

/**
 * View that represents the market with all its items for sale
 */
@SuppressWarnings("ALL")
public class MarketView extends AppCompatActivity implements OnClickListener {

    private TextView itemTotaltoEditText;
    private TextView itemTotalView;
    private TextView creditTotaltoEditText;
    private TextView marketMode;
    private ArrayList<Item> orders;

    private MarketItemAdapter setadpater;

    private Button switchButton;
    private Button buyorsellButton;

    /**
     * Overridden onCreate method initialize activity
     * @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        TextView selectedPlanet = findViewById(R.id.selected_planet_view);
        String planet_text = "You are in " +
                Repository.planetClass.getPlanetName().toString() + " Planet";
        selectedPlanet.setText(planet_text);

        marketMode = findViewById(R.id.market_mode_view);

        switchButton = findViewById(R.id.switch_button);
        switchButton.setOnClickListener(this);

        buyorsellButton = findViewById(R.id.make_item_change_button);
        buyorsellButton.setOnClickListener(this);

        Button toShipyardButton = findViewById(R.id.shipyard_button);
        toShipyardButton.setOnClickListener(this);

        Button doneButton = findViewById(R.id.done_button);
        doneButton.setOnClickListener(this);

        itemTotaltoEditText = findViewById(R.id.total_num_view);
        creditTotaltoEditText = findViewById(R.id.credit_num_view);
        String m1 = "" + Repository.playerClass.getCredits();
        creditTotaltoEditText.setText(m1);
        itemTotalView = findViewById(R.id.item_total);

        if (!Repository.isitBuying) {
            switchButton.setText("Switch to Buy");
            buyorsellButton.setText("Sell");
            itemTotalView.setText("Total Sale   ");
            marketMode.setText("Sell Items");
        } else {
            switchButton.setText("Switch to Sell");
            buyorsellButton.setText("Buy");
            itemTotalView.setText("Total Expense   ");
            marketMode.setText("Buy Items");
        }

        ListView storedOrders = findViewById(R.id.selected_item_list);

        orders = getListItemData();
        Repository.setItemsList(orders);
        MarketItemAdapter adapter = new MarketItemAdapter(this, orders);
        setadpater = adapter;
        storedOrders.setAdapter(setadpater);
        setadpater.registerDataSetObserver(observer);
    }

    /**
     * Calculates the total price of selected items
     * @return int of itemTotal
     */
    public int calculateItemTotal(){
        int itemTotal = 0;
        for (Item order : orders){
            itemTotal += order.getPrice() * order.getQuantityChange();
        }
        return itemTotal;
    }

    /**
     * Calculates the total credits that the player owns
     * @return int of calculateCreditTotal
     */
    public int calculateCreditTotal(){

        if (Repository.isitBuying) {
            return Integer.parseInt((String)creditTotaltoEditText.getText()) - calculateItemTotal();
        } else {
            return Integer.parseInt((String)creditTotaltoEditText.getText()) + calculateItemTotal();
        }
    }

    /**
     * Displays the calculated total price of selected items
     */
    public void setItemTotal(){
        String m2 = "" + calculateItemTotal();
        itemTotaltoEditText.setText(m2);
    }

    /**
     * Displays the total credits that the player owns
     */
    public void setCreditTotal() {
        String m3 = "" + calculateCreditTotal();
        creditTotaltoEditText.setText(m3);}

    /**
     * Resets all the quantities of selected items to zero after purchasing or selling
     */
    public void resetItemTotal() {
        for (Item order : orders) {
            order.setQuantityChange(0);
        }
        itemTotaltoEditText.setText("0");
    }

    /**
     * Updates the change in the number of items owned in cargo
     */
    public void updateIteminCargo() {
        for (Item order : orders) {
            order.updateQuantity();
        }
    }

    final DataSetObserver observer = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            setItemTotal();
            //setCreditTotal();
        }
    };

    /**
     * Creates and returns a list of Item objects to use in MarketItemAdapter
     * @return a list of Item objects
     */
    private ArrayList<Item> getListItemData(){
        ArrayList<Item> listViewItems = new ArrayList<>();
        ArrayList<String> itemName =
                new ArrayList<>(Arrays.asList("Water", "Furs", "Food",
                        "Ore", "Games", "Firearms", "Medicine", "Machines",
                        "Narcotics", "Robots"));
        ArrayList<Integer> itemPrice =
                new ArrayList<>(Arrays.asList(30, 250, 100, 350,
                        250, 1250, 650, 900, 3500, 5000));
        int i = 0;
        while (i < 10) {
            String name = itemName.get(i);
            listViewItems.add(new Item(name, itemPrice.get(i), Repository.itemMap.get(name)));
            i++;
        }
        return listViewItems;
    }

    @Override
    public void onClick (View v) {

        if (v.getId() == R.id.switch_button) {

            if (Repository.isitBuying) {

                switchButton.setText("Switch to Buy");
                buyorsellButton.setText("Sell");
                itemTotalView.setText("Total Sale   ");
                marketMode.setText("Sell Items");

                Repository.isitBuying = false;
                resetItemTotal();
                setadpater.notifyDataSetChanged();


            } else {
                switchButton.setText("Switch to Sell");
                buyorsellButton.setText("Buy");
                itemTotalView.setText("Total Expense   ");
                marketMode.setText("Buy Items");

                Repository.isitBuying = true;
                resetItemTotal();
                setadpater.notifyDataSetChanged();
            }

        } else if (v.getId() == R.id.make_item_change_button) {
            if (Repository.isitBuying) {
                if (Integer.parseInt((String) creditTotaltoEditText.getText()) >=
                        calculateItemTotal()) {
                    if (calculateItemTotal() == 0) {
                        String m = "You didn't select any items";
                        Toast.makeText(getApplicationContext(), m, Toast.LENGTH_SHORT).show();
                    } else {
                        Repository.setTransactionHistory(true);
                        String m = "You successfully purchased the items";
                        Toast.makeText(getApplicationContext(), m, Toast.LENGTH_SHORT).show();
                        setCreditTotal();
                        resetItemTotal();
                        updateIteminCargo();
                        setadpater.notifyDataSetChanged();


                    }
                }
            } else {
                // selling
                if (Integer.parseInt((String) itemTotaltoEditText.getText()) > 0) {
                    String m = "You successfully sold your items";
                    Toast.makeText(getApplicationContext(), m, Toast.LENGTH_SHORT).show();
                    setCreditTotal();
                    resetItemTotal();
                    updateIteminCargo();
                    setadpater.notifyDataSetChanged();


                } else if (calculateItemTotal() == 0) {
                    String m = "You didn't select any items";
                    Toast.makeText(getApplicationContext(), m, Toast.LENGTH_SHORT).show();

                }
            }
        } else if (v.getId() == R.id.shipyard_button) {
            startActivity(new Intent(this, ShipyardView.class));

        } else if (v.getId() == R.id.done_button) {
            startActivity(new Intent(this, PlanetsView.class));
        }
    }
}