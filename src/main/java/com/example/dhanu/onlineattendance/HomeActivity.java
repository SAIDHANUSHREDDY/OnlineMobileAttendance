package com.example.dhanu.onlineattendance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    EditText loginemail,loginpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth=FirebaseAuth.getInstance();
        findViewById(R.id.signupnavigate).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
        loginemail=(EditText)findViewById(R.id.loginemail);
        loginpassword=(EditText)findViewById(R.id.loginpassword);

    }
    private void loginUser()
    {
        String email = loginemail.getText().toString().trim();
        String password = loginpassword.getText().toString().trim();
        if (email.isEmpty()) {
            loginemail.setError("Email is Required");
            loginemail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            loginpassword.setError("Password is Required");
            loginpassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginemail.setError("Please a enter a valid Email");
            loginemail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            loginpassword.setError("Minimum length of password must be 6");
            loginpassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Intent i=new Intent(HomeActivity.this,Button.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.signupnavigate:
                    startActivity(new Intent(this,RegisterActivity.class));
                    break;
            case R.id.login:
                   loginUser();
                break;
        }

    }
}
