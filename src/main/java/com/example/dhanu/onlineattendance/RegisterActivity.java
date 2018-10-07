package com.example.dhanu.onlineattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    EditText registeremail,registerpassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        registeremail=(EditText)findViewById(R.id.registeremail);
        registerpassword=(EditText)findViewById(R.id.registerpassword);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.login_navigate).setOnClickListener(this);
    }
    private void registerUser()
    {
        String email = registeremail.getText().toString().trim();
        String password = registerpassword.getText().toString().trim();
        if (email.isEmpty()) {
            registeremail.setError("Email is Required");
            registeremail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            registerpassword.setError("Password is Required");
            registerpassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registeremail.setError("Please a enter a valid Email");
            registeremail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            registerpassword.setError("Minimum length of password must be 6");
            registerpassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(getApplicationContext(),"You are already Registered",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                    registerUser();
                  break;
            case R.id.login_navigate:
                startActivity(new Intent(this,HomeActivity.class));
                break;
        }

    }
}
