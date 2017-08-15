package com.lembrol.ana.View;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.lembrol.ana.Model.Reminder;
import com.lembrol.ana.Model.Title;
import com.lembrol.ana.R;

import java.util.List;

public class TitleAdapter extends ArrayAdapter<Reminder> {

    private ArrayList<Reminder> reminders;
    private Context context;

    public TitleAdapter(Context c, ArrayList<Reminder> objects) {
        super(c, 0, objects);
        this.reminders = objects;
        this.context = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        //Verifica se tem lista
        if (reminders != null){

            //inicializar objetos para montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Montar a view a partir do xml
            view = inflater.inflate(R.layout.title_list, parent, false);

            //Recupera elemento para exibição
            TextView titleReminder = (TextView) view.findViewById(R.id.tv_title);

            Reminder reminder = reminders.get(position);
            titleReminder.setText(reminder.getTitleReminder());
        }

        return view;
    }
}
