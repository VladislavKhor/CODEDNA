package com.example.codedna;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button create=(Button)findViewById(R.id.create);
        getSupportActionBar().hide();
    }

    public void create(View view) { //переход к созданию заметки
        Intent screen=new Intent(MainActivity.this, CreateActivity.class);
        startActivity(screen);
    }

    public void list(View view) {// переход к списку созданных заметок
        Intent screen = new Intent(MainActivity.this, OpenActivity.class);
        startActivity(screen);
    }

    public void timer(View view) {// таймер
        Intent screen=new Intent(MainActivity.this, TimerActivity.class);
        startActivity(screen);
    }
}