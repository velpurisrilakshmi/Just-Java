package com.example.srilakshmi.justjava; /**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=2;
    private boolean whippedcream1;
    private boolean chocolate1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
    public void increment(View view) {
       if(quantity==100)
       {
           Toast.makeText(this,"you cant order coffee more than 100 ",Toast.LENGTH_SHORT).show();
           return;
       }
        quantity = quantity + 1 ;
        displayQuantity(quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        /**int quantity=5;
        display( quantity);*/


        CheckBox checkbox=(CheckBox)findViewById(R.id.checkbox_id);
        boolean haschecked=checkbox.isChecked();
        String  priceMessage;
        EditText text=(EditText)findViewById(R.id.field_name);
        String name=text.getText().toString();




        CheckBox checkbox1=(CheckBox)findViewById(R.id.checkbox1_id);
        boolean haschecked1=checkbox1.isChecked();
        String  priceMessage1;

        int  price=calculatePrice(whippedcream1,chocolate1);
        priceMessage = createOrderSummary(name,price,haschecked,haschecked1);

        priceMessage1 = createOrderSummary(name,price,haschecked,haschecked1);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }


    public void decrement(View view) {
        if(quantity==1)
        {
            Toast.makeText(this,"you cant order coffee less than 1 ",Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity - 1 ;
        displayQuantity(quantity);

    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // First calculate the price of one cup of coffee
        int basePrice = 5;

        // If the user wants whipped cream, add $1 per cup
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        // If the user wants chocolate, add $2 per cup
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        // Calculate the total order price by multiplying by the quantity
        return quantity * basePrice;
    }
    public String createOrderSummary(String name,int price,boolean addcheck,boolean addcheck1){


        String priceMessage = "NAME="+name;
        priceMessage+= "\nadd whippedcream?" + addcheck;
        priceMessage+= "\nadd chocolate?" + addcheck1;
        priceMessage+="\nquantity="+quantity;
        priceMessage+="\nTotal=$" +price;
        priceMessage+="\nTHANK YOU!";
        return priceMessage;


    }

}

