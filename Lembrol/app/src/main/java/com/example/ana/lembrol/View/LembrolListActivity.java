package com.example.ana.lembrol.View;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ana.lembrol.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LembrolListActivity extends AppCompatActivity {

    private EditText textList;
    private Button addButton;
    private ListView list;
    private SQLiteDatabase dataBase;

    private ArrayAdapter<String> itemAdapter;
    private ArrayList<String> item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembrol_list);

        try {

            //Recuperar componentes
            textList = (EditText) findViewById(R.id.textListId);
            addButton = (Button) findViewById(R.id.addButtonId);


            //Banco de Dados
            dataBase = openOrCreateDatabase("appLembrol", MODE_PRIVATE, null);

            //tabela tarefas
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS toDoList(id INTEGER PRIMARY KEY AUTOINCREMENT, reminder VARCHAR) ");

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newText = textList.getText().toString();
                    salveReminder(newText);
                }
            });

            //Recuperar tarefas
            recoverReminder();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void salveReminder(String text){

        try{

            if (text.equals("")){
                Toast.makeText(LembrolListActivity.this, "Digite uma tarefa", Toast.LENGTH_SHORT).show();
            } else {
                dataBase.execSQL("INSERT INTO toDoList (reminder) VALUES ('" + text + "')");
                Toast.makeText(LembrolListActivity.this, "Tarefa Salva", Toast.LENGTH_SHORT).show();
                recoverReminder();
                textList.setText("");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void recoverReminder(){
        try {

            //Recuperar as tarefas
            Cursor cursor = dataBase.rawQuery("SELECT * FROM toDoList ORDER BY id DESC", null);

            //Recuperar os ids das colunas
            int idTable = cursor.getColumnIndex("id");
            int reminderTable = cursor.getColumnIndex("reminder");

            //Lista
            list = (ListView) findViewById(R.id.listId);

            //Criar adaptador
            item = new ArrayList<String>();
            itemAdapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    item);

            list.setAdapter(itemAdapter);

            //listar as tarefas
            cursor.moveToFirst();
            while (cursor != null){
                item.add(cursor.getString(reminderTable));
                cursor.moveToNext();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
