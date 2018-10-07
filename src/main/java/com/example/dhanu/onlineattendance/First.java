package com.example.dhanu.onlineattendance;

import android.support.v4.content.ContextCompat;
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


public class First extends AppCompatActivity {
    private DatabaseReference databaseReference;
    public String s1,s2,s4;
    public Object o,id;
    public int i=0;
    EditText et;
    TextView tv;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        et=(EditText)findViewById(R.id.et);
        tv=(TextView)findViewById(R.id.tv);
        tv1=(TextView)findViewById(R.id.tv1);
        databaseReference= FirebaseDatabase.getInstance().getReference("students");

    }
    public void getattendance(View v) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child1 : children)
                {
                    s1 = et.getText().toString().toUpperCase().trim();
                    s4 = child1.child("id").getValue().toString();
                    if (s4.matches(s1)) {
                        o = child1.child("count").getValue();
                        s2 = o.toString().trim();
                        tv.setText(s4);
                        tv1.setText(s2);
                        i=1;
                    }
                }
                if(i==0)
                {
                    Toast.makeText(getApplicationContext(), "Enter valid id number", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
