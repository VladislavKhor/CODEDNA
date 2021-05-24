package com.example.codedna;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper//Класс для работы с БД
{
    public static final int DATABASE_VERSION=6;//Версия БД
    public static final String DATABASE_NAME="DBexper";//Имя БД
    public static final String TABLE_NAME="data";//Имя таблицы
//Данные по строкам
    public static final String KEY_ID="_id";//id строки
    public static final String KEY_NAME="name";//Имя строки
    public static final String KEY_TIME="time";//Длительность
    public static final String KEY_TEMP="tempar";//Температура
    public static final String KEY_WATER="water";//Влажность
    public static final String KEY_CO2="co2";//Углекислый газ
    public static final String KEY_O2="o2";//Кислорожд
    public static final String KEY_FOOD="food";//Питательная среда
    public static final String KEY_DOP="dop";//Доп данные
    public static final String INF1="inf1";//КОИ ячейка 1
    public static final String INF2="inf2";//КОИ ячейка 2
    public static final String INF3="inf3";//КОИ ячейка 3
    public static final String TIME1="time1";//Время добавления в ячейку 1
    public static final String TIME2="time2";//Время добавления в ячейку 2
    public static final String TIME3="time3";//Время добавления в ячейку 3
    public static final String DAY="day";//День создания проекта
    public static final String MOUNTH="mounth";//Месяц
    public static final String HOURS="hours";//Час
    public static final String MINUTES="minutes";//минута

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"("+KEY_ID+" integer primary key autoincrement, "+KEY_NAME+" text, "+KEY_TIME +" text, "+
                    KEY_TEMP+" real, "+KEY_WATER+" real, "+KEY_CO2+" real, "+KEY_O2+" real, "+KEY_FOOD+" text, "+KEY_DOP+" text, "+INF1+" real, "+INF2+" real, "+INF3+" real, "+TIME1+" real, "+
                TIME2+" real, "+TIME3+" real, "+DAY+" integer, "+MOUNTH+" integer, "+MINUTES+" integer, "+HOURS+" integer"+")");//Создание БД
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);//Обновление, в случае устаревшей версии
    }
}
