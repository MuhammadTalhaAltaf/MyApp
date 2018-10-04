package com.example.user.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Constructor;

public class CategoriesActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    Button btn;
    String buttonText1;
   // public  static final String Extra_message="com.example.user.myapplication.ItemsActivity";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        constraintLayout = (ConstraintLayout)findViewById(R.id.layout_Category);
        constraintLayout.setBackgroundResource(R.drawable.background);

    }

    public void category_button(View view)
    {

        Button btn = (Button)view;
        buttonText1 = btn.getText().toString();
      //  Toast.makeText(getApplicationContext(),buttonText1.toString(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ItemsActivity.class);

        intent.putExtra("category",buttonText1);

        startActivity(intent);
       // finish();
//
//  btn = (Button) findViewById(R.id.button3);
//        String BBQbtn = (String) btn.getText();
    }
    public void onClick(View v){


    }
}

