package com.example.workscheduler;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Schedule extends AppCompatActivity {
    public static  String dir_path;

    Button save;
    public ListView list;
    public int size;
    public String[] work ;
    public String[] time ;
    public String[] days ;
    public String[] Imp ;
    public String[] priority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sechule);
        Intent show = getIntent();
        work = show.getStringArrayExtra(Add_Work.extra_work);
        time = show.getStringArrayExtra(Add_Work.extra_time);
        days = show.getStringArrayExtra(Add_Work.extra_days);
        Imp = show.getStringArrayExtra(Add_Work.extra_imp);
        size = show.getIntExtra(Add_Work.extra_size,10);

         Algo algo = new Algo();
         priority = algo.Calculate(time,days,Imp,size);

        list = findViewById(R.id.ListView);
        ScheduleAdapter ad = new ScheduleAdapter(this,R.layout.schedule,work,days,priority);
        list.setAdapter(ad);

        save=findViewById(R.id.save_bt);
        verifyStoragePermission(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeSS(getWindow().getDecorView().getRootView(),"ScheduleList");
                Toast.makeText(Schedule.this, "!!Image Saved at "+dir_path, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected static File takeSS(View view,String filename)
    {
        Date date = new Date();
        CharSequence format = DateFormat.format("yyyy-MM-dd_hh:mm:ss",date);

        try{
            dir_path = Environment.getExternalStorageDirectory().toString()+ "/DCIM/WorkScheduler";
            File filedir = new File(dir_path);
            if(!filedir.exists())
            {
                boolean mkdir = filedir.mkdir();
            }
            String path = dir_path+"/"+filename+"-"+format+".jpg";
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            File imagefile = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(imagefile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return imagefile;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static final int Request_External_Storage=1;
    private static String[] Permission_Storge={
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static void verifyStoragePermission(Activity activity){
        int permission = ActivityCompat.checkSelfPermission(activity,Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permission!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,Permission_Storge,Request_External_Storage);
        }
    }

}