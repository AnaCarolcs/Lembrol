package com.example.ana.lembrol.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.ana.lembrol.Config.FirebaseConfig;
import com.example.ana.lembrol.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogout;
    private FirebaseAuth authentication;
    private Toolbar toolbar;
    private DatabaseReference firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  buttonSignId = (Button) findViewById(R.id.signId);

        buttonSignId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, LembrolListActivity.class));
            }
        });*/

        openFragment();

        authentication = FirebaseConfig.getAuthenticationFirebase();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Lembrol");
        setSupportActionBar(toolbar);
    }

    public void openFragment(){
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rl_fragmentId, new TitleListFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
