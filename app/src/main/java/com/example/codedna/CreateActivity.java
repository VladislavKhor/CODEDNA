package com.example.codedna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateActivity extends AppCompatActivity {
    EditText ettime, ettemp, etwat,etco2, etdop, etname, eto2, etfood;//все EditText'ы
    DBHelper dbhelper;//Для БД


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        getSupportActionBar().hide();


        //Объявляем EditText'ы
        etname=(EditText)findViewById(R.id.name);
        ettime=(EditText)findViewById(R.id.time);
        ettemp=(EditText)findViewById(R.id.temp);
        etwat=(EditText)findViewById(R.id.wat);
        etco2=(EditText)findViewById(R.id.co2);
        etdop=(EditText)findViewById(R.id.dop);
        eto2=(EditText)findViewById(R.id.O2);
        etfood=(EditText)findViewById(R.id.food);

        dbhelper=new DBHelper(this);//Для БД
    }

    public void back(View view) {
        Intent screen=new Intent(CreateActivity.this, MainActivity.class);//Назад
        startActivity(screen);
    }

    public void prcreate(View view) {

        String name=etname.getText().toString();
        String time=ettime.getText().toString();
        double temp=Double.parseDouble(ettemp.getText().toString().trim());
        double wat=Double.parseDouble(etwat.getText().toString().trim());
        double co2=Double.parseDouble(etco2.getText().toString().trim());
        double O2=Double.parseDouble(eto2.getText().toString().trim());
        String dop=etdop.getText().toString();
        String food=etfood.getText().toString();//Получаем всю информацию из EditText'ов

        SQLiteDatabase database = dbhelper.getWritableDatabase();//Подключаем ДБ к редактированию
        ContentValues contentValues = new ContentValues();// Для вставок в ДБ

        contentValues.put(DBHelper.KEY_NAME, name);// От сих
        contentValues.put(DBHelper.KEY_TIME, time);
        contentValues.put(DBHelper.KEY_TEMP, temp);
        contentValues.put(DBHelper.KEY_WATER,wat);
        contentValues.put(DBHelper.KEY_CO2, co2);
        contentValues.put(DBHelper.KEY_O2, O2);
        contentValues.put(DBHelper.KEY_FOOD, food);
        contentValues.put(DBHelper.KEY_DOP, dop);
        contentValues.put(DBHelper.INF1, 0);
        contentValues.put(DBHelper.INF2, 0);
        contentValues.put(DBHelper.INF3, 0);
        contentValues.put(DBHelper.TIME1,0);
        contentValues.put(DBHelper.TIME2,0);
        contentValues.put(DBHelper.TIME3,0);//До сих подготавливаем данные для вставки в БД

        DateFormat df=new SimpleDateFormat("dd");//От сих
        Date date=new Date();
        int day=Integer.parseInt(df.format(date));
        DateFormat mf=new SimpleDateFormat("MM");
        int mounth=Integer.parseInt(mf.format(date));
        DateFormat hf=new SimpleDateFormat("HH");
        DateFormat minf=new SimpleDateFormat("mm");
        int hours=Integer.parseInt(hf.format(date));
        int  minutes=Integer.parseInt(minf.format(date));//До сих получаем текущее время
        contentValues.put(DBHelper.DAY, day );//От сих
        contentValues.put(DBHelper.MOUNTH, mounth);
        contentValues.put(DBHelper.HOURS, hours);
        contentValues.put(DBHelper.MINUTES, minutes);// До сих подготавливаем к вставке время
        database.insert(DBHelper.TABLE_NAME, null, contentValues);// Всю информацию о заметке вставляем в БД
        DataClass.selectarg = name;//Это чтобы при открытии знать что открыть
        DataClass.type=1;// А это как открыть
        database.close();//Закрываем БД
        Intent screen=new Intent(CreateActivity.this, ViewActivity.class);//Переходим к открытию заметки
         startActivity(screen);
    }
}