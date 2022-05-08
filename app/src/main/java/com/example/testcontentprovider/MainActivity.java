package com.example.testcontentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: Add code here
        listView = findViewById(R.id.listView);

        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.taskmanager.TaskProvider/tasks"), null, null, null, null);
        System.out.println(cursor.getCount());
        if(cursor.moveToFirst()) {
            ArrayList<String> tasks = new ArrayList<String>();

            while (!cursor.isAfterLast()) {
                tasks.add(cursor.getString(Integer.valueOf(cursor.getColumnIndex("ID"))) +" - " + cursor.getString(Integer.valueOf(cursor.getColumnIndex("NAME"))));
                cursor.moveToNext();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks);
            listView.setAdapter(adapter);
        }
        else {
            listView.setAdapter(null);
        }
    }
}