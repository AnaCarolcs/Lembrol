package com.example.ana.lembrol.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ana.lembrol.Config.FirebaseConfig;
import com.example.ana.lembrol.Config.Preference;
import com.example.ana.lembrol.Model.Reminder;
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

        //  progressBar.setVisibility(View.VISIBLE);

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

        // progressBar.setVisibility(View.GONE);
        //  onBackPressed();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_list_id:
                newItemList();
                return true;

            case R.id.bt_Logout:
                userLogout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void newItemList(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        //Configuração do Dialog
        alertDialog.setTitle("Nova lista");
        alertDialog.setMessage("Digite o nome da nova lista");
        alertDialog.setCancelable(false);

        final EditText editText = new EditText(MainActivity.this);
        alertDialog.setView(editText);


        //Configura botões
        alertDialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String titleList = editText.getText().toString();

                //Validar se titulo foi digitado
                if(titleList.isEmpty()){
                    Toast.makeText(MainActivity.this, "Digite um Título", Toast.LENGTH_LONG).show();
                }else{

                    //Recuperar os dados do titleList


                    //Recupera identificador do usuario logado
                    Preference preference = new Preference(MainActivity.this);
                    String userIdentifier = preference.getIdentifier();

                    firebase = FirebaseConfig.getFirebase();
                    firebase = firebase.child("Titles")
                            .child(userIdentifier)
                            .child(titleList)/*.setValue()*/;

                    Reminder reminder = new Reminder();
                    //futuramente setar aqui os dados dos lembretes
                    reminder.setReminderBody("");

                    firebase.setValue(reminder);

                }

            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.create();
        alertDialog.show();

    }

    private void userLogout(){
        authentication.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
