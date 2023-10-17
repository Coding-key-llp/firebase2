package com.example.simple_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserReg extends AppCompatActivity {

    EditText regEmail,regPass;
    Button RegBtn;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        //connecting to front-end
        regEmail=findViewById(R.id.RegEmail);
        regPass=findViewById(R.id.regPass);

        RegBtn=findViewById(R.id.RegBtn);

        //Firebase objects

        mAuth=FirebaseAuth.getInstance();

    }

    public void toLogin(View view){
        Intent intent=new Intent(UserReg.this,UserLog.class);
        startActivity(intent);
        finish();
    }

    public void Register(View v){
        String email,pass;
        email=regEmail.getText().toString();
        pass=regPass.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(UserReg.this, "Authentication Successfull.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(UserReg.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });

    }
}