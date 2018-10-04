package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class DescriptionActivity extends AppCompatActivity {

    TextView txt, txt1;
    ElegantNumberButton btn;
    String text;
    String [] ItemData;
    private String category="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Intent intent = getIntent();
        text = intent.getStringExtra(ItemsActivity.Extra_message);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
       // Log.d("---MSg",""+text);
        //Toast.makeText(getApplicationContext(),""+text,Toast.LENGTH_SHORT).show();

        ItemData=text.split("_");
        category = ItemData[3].toString();


//
//        Toast.makeText(this, ItemData[0], Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, ItemData[1], Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, ItemData[2], Toast.LENGTH_SHORT).show();

        txt = (TextView) findViewById(R.id.textView6);
        txt1 = (TextView) findViewById(R.id.textView7);

        txt.setText(ItemData[2]);
        txt1.setText(ItemData[1]);


        btn = (ElegantNumberButton) findViewById(R.id.myButton);
        btn.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = btn.getNumber();
                //Log.e("Num", num);
            }
        });
    }

    public void CartButton(View view)
    {
        ///Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();


        CartItems ct=new CartItems();
        ct.itemName=ItemData[0];
        Log.d("NAME", ItemData[0]);
        ct.itemQuantity=2;
        ct.itemPrice= Integer.valueOf(ItemData[1]);
        ct.itemName = (ItemData[2]).toString();
        ct.itemCat = ItemData[3].toString();

       // Log.d("PRICE", ItemData[1]);
        //Toast.makeText(getApplicationContext(),ItemData[0].toString()+" : "+ItemData[1].toString(),Toast.LENGTH_LONG).show();
        Cart.items.add(ct);

        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
      //  finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(DescriptionActivity.this, ItemsActivity.class);
        intent.putExtra("category",category);
        startActivity(intent);

    }


}
