package com.example.dhanu.onlineattendance;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    private static int SPLASH_TIME_OUT=4000;
    public WifiManager wManager;
    public WifiInfo winfo;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wManager.setWifiEnabled(true);
        new Handler().postDelayed(new Runnable(){
            @Override
            public  void run()
            {
                Intent homeIntent=new Intent(MainActivity.this,WifiActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
