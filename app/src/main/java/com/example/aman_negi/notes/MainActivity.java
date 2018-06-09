package com.example.aman_negi.notes;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private ListView listView;
    private Toolbar toolbar;
    private String[] name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        listView = findViewById(R.id.listView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notes");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                Snackbar.make(v, "New Note Created", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        updateFileList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get item name and open file
                String itemName = name[position];
                //sending filename to MainActivity3
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                intent.putExtra("key", itemName);
                startActivity(intent);
            }
        });

    }


    public void updateFileList() {
        name = fileList();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, name);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateFileList();

    }
}

