package com.example.dhanu.onlineattendance;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.media.Image;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;
    private DatabaseReference databaseReference;
    public String s1,s2,s4;
    public Object o;
    public int c;
    EditText et;

    public FingerprintHandler(Context context){

        this.context = context;

    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){

        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

        this.update("There was an Authentication Error. " + errString, false);

    }

    @Override
    public void onAuthenticationFailed() {

        this.update("Authentication Failed. ", false);

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

        this.update("Error: " + helpString, false);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

        this.update("Attendance marked successfully", true);
        et=(EditText)((Activity)context).findViewById(R.id.et);
        databaseReference = FirebaseDatabase.getInstance().getReference("students");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child1 : children) {
                    s1 = et.getText().toString().toUpperCase().trim();
                    s4 = child1.child("id").getValue().toString();
                    if (s4.matches(s1))
                    {
                        o = child1.child("count").getValue();
                        s2 = o.toString().trim();
                        c = Integer.parseInt(s2);
                        c++;
                        s2 = String.valueOf(c);
                        o = (Object) s2;
                        databaseReference.child(s4).child("count").setValue(o);
                        Toast.makeText(context.getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                        System.exit(0);

                    } else {
                        Toast.makeText(context.getApplicationContext(), "Enter Valid Id Number", Toast.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context.getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void update(String s, boolean b) {

        TextView paraLabel = (TextView) ((Activity)context).findViewById(R.id.paraLabel);
        ImageView imageView = (ImageView) ((Activity)context).findViewById(R.id.fingerprintImage);


        paraLabel.setText(s);

        if(b == false){

            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else {

            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            imageView.setImageResource(R.mipmap.ic_launcher);

        }

    }
}
