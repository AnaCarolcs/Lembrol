package com.lembrol.ana.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.lembrol.ana.Config.FirebaseConfig;
import com.lembrol.ana.Config.Preference;
import com.lembrol.ana.Model.Reminder;
import com.lembrol.ana.Model.Title;
import com.lembrol.ana.R;

import java.util.ArrayList;

public class ListTitleFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Reminder> titlesReminder;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerTitle;

    public ListTitleFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener( valueEventListenerTitle );
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerTitle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Instância objetos
        titlesReminder = new ArrayList<>();
        //titles.add("Escola");
        //titles.add("Trabalho");
        //titles.add("Casa");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_title, container, false);

        //Monta listview e adapter
        listView = (ListView) view.findViewById(R.id.lv_title_list_id);

      //  adapter = new ArrayAdapter(getActivity(),R.layout.title_list, titles)
        adapter = new TitleAdapter(getActivity(), titlesReminder);


        listView.setAdapter(adapter);








        //recuperar contatos do firebase
        Preference preference = new Preference(getActivity());
        String identifierUser = preference.getIdentifier();
        firebase = FirebaseConfig.getFirebase()
                .child("Titles")
                .child(identifierUser);

        //Listener para recuperar titles
        valueEventListenerTitle = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Limpar lista
                titlesReminder.clear();

                //Listar Titles
                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    //Title title = datas.getValue(Title.class);
                    Reminder reminder = datas.getValue(Reminder.class);
                    titlesReminder.add( reminder );

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        firebase.addValueEventListener( valueEventListenerTitle );









        return view;
    }

}