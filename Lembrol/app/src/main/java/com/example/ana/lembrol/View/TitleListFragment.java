package com.example.ana.lembrol.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ana.lembrol.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class TitleListFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> titles;
    //private DatabaseReference firebase;

    public TitleListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Instância objetos
        titles = new ArrayList<>();
        titles.add("Escola");
        titles.add("Trabalho");
        titles.add("Casa");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_title_list, container, false);

        //Monta listview e adapter
        listView = (ListView) view.findViewById(R.id.lv_title_list_id);
        adapter = new ArrayAdapter(getActivity(), R.layout.title_list, titles);
        listView.setAdapter(adapter);








        return view;
    }
}
