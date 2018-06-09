package com.example.aman_negi.notes;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity3 extends AppCompatActivity {
    private Toolbar toolbar3;
    private EditText noteOpen;
    private String itemName;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        toolbar3 = findViewById(R.id.toolbar3);
        noteOpen = findViewById(R.id.noteOpen);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        setSupportActionBar(toolbar3);

        //getting filename from MainActivity
        itemName = getIntent().getStringExtra("key");

        getSupportActionBar().setTitle(itemName);

        openFile(itemName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                deleteFile(itemName);
                Snackbar.make(getWindow().getDecorView(), "Note Deleted Successfully", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                finish();
                return true;
            case R.id.save:
                if (noteOpen.getText().toString().length() == 0) {
                    deleteFile(itemName);
                } else {
                    saveFile(itemName);
                    Snackbar.make(getWindow().getDecorView(), itemName, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openFile(String fileName) {
        String value = "";
        try {
            FileInputStream fis = openFileInput(fileName);
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {
                value += new String(input);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        noteOpen.setText(value);
    }

    public void saveFile(String name) {
        String filename = name;
        String fileContents = noteOpen.getText().toString();
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
