package com.example.notemakingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashSet;

public class HomeActivity extends AppCompatActivity {

    static ArrayList<String> notes=new ArrayList<>();
    static ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_actitivty);

        GridView gridView = findViewById(R.id.gridView_id);
        SharedPreferences sp=getSharedPreferences("Notes file",MODE_PRIVATE);
        HashSet<String> set=(HashSet<String>) sp.getStringSet("Notes",null);

        if(set==null){
        notes.add("Example note");}else{
            notes=new ArrayList<>(set);
        }


        FloatingActionButton fab = findViewById(R.id.add_note_id);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomeActivity.this,NoteTakingActivity.class);
                startActivity(intent);
            }
        });

        arrayAdapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,notes);
        gridView.setAdapter(arrayAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(HomeActivity.this,NoteTakingActivity.class);
                intent.putExtra("noteId",position);
                startActivity(intent);
            }
        });
    }

}