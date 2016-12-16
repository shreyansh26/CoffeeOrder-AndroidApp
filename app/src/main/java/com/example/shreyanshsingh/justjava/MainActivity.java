package com.example.shreyanshsingh.justjava;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int numberofCoffees = 1;

    public void submitOrder(View view) {

        int price = calculatePrice(numberofCoffees);
        displayMessage(createOrderSummary(price));

    }

    public void increment(View view) {

        if(numberofCoffees<=99)
        display(++numberofCoffees);
        else {display(100);
            Toast.makeText(MainActivity.this, "You cannot order more than 100 coffees", Toast.LENGTH_SHORT).show();}


    }

    public void decrement(View view) {

        if(numberofCoffees>1)
        display(--numberofCoffees);
        else {display(1);
            Toast.makeText(MainActivity.this, "You cannot order less than 1 coffee", Toast.LENGTH_SHORT).show();}

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
   /* private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }*/

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        //      TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//        orderSummaryTextView.setTextSize(18);
//        orderSummaryTextView.setTextColor(Color.BLACK);
        EditText nameInput = (EditText) findViewById(R.id.name_text);
        String name = nameInput.getText().toString();
        String subject="Coffee Order for "+name;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int quantity) {
        int price = quantity * 5;
        return price;
    }


    public String createOrderSummary(int price) {

        EditText nameInput = (EditText) findViewById(R.id.name_text);
        String name = nameInput.getText().toString();
        String summary = getString(R.string.order_name,name) + "\n";

        CheckBox checkBox = (CheckBox) findViewById(R.id.notify_me_checkbox_cream);
        if (checkBox.isChecked()) {
            summary += "Add Whipped Cream?: " + " Yes" + "\n";
            price += 1*numberofCoffees;
        } else
            summary += "Add Whipped Cream?: " + " No" + "\n";

        CheckBox checkBox2 = (CheckBox) findViewById(R.id.notify_me_checkbox_chocolate);
        if (checkBox2.isChecked()) {
            summary += "Add Chocolate?: " + " Yes" + "\n";
            price += 2*numberofCoffees;
        } else
            summary += "Add Chocolate?: " + " No" + "\n";

        summary += "Quantity: " + numberofCoffees;
        summary += "\n" + getString(R.string.total)+": $" + price;
        //summary += "\nThank You!!";
        return summary;


    }


}

