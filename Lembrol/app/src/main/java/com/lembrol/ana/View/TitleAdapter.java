package com.lembrol.ana.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lembrol.ana.Model.Reminder;
import com.lembrol.ana.R;

import java.util.List;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.MyViewHolder>{


    private List<String> titleReminder;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleReminder;

        public MyViewHolder(View view) {
            super(view);
            titleReminder = (TextView) view.findViewById(R.id.tv_title);
        }
    }


    public TitleAdapter(List<String> titleReminder) {
        this.titleReminder = titleReminder;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.title_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String titleKey = titleReminder.get(position);
        holder.titleReminder.setText(titleKey);

    }

    @Override
    public int getItemCount() {
        return titleReminder.size();
    }


}
