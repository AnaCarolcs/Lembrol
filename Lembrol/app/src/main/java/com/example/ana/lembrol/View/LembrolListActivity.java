package com.example.ana.lembrol.View;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ana.lembrol.R;

import java.util.ArrayList;

public class LembrolListActivity extends AppCompatActivity {

    private EditText textList;
    private Button addButton;
    private ListView list;
    private SQLiteDatabase dataBase;

    private ArrayList<Integer> idReminder;
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

            //Lista
            list = (ListView) findViewById(R.id.listId);

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

            list.setLongClickable(true);
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removeReminder( idReminder.get( position ) );
                    return true;
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

            //Criar adaptador
            item = new ArrayList<String>();
            idReminder = new ArrayList<Integer>();
            itemAdapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    item);

            list.setAdapter(itemAdapter);

            //listar as tarefas
            cursor.moveToFirst();
            while (cursor != null){

                item.add(cursor.getString(reminderTable));
                idReminder.add(Integer.parseInt(cursor.getString(idTable)));
                
                cursor.moveToNext();

            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void removeReminder(Integer id){

        try{

            dataBase.execSQL("DELETE FROM toDoList WHERE id="+id);
            recoverReminder();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
