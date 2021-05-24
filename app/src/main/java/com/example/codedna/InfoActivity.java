package com.example.codedna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InfoActivity extends AppCompatActivity {
    EditText etinf1,etinf2,etinf3;//EditTExt'ы все подключаем
    int id;//Id
    int edit1, edit2,edit3=0;//переменные для отлавливания ситуации
    private DBHelper dbhelper;//Для БД
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().hide();

        id=ViewActivity.id;//id получаем из ViewActivity
        String[] arr={String.valueOf(id)};//Пакуем id в массив строк
        etinf1=(EditText)findViewById(R.id.inf1);//
        etinf2=(EditText)findViewById(R.id.inf2);//Привязываем EditText'ы  к их id
        etinf3=(EditText)findViewById(R.id.inf3);//
        dbhelper = new DBHelper(this);//Для БД
        SQLiteDatabase db = dbhelper.getReadableDatabase();//Для БД
        String select = DBHelper.KEY_ID + "= ?";//Метод выборки

        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, select, arr, null, null, null);//Получаем данные из БД
        cursor.moveToFirst();//К первой строке
        int inf1out=cursor.getColumnIndex(DBHelper.INF1);//
        int inf2out=cursor.getColumnIndex(DBHelper.INF2);//Получаем индексы значений КОИ
        int inf3out=cursor.getColumnIndex(DBHelper.INF3);//
        double inf1=cursor.getDouble(inf1out);//
        double inf2=cursor.getDouble(inf2out);//Получаем значения КОИ
        double inf3=cursor.getDouble(inf3out);//
        etinf1.setText(String.valueOf(inf1));//
        etinf2.setText(String.valueOf(inf2));//Отображаем данные по КОИ
        etinf3.setText(String.valueOf(inf3));//
        etinf1.setEnabled(false);//
        etinf2.setEnabled(false);//Запрещаем редактирование EditText'ов
        etinf3.setEnabled(false);//
    }

    public void inf1(View view) {//Если нажимем на первую кнопку
        if(edit1==0){//Открываем возможность редактировать 1 строки
            etinf1.setEnabled(true);
            edit1=1;

        } else {//Закрываем возможность редактирования, сохраняя новые данные

            SQLiteDatabase db = dbhelper.getReadableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(DBHelper.INF1, Double.parseDouble( etinf1.getText().toString()));
            db.update(DBHelper.TABLE_NAME, cv, DBHelper.KEY_ID+"="+id, null);
            edit1=0;
            etinf1.setEnabled(false);


        }
    }

    public void inf2(View view) {//Открываем возможность редактировать 2 строки
        if(edit2==0) {
            etinf2.setEnabled(true);
            edit2=1;
        } else {//Закрываем возможность редактирования, сохраняя новые данные
            edit2=0;
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(DBHelper.INF2, Double.parseDouble( etinf2.getText().toString()));
            db.update(DBHelper.TABLE_NAME, cv, DBHelper.KEY_ID+"="+id, null);
            etinf2.setEnabled(false);
        }
    }

    public void inf3(View view) {//Открываем возможность редактировать 3 строки
        if(edit3==0) {
            etinf3.setEnabled(true);
            edit3=1;
        }else{//Закрываем возможность редактирования, сохраняя новые данные
            edit3=0;
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(DBHelper.INF3,Double.parseDouble( etinf3.getText().toString()));
            db.update(DBHelper.TABLE_NAME, cv, DBHelper.KEY_ID+"="+id, null);

            etinf3.setEnabled(false);
        }
    }

    public void backinf(View view) {//Возвращаемся назад в ViewActivity
        Intent intent=new Intent(InfoActivity.this, ViewActivity.class);
        startActivity(intent);
    }
}