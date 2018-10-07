package com.example.dhanu.onlineattendance;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class WifiActivity extends AppCompatActivity {
    public WifiManager wManager;
    public WifiInfo winfo;
    public String bssid,compare;
    ProgressBar pbar;
    TextView wt1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        pbar=(ProgressBar)findViewById(R.id.pbar);
        wt1=(TextView)findViewById(R.id.wt1);
        wManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wManager.setWifiEnabled(true);
        winfo=wManager.getConnectionInfo();
        bssid=winfo.getBSSID();
        compare="ac:5a:14:9f:e9:fc";
        if(bssid.compareTo(compare)==0)
        {
            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
            Intent i=new Intent(this,HomeActivity.class);
            startActivity(i);
            pbar.setEnabled(false);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_LONG).show();
            pbar.setEnabled(false);
            wt1.setText("Please Connect To an Authorized Wifi Network");

        }
    }
}
