package com.example.dhanu.onlineattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Button extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
    }
    public void attendence(View view){
        Intent intent=new Intent(this,First.class);
        startActivity(intent);
    }
    public void results(View view)
    {
        Intent intent1=new Intent(this,Second.class);
        startActivity(intent1);

    }
    public void mark(View view){
        Intent intent2=new Intent(this,Third.class);
        startActivity(intent2);

    }
}
