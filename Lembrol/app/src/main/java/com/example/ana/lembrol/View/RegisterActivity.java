package com.example.ana.lembrol.View;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ana.lembrol.Config.Base64Custom;
import com.example.ana.lembrol.Config.Preference;
import com.example.ana.lembrol.Model.User;
import com.example.ana.lembrol.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import com.example.ana.lembrol.Config.FirebaseConfig;

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
                registerUser();

            }
        });
    }

    private void registerUser(){

        authentication = FirebaseConfig.getAuthenticationFirebase();
        authentication.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(RegisterActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Cadastro efetuado com sucesso",
                                            Toast.LENGTH_LONG).show();

                                    FirebaseUser userFirebase =task.getResult().getUser();

                                    String userIdentifier = Base64Custom.code64Base(user.getEmail());
                                    user.setId(userIdentifier);
                                    user.save();

                                    Preference preference = new Preference(RegisterActivity.this);
                                    preference.dataSave(userIdentifier);

                                    //openUserLogin();

                                } else {

                                    try{
                                        throw task.getException();

                                    } catch (FirebaseAuthWeakPasswordException e) {
                                        erroException = "Digite uma senha mais forte, contendo mais caracteres e com letras e números";
                                    } catch (FirebaseAuthInvalidCredentialsException e){
                                        erroException = "O email informado é invalido";
                                    } catch (FirebaseAuthUserCollisionException e){
                                        erroException = "Esse email já está cadastrado";
                                    } catch (Exception e){
                                        erroException = "Erro ao efetuar o cadastro";
                                    }

                                    Toast.makeText(RegisterActivity.this, "Erro: " + erroException,
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });

    }



    }

