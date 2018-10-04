package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItem extends AppCompatActivity {


    EditText editTextName, editTextPrice, editTextDesc, editTextCategory;
    Button buttonAddItem;

    DatabaseReference databaseItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

      //  Toast.makeText(getApplicationContext(), "Add Item", Toast.LENGTH_SHORT).show();

        editTextName = (EditText) findViewById(R.id.editText);
        editTextPrice = (EditText) findViewById(R.id.editText16);
        editTextDesc = (EditText) findViewById(R.id.editText13);
        editTextCategory = (EditText) findViewById(R.id.editText2);
        buttonAddItem = (Button) findViewById(R.id.button3);

//        buttonAddItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addItem();
//            }
//        });

    }

    public void addItem1(View view) {
        String name = editTextName.getText().toString().trim();
        String price = editTextPrice.getText().toString().trim();
        String desc = editTextDesc.getText().toString().trim();
        String cat = editTextCategory.getText().toString().trim();

        Toast.makeText(getApplicationContext()," Desc "+desc,Toast.LENGTH_SHORT).show();
        if (cat.equals("BBQ")) {
            databaseItems = FirebaseDatabase.getInstance().getReference("BBQ");
            String id = databaseItems.push().getKey();

            Item item = new Item(id, name, price, desc, cat);

            databaseItems.child(id).setValue(item);
           //Toast.makeText(getApplicationContext(), "In BBQ", Toast.LENGTH_LONG).show();
        } else if (cat.equals("Fast Food")) {
            databaseItems = FirebaseDatabase.getInstance().getReference("Fast Food");
            String id = databaseItems.push().getKey();

            Item item = new Item(id, name, price, desc, cat);

            databaseItems.child(id).setValue(item);
          //  Toast.makeText(getApplicationContext(), "In Fast Food", Toast.LENGTH_LONG).show();

        } else if (cat.equals("Chinese")) {
            databaseItems = FirebaseDatabase.getInstance().getReference("Chinese");
            String id = databaseItems.push().getKey();

            Item item = new Item(id, name, price, desc, cat);

            databaseItems.child(id).setValue(item);
           // Toast.makeText(getApplicationContext(), "In Chinese", Toast.LENGTH_LONG).show();

        } else if (cat.equals("Sea Food")) {
            databaseItems = FirebaseDatabase.getInstance().getReference("Sea Food");
            String id = databaseItems.push().getKey();

            Item item = new Item(id, name, price, desc, cat);

            databaseItems.child(id).setValue(item);
         //   Toast.makeText(getApplicationContext(), "In Sea Food", Toast.LENGTH_LONG).show();

        } else {

           // Toast.makeText(getApplicationContext(), "In Else", Toast.LENGTH_LONG).show();
        }

//        if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(price) || !TextUtils.isEmpty(desc) || !TextUtils.isEmpty(cat)){
//
//
//            Toast.makeText(this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
//        }
//        else{
//
//            Toast.makeText(this, "Enter details first", Toast.LENGTH_SHORT).show();
//        }
    }
}
