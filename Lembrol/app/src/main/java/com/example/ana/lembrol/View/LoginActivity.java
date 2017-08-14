package com.example.ana.lembrol.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ana.lembrol.R;
import com.example.ana.lembrol.Config.FirebaseConfig;
import com.example.ana.lembrol.Model.User;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button buttonLogin;
    private FirebaseAuth authentication;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //validateUserLoggerIn();

        email = (EditText) findViewById(R.id.loginEmailId);
        password = (EditText) findViewById(R.id.loginPasswordId);
        buttonLogin = (Button) findViewById(R.id.bt_Login);

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                user = new User();
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());

                //validateLogin();

            }

        });

    }

   // private  void validateUserLoggerIn(){

   //     authentication = FirebaseConfig.getAuthenticationFirebase();
   //     if (authentication.getCurrentUser() != null){
   //         openMainActivity();
   //     }

  //  }

    public void openMainActivity(){

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    public void openUserRegister(View view){

        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }

}
