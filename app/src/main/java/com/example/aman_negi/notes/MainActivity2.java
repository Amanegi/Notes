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

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    private Toolbar toolbar2;
    private EditText editText;
    private String currentDateTime;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar2 = findViewById(R.id.toolbar2);
        editText = findViewById(R.id.noteEntry);

        //automatically show keyboard
        toolbar2.requestFocus();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy h:mm:ss a");
        currentDateTime = dateFormat.format(c.getTime());

        setSupportActionBar(toolbar2);
        getSupportActionBar().setTitle(currentDateTime);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                if (editText.getText().toString().length() != 0) {
                    saveFile(currentDateTime);
                    Snackbar.make(getWindow().getDecorView(), currentDateTime, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void saveFile(String name) {
        String filename = name;
        String fileContents = editText.getText().toString();
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
