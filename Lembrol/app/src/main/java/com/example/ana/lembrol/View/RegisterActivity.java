package com.example.ana.lembrol.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ana.lembrol.Model.User;
import com.example.ana.lembrol.R;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private Button buttonRegister;

    private User user;
    private FirebaseAuth authentication;

    public String erroException = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.registerNameId);
        email = (EditText) findViewById(R.id.registerEmailId);
        password = (EditText) findViewById(R.id.registerPasswordId);
        buttonRegister = (Button) findViewById(R.id.bt_Register);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                user.setName( name.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                //registerUser();

            }
        });
    }

    }

