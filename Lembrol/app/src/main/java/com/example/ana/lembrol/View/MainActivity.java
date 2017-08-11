package com.example.ana.lembrol.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ana.lembrol.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonSignId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignId = (Button) findViewById(R.id.signId);

        buttonSignId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, LembrolListActivity.class));
            }
        });

    }
}