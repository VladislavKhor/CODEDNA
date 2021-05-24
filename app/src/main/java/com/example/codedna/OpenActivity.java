package com.example.codedna;



import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class OpenActivity extends ListActivity  {
    private DBHelper dbhelper;
    private ArrayAdapter<String> mAdapter;


    ArrayList<String> arr=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbhelper = new DBHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        if (!cursor.isAfterLast()) {
            cursor.moveToNext();
            do {

                int out = cursor.getColumnIndex(DBHelper.KEY_NAME);
                String name = cursor.getString(out);                     // Формируем список записок
                arr.add(name);
            } while (cursor.moveToNext());
        }
        mAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr );       //Отображаем
        setListAdapter(mAdapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {//Обработка нажатия на один из элементов

        super.onListItemClick(l, v, position, id);
        DataClass.type=2;//Передаём тип аргумента
        int intid=position+1;//ID выбранной записки
        DataClass.selectarg=Integer.toString(intid);//ID в String
        Intent intent=new Intent(OpenActivity.this, ViewActivity.class);//Переходим в ViewActivity
        startActivity(intent);

    }

    //Назад вернуться можно только через кнопку back на телефоне, вот так...

}