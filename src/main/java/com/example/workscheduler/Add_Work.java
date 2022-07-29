package com.example.workscheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Work extends AppCompatActivity {
    public static final String extra_work = "com.example.workscheduler.extra.work";
    public static final String extra_time = "com.example.workscheduler.extra.time";
    public static final String extra_days = "com.example.workscheduler.extra.days";
    public static final String extra_imp = "com.example.workscheduler.extra.imp";
    public static final String extra_size = "com.example.workscheduler.extra.size";
    public int limit = 10;
    public String[] work_store = new String[limit];
    public String[] time_store = new String[limit];
    public String[] days_store = new String[limit];
    public String[] Imp_store = new String[limit];
    public int i=0;
    EditText work;
    EditText time;
    EditText days;
    EditText Imp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);
            work = findViewById(R.id.Work);
            time = findViewById(R.id.Time);
            days = findViewById(R.id.Days);
            Imp = findViewById(R.id.Importance);

    }
    public void add_task(View view){
        if(work.getText().toString().equals("")||time.getText().toString().equals("")||days.getText().toString().equals("")||Imp.getText().toString().equals(""))
        {
            Toast.makeText(this, "!!All Fields are Required!!", Toast.LENGTH_SHORT).show();
        }

        else if(i<limit)
        {
            work_store[i] = work.getText().toString();
            time_store[i] = time.getText().toString();
            days_store[i] = days.getText().toString();
            Imp_store[i] = Imp.getText().toString();
            work.setText("");
            time.setText("");
            days.setText("");
            Imp.setText("");
            Toast.makeText(this, "!! Task " + (i+1) +" is Added !!", Toast.LENGTH_SHORT).show();
            i++;
        }
        else
        {
            Toast.makeText(this, "!! Limit Reached !!\n   !! List is full !!", Toast.LENGTH_SHORT).show();
            work.setText("");
            time.setText("");
            days.setText("");
            Imp.setText("");
        }
    }

    public void show_tasks(View view){
            int size = i;
            String[] work_data = new String[size];
            String[] time_data = new String[size];
            String[] days_data = new String[size];
            String[] Imp_data = new String[size];
           for (int j = 0; j <size; j++) {
              work_data[j] = work_store[j];
              time_data[j] = time_store[j];
              days_data[j] = days_store[j];
              Imp_data[j] = Imp_store[j];
           }


            Intent show_tasks = new Intent(this , Schedule.class);
            show_tasks.putExtra(extra_work , work_data);
            show_tasks.putExtra(extra_time , time_data);
            show_tasks.putExtra(extra_days , days_data);
            show_tasks.putExtra(extra_imp , Imp_data);
            show_tasks.putExtra(extra_size , size);
            startActivity(show_tasks);
        }


}
