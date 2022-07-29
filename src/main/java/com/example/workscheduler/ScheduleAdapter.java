package com.example.workscheduler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ScheduleAdapter extends ArrayAdapter<String> {
    //public Context context;
    public String[] display_work;
    public String[] display_days;
    public String[] display_priority;

    public ScheduleAdapter(@NonNull Context context, int resource, @NonNull String[] work,String[] days, String[] priority) {
        super(context, resource, work );
        //this.context = context;
        this.display_work=work;
        this.display_days=days;
        this.display_priority=priority;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.schedule,parent,false);
        TextView worktext = convertView.findViewById(R.id.wd);
        TextView daystext = convertView.findViewById(R.id.dd);
        TextView prioritytext = convertView.findViewById(R.id.pd);
        worktext.setText(display_work[position]);
        daystext.setText(display_days[position]);
        prioritytext.setText(display_priority[position]);
        return convertView;
    }
}
