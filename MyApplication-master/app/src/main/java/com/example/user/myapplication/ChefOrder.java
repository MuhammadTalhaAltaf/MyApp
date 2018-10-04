package com.example.user.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ChefOrder extends AppCompatActivity {

    private FirebaseAuth auth;
    DatabaseReference databaseItems,db;
    TextView txtOrders;
    Button btnOrders, logOutBtn;

    ListView ListViewItems;

    List<CartItems> itemList;

    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_order);

        btnOrders = (Button)findViewById(R.id.btnShow);
//        logOutBtn = (Button)findViewById(R.id.logoutPopup);
        txtOrders = (TextView)findViewById(R.id.txtOrders);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        databaseItems = FirebaseDatabase.getInstance().getReference("Orders");

//        logOutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

//        userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
//        databaseItems = FirebaseDatabase.getInstance().getReference("Orders");
//
//
//        if(userEmail.equals("navaid@gmail.com")) {
//            Toast.makeText(getApplicationContext(),"Cheff Navaid is online ",Toast.LENGTH_LONG).show();
//        }else if(userEmail.equals("talha@gmail.com")){
//            Toast.makeText(getApplicationContext(),"Cheff Talha is online",Toast.LENGTH_LONG).show();
//        }else if(userEmail.equals("essa@gmail.com")){
//            Toast.makeText(getApplicationContext(),"Cheff Essa is online",Toast.LENGTH_LONG).show();
//        }

        ListViewItems = (ListView) findViewById(R.id.dataListView);

        itemList = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  R.id.menuLogout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, Login.class));

                break;

        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

//        Toast.makeText(getApplicationContext(),"In start",Toast.LENGTH_LONG).show();

        databaseItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                itemList.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                    //Item item = itemSnapshot.getValue(Item.class);
                    CartItems item = itemSnapshot.getValue(CartItems.class);
                    itemList.add(item);
                }
                    Order();
//                ItemList adapter = new ItemList(ChefOrder.this, itemList);
//
//                ListViewItems.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
    private String cheff1,cheff2,cheff3;
    public void Orders(View view){
//        for(int i=0;i<itemList.size();i++){
//            show+=itemList.get(i).itemName()+"\n";
//        }
//        txtOrders.setText(show);
    }

    public void Order(){
        for(int i=0;i<itemList.size();i++){
            if(itemList.get(i).itemCat.equals("Fast Food")) {
                cheff1 += itemList.get(i).itemName + "\n";
            }
            if(itemList.get(i).itemCat.equals("BBQ")){
                cheff2 += itemList.get(i).itemName + "\n";
            }
            if(itemList.get(i).itemCat.equals("Sea Food")){
                cheff3 += itemList.get(i).itemName + "\n";
            }
        }
        if(userEmail.equals("navaid@gmail.com")) {
            Toast.makeText(getApplicationContext(),"Cheff Navaid is online ",Toast.LENGTH_LONG).show();
            txtOrders.setText(cheff3);
        }else if(userEmail.equals("talha@gmail.com")){
            Toast.makeText(getApplicationContext(),"Cheff Talha is online",Toast.LENGTH_LONG).show();
            txtOrders.setText(cheff2);
        }else if(userEmail.equals("essa@gmail.com")){
            Toast.makeText(getApplicationContext(),"Cheff Essa is online",Toast.LENGTH_LONG).show();
            txtOrders.setText(cheff1);
        }

    }

}


