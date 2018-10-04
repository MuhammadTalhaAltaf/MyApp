package com.example.user.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    TextView txt;
    DatabaseReference databaseItems;
    public static int totalPrice=0;

    List<CartItems> itemsList;


    public static List<CartItems> items=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ListView lv = (ListView) findViewById(R.id.lv);

        ArrayAdapter<CartItems> adapter = new ArrayAdapter<CartItems>(this,
                android.R.layout.simple_list_item_1, items);

        databaseItems = FirebaseDatabase.getInstance().getReference("Orders");

        itemsList = new ArrayList<>();
        lv.setAdapter(adapter);

        txt=(TextView)findViewById(R.id.textView2);
        calculatePrice();
        //Toast.makeText(this, totalPrice, Toast.LENGTH_SHORT).show();
        txt.setText(String.valueOf(totalPrice));
        //txt.setText(();
        totalPrice=0;


    }

    public void setPriority(){
        //int priority = items.get(items.size()-1).itemPriority;
        //items == jo order abhi place kia hai
        //itemslist == jo order place ho chuky hain
        //items pehla index utjaya and loop chalaya reverse itemslist pr, if item 0 ki cat == itemslist
        int index=0;
        int index_orders= itemsList.size();
       // Toast.makeText(getApplicationContext(),""+itemsList.size()+" :: "+items.size(),Toast.LENGTH_LONG).show();
        Log.d("MSGGG",""+itemsList.size()+" :: "+items.size());
        try {
            for (int i = itemsList.size()-1; i >=0; i--) {
                if(items.size() <= i) {
                    if (itemsList.get(i).itemCat.equals(items.get(index).itemCat)) {
                        items.get(index).itemPriority = itemsList.get(i).itemPriority + 1;
                        //Toast.makeText(getApplicationContext() ,""+items.get(index).itemPriority+" : "+itemsList.get(index).itemPriority, Toast.LENGTH_SHORT).show();
                        Log.d("MSGGGGGGGGGG",""+items.get(index).itemPriority+" : "+itemsList.get(index).itemPriority);
                        index_orders--;

                    }
                    break;
                }
            }

        }catch (Exception e){
            Log.d("MSGGGG",""+e);
//            Log.d("MSGGGGGGGGGG",""+items.get(index).itemPriority+" : "+itemsList.get(index).itemPriority);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                itemsList.clear();

                for(DataSnapshot itemSnapshot : dataSnapshot.getChildren()){

                    CartItems items = itemSnapshot.getValue(CartItems.class);
                    itemsList.add(items);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void finalOrder(View view) {
        databaseItems = FirebaseDatabase.getInstance().getReference("Orders");
        setPriority();
        for(int j=0;j<items.size();j++){
            if(items.get(j).itemPriority == null){
                items.get(j).itemPriority = 1;
            }
        }
        for(int i=0;i<items.size();i++){
            //items.get(i).itemPriority = 1;
           // Toast.makeText(getApplicationContext(),items.get(i).itemCat,Toast.LENGTH_SHORT).show();
        String id =databaseItems.push().getKey();
            databaseItems.child(id).setValue(items.get(i));
                databaseItems.child(id).setValue(items.get(i));
        }
        Toast.makeText(getApplicationContext(),"Your order has been placed",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Cart.this,CategoriesActivity.class);
                startActivity(intent);
            }
        },1500);

    }

    public void CategoriesButton(View view)
    {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);


    }

    public void calculatePrice(){
        for(int i=0;i<items.size();i++){
            totalPrice+= items.get(i).itemPrice;
        }
    }
}
