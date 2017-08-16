package com.lembrol.ana.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.lembrol.ana.R;

import java.util.ArrayList;
import java.util.List;

public class ListTitleFragment extends android.app.Fragment {

    private TitleAdapter adapter;

    List<String> titlesReminder;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerTitle;
    private RecyclerView mRecyclerView;

    public ListTitleFragment() {
    }

   @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       //recuperar contatos do firebase
       Preference preference = new Preference(getActivity());
       String identifierUser = preference.getIdentifier();
       firebase = FirebaseConfig.getFirebase()
               .child("user")
               .child(identifierUser);


       //Listener para recuperar titles
       valueEventListenerTitle = new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {

               //Limpar lista
               titlesReminder.clear();

               //Listar Titles
               for(DataSnapshot datas: dataSnapshot.getChildren()){

                   String titleKey = datas.getKey();
            //       Reminder reminder = datas.getValue(Reminder.class);
                   titlesReminder.add( titleKey );

               }
               adapter = new TitleAdapter(titlesReminder);
               mRecyclerView.setAdapter(adapter);

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       };

       firebase.addValueEventListener( valueEventListenerTitle );

   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_title, container, false);

        titlesReminder = new ArrayList<>();
        //titlesReminder.add(new Reminder("Titulo1", "Qualquer coisa"));
        //titlesReminder.add(new Reminder("Titulo2", "Qualquer coisa"));
        //titlesReminder.add(new Reminder("Titulo3", "Qualquer coisa"));

        adapter = new TitleAdapter(titlesReminder);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layout);

        mRecyclerView.setAdapter(adapter);


        return view;
    }

}