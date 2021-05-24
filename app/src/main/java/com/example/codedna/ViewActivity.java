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
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

public class ViewActivity extends AppCompatActivity {
    EditText tvname, tvo2, tvfood, tvdop, etinf;//EditText'ы всякие
    TextView tvtime, tvtemp, tvwat, tvco2;//TextView'шки всякие
    Button btno2,btnname,btnfood,btndop;//Кнопочки
    int btname=0,bto2=0,btfood=0,btdop=0;//Переменные для работы с кнопками
    double inf1;//
    double inf2;// Переменные для данных по КОЕ
    double inf3;//
   static  int id;//ID заметки
    private DBHelper dbhelper;//Готовим БД
    String arg[]={DataClass.selectarg};//Аргумент для выборки из БД при открытии

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        getSupportActionBar().hide();



        btnname=(Button) findViewById(R.id.btnname);//От сих
        btno2=(Button) findViewById(R.id.btno2);
        btnfood=(Button) findViewById(R.id.btnfood);
        btndop=(Button) findViewById(R.id.btndop);

        etinf=(EditText) findViewById(R.id.etinf);
        tvname = (EditText) findViewById(R.id.name);
        tvtime = (TextView) findViewById(R.id.time);
        tvtemp = (TextView) findViewById(R.id.temp);
        tvwat = (TextView) findViewById(R.id.wat);
        tvco2 = (TextView) findViewById(R.id.co2);
        tvo2 = (EditText) findViewById(R.id.o2);
        tvfood = (EditText) findViewById(R.id.food);
        tvdop = (EditText) findViewById(R.id.dop);//До сих привязываем к их id все элементы экрана
        dbhelper = new DBHelper(this);//Готовим БД до конца
        SQLiteDatabase db = dbhelper.getReadableDatabase();// Подключаем к чтению БД


        if(DataClass.type==1){// Если эта активность открылась из окна созданию, то
        String select = DBHelper.KEY_NAME + "= ?";//Метод выборки
            String arg[]={DataClass.selectarg};//Для полной уверенности
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, select, arg, null, null, null);//Получаем все данные
        cursor.moveToFirst();//Курсор на первую строку
        int idout=cursor.getColumnIndex(DBHelper.KEY_ID);//От сих
        int nameout = cursor.getColumnIndex(DBHelper.KEY_NAME);
        int timeout=cursor.getColumnIndex(DBHelper.KEY_TIME);
        int tempout=cursor.getColumnIndex(DBHelper.KEY_TEMP);
        int watout=cursor.getColumnIndex(DBHelper.KEY_WATER);
        int co2out=cursor.getColumnIndex(DBHelper.KEY_CO2);
        int o2out=cursor.getColumnIndex(DBHelper.KEY_O2);
        int foodout=cursor.getColumnIndex(DBHelper.KEY_FOOD);
        int dopout=cursor.getColumnIndex(DBHelper.KEY_DOP);//До сих получаем индексы всех данных

        String name = cursor.getString(nameout);//От сих
        String time=cursor.getString(timeout);
        String temp=Double.toString(cursor.getDouble(tempout));
        String wat= Double.toString(cursor.getDouble(watout) );
        String co2= Double.toString(cursor.getDouble(co2out) );
        String o2=  Double.toString(cursor.getDouble(o2out)  );
        String food=cursor.getString(foodout);
        String dop=cursor.getString(dopout);
        id=cursor.getInt(idout);//До сих мы получаем через индексы из столбца данные и присваиваем их переменным

        tvname.setText(name);//От сих
        tvname.setEnabled(false);
        tvtime.setText(time);
        tvtemp.setText(temp);
        tvwat.setText(wat);
        tvco2.setText(co2);
        tvo2.setText(o2);
        tvo2.setEnabled(false);
        tvfood.setText(food);
        tvfood.setEnabled(false);
        tvdop.setText(dop);
        tvdop.setEnabled(false);//До сих устанавливаем на экран текст и настраиваем его

        }else if(DataClass.type==2){// Если данная активность была открыта из меню выбора
        String select = DBHelper.KEY_ID + "= ?";//Метод выборки
        id=Integer.parseInt(DataClass.selectarg.trim());//id
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, select, arg, null, null, null);//Выборка
        cursor.moveToFirst();//Курсор на строку номер 1
        int nameout = cursor.getColumnIndex(DBHelper.KEY_NAME);//Получаем индексы от сюда
        int timeout=cursor.getColumnIndex(DBHelper.KEY_TIME);
        int tempout=cursor.getColumnIndex(DBHelper.KEY_TEMP);
        int watout=cursor.getColumnIndex(DBHelper.KEY_WATER);
        int co2out=cursor.getColumnIndex(DBHelper.KEY_CO2);
        int o2out=cursor.getColumnIndex(DBHelper.KEY_O2);
        int foodout=cursor.getColumnIndex(DBHelper.KEY_FOOD);
        int dopout=cursor.getColumnIndex(DBHelper.KEY_DOP);//до сюда



        String name = cursor.getString(nameout);//Получаем через индексы данных из строки
        String time=cursor.getString(timeout);
        String temp=Double.toString(cursor.getDouble(tempout));
        String wat= Double.toString(cursor.getDouble(watout) );
        String co2= Double.toString(cursor.getDouble(co2out) );
        String o2=  Double.toString(cursor.getDouble(o2out)  );
        String food=cursor.getString(foodout);
        String dop=cursor.getString(dopout);// Тут это заканчиваем


        tvname.setText(name);//Вот отсюда
        tvname.setEnabled(false);
        tvtime.setText(time);
        tvtemp.setText(temp);
        tvwat.setText(wat);
        tvco2.setText(co2);
        tvo2.setText(o2);
        tvo2.setEnabled(false);
        tvfood.setText(food);
        tvfood.setEnabled(false);
        tvdop.setText(dop);
        tvdop.setEnabled(false);//До сюда мы устанавливаем эти данные в нужные места на экранах и там где это нужно запрещаем редактировать
        }
        //Далее мы получаем данные по КОЕ, их значения и время установки
        String select = DBHelper.KEY_ID + "= ?";//Тут Метод выборки
        String[] arr={String.valueOf(id)};//Аргумент

        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, select, arr, null, null, null);//Выборка
        cursor.moveToFirst();
        int inf1out=cursor.getColumnIndex(DBHelper.INF1);//Индексы
        int inf2out=cursor.getColumnIndex(DBHelper.INF2);
        int inf3out=cursor.getColumnIndex(DBHelper.INF3);
        int time1out=cursor.getColumnIndex(DBHelper.TIME1);
        int time2out=cursor.getColumnIndex(DBHelper.TIME2);
        int time3out=cursor.getColumnIndex(DBHelper.TIME3);// Индексы

        inf1=cursor.getDouble(inf1out);                                 //Переменные устанавливаются
        inf2=cursor.getDouble(inf2out);
        inf3=cursor.getDouble(inf3out);
        double time1=cursor.getDouble(time1out);
        double time2=cursor.getDouble(time2out);
        double time3=cursor.getDouble(time3out);                        //Переменные устанавливаются
        GraphView graph = (GraphView) findViewById(R.id.graph);         //ПРивязываем график к его Id
        /*Toast.makeText(getApplicationContext(),""+inf1+ " "+inf2+" "+ inf3, Toast.LENGTH_SHORT).show();*/
        if(inf1!=0&&inf2==0){//Далее в зависимости от имеющихся данных выберается как будет отрисован график
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint( time1,inf1)

        });
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(0);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMaxX(1000);
            // enable scaling and scrolling
            graph.getViewport().setScalable(true);
            graph.getViewport().setScalableY(true);

            graph.addSeries(series);
        } else if(inf2!=0&&inf3==0){
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                    new DataPoint( time1,inf1),
                    new DataPoint(time2, inf2)
            });
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(0);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMaxX(1000);

            // enable scaling and scrolling
            graph.getViewport().setScalable(true);
            graph.getViewport().setScalableY(true);

            graph.addSeries(series);
        } else if (inf3!=0) {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint( time1,inf1),
                    new DataPoint(time2, inf2),
                    new DataPoint(time3,inf3)
            });
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(0);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMaxX(1000);

            // enable scaling and scrolling
            graph.getViewport().setScalable(true);
            graph.getViewport().setScalableY(true);

            graph.addSeries(series);//график рисуется
        }


    }
    public void back(View view) {
        Intent screen=new Intent(ViewActivity.this, MainActivity.class);//Назад в главное меню
        startActivity(screen);
    }

    public void btnname(View view) {//Редактируем ячейку с именем
        if(btname==0){
        tvname.setEnabled(true);//Позволяем редактировать
        btnname.setText("Сохранить");
        btname=1;         //Переменная для отлавливания ситуации
        } else if (btname==1){//Сохраняем результаты редактирования
            SQLiteDatabase db = dbhelper.getReadableDatabase();//Разрешаем редактирование
            ContentValues cv=new ContentValues();//Для упаковываия данных
            cv.put(DBHelper.KEY_NAME, tvname.getText().toString());//Упаковываем
            db.update(DBHelper.TABLE_NAME, cv, DBHelper.KEY_ID+"="+id, null);//Обновляем
            tvname.setEnabled(false);//Запрещаем редактировать
            btnname.setText("Редактировать");
            btname=0;
        }
    }

    public void btno2(View view) {//Редактируем ячейку с данными по кислороду, тут и далее всё также как и с именем
        if(bto2==0){
        tvo2.setEnabled(true);
        btno2.setText("Сохранить");
        bto2=1;
        } else if (bto2==1){
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(DBHelper.KEY_O2, Double.parseDouble(tvo2.getText().toString().trim()));
            db.update(DBHelper.TABLE_NAME, cv, DBHelper.KEY_ID+"="+id, null);
            tvo2.setEnabled(false);
            btno2.setText("Редактировать");
            bto2=0;
        }
    }

    public void btnfood(View view) {//Редактируем данные по питательной среде
        if(btfood==0){
        tvfood.setEnabled(true);
        btnfood.setText("Сохранить");
        btfood=1;
        } else if (btfood==1){
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(DBHelper.KEY_FOOD, tvfood.getText().toString());
            db.update(DBHelper.TABLE_NAME, cv, DBHelper.KEY_ID+"="+id, null);
            tvfood.setEnabled(false);
            btnfood.setText("Редактировать");
            btfood=0;
        }
    }

    public void btndop(View view) {//Редактрируем данные по доп. факторам
        if(btdop==0){
        tvdop.setEnabled(true);
        btndop.setText("Сохранить");
        btdop=1;
        } else if (btdop==1){
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(DBHelper.KEY_DOP, tvname.getText().toString());
            db.update(DBHelper.TABLE_NAME, cv, DBHelper.KEY_ID+"="+id, null);
            tvdop.setEnabled(false);
            btndop.setText("Редактировать");
            btdop=0;
        }
    }

    public void btninf(View view) {//Тут мы добавляем данные по КОЕ
        SQLiteDatabase db = dbhelper.getReadableDatabase();//Разрешаем читать БД
        String select = DBHelper.KEY_ID + "= ?";//Метод выборки
        String[] arr={String.valueOf(id)};//Упаковываем аргумент в виде строки
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, select, arr, null, null, null);//Получаем данные
        cursor.moveToFirst();//К первой строке

        int timedout=cursor.getColumnIndex(DBHelper.DAY);       //Получаем индексы
        int timemout=cursor.getColumnIndex(DBHelper.MOUNTH);    //данных по времени
        int timehout=cursor.getColumnIndex(DBHelper.HOURS);     //создания заметки
        int timeminout=cursor.getColumnIndex(DBHelper.MINUTES); //и индексы текущих
        int inf1out=cursor.getColumnIndex(DBHelper.INF1);       //КОЕ в ячейках
        int inf2out=cursor.getColumnIndex(DBHelper.INF2);       //, коих только три
        int inf3out=cursor.getColumnIndex(DBHelper.INF3);       //Так как больше вряд ли нужно
        inf1=cursor.getInt(inf1out);                            //Получаем переменные через индекс от сюда
        inf2=cursor.getInt(inf2out);
        inf3=cursor.getInt(inf3out);
        int timed=cursor.getInt(timedout);
        int timem=cursor.getInt(timemout);
        int timemin=cursor.getInt(timeminout);
        int timeh=cursor.getInt(timehout);                      //до сюда

        DateFormat df=new SimpleDateFormat("dd");       //Готовим шаблоны для времени. 1)ДЕнь
        DateFormat mf=new SimpleDateFormat("MM");       //2)Месяц
        DateFormat hf=new SimpleDateFormat("HH");       //3)Час
        DateFormat minf=new SimpleDateFormat("mm");     //4)Минута
        Date date=new Date();                                   //Получаем дату
        int day=Integer.parseInt(df.format(date));              //РЕдактируем даты согласно выбранному шаблону
        int mounth=Integer.parseInt(mf.format(date));           //Полученное определяем в переменные
        int hours=Integer.parseInt(hf.format(date));            //
        int minutes=Integer.parseInt(minf.format(date));        //

        int dm=-timem+mounth;                                   //Получаем разницу между временем создания проекта и текущим временем
        int dd=-timed+day;                                      //выражаем в месяцах, днях, часах, минутах
        int dh=-timeh+hours;
        int dmin=-timemin+minutes;
        if (dm>0){//механизм отлавливания ошибки при разнице в месяцах, то есть если проект был создан 30 января, а добавляешь данные 2 февраля, то пройдет не -28 дней, а 3 дня
            if(timem==1||timem==3||timem==5||timem==7||timem==8||timem==10||timem==12){
                dd=31-timed+day;
            } else if(timem==2){
                dd=28-timed+day;
            } else{
                dd=30-timed+day;
            }
        }
        double time=dd*24+dm*30+dh+dmin/60; //Всё временную разницу выражаем через часы


        if(inf1==0){//Тут мы определяем какие ячейки свободны, а какие нет
            //1 свободна, методика установки данных такая же как и раньше, но всё же
            ContentValues cv=new ContentValues();// Пакет для данных
            cv.put(DBHelper.INF1, Double.parseDouble(etinf.getText().toString().trim()));//В пакет для данных складываем данные, а именно данные по КОЕ
            cv.put(DBHelper.TIME1, time);//добавляем данные по текущему часу от начала эксперимента
            db.update(DBHelper.TABLE_NAME, cv, DBHelper.KEY_ID+"="+id, null);//Обновляем данные в таблице
            Toast.makeText(getApplicationContext(), "На графике установлена точка 1 на "+time+" часу ", Toast.LENGTH_SHORT).show();//Оповещаем пользователя о том, что данные были добавлены и в каком часу

        } else if(inf2==0){
            //Тоже самое в случае, если первая ячейка занята
            ContentValues cv=new ContentValues();
            cv.put(DBHelper.INF2, Double.parseDouble(etinf.getText().toString().trim()));
            cv.put(DBHelper.TIME2, time);
            db.update(DBHelper.TABLE_NAME, cv, DBHelper.KEY_ID+"="+id, null);
            Toast.makeText(getApplicationContext(), "На графике установлена точка 2 на "+time+" часу ", Toast.LENGTH_SHORT).show();
        } else if(inf3==0){
            //3 ячейка
            ContentValues cv=new ContentValues();
            cv.put(DBHelper.INF3, Double.parseDouble(etinf.getText().toString().trim()));
            cv.put(DBHelper.TIME3, time);
            db.update(DBHelper.TABLE_NAME, cv, DBHelper.KEY_ID+"="+id, null);
            Toast.makeText(getApplicationContext(), "На графике установлена точка 3 на "+time+" часу ", Toast.LENGTH_SHORT).show();
        } else {//все ячейки заняты
            Toast.makeText(getApplicationContext(), "Все ячейки данных заняты", Toast.LENGTH_SHORT).show();
        }





        inf1out=cursor.getColumnIndex(DBHelper.INF1);//получаем индексы данных по КОЕ
        inf2out=cursor.getColumnIndex(DBHelper.INF2);//
        inf3out=cursor.getColumnIndex(DBHelper.INF3);//
        int time1out=cursor.getColumnIndex(DBHelper.TIME1);//получаем индексы времени для КОЕ
        int time2out=cursor.getColumnIndex(DBHelper.TIME2);//
        int time3out=cursor.getColumnIndex(DBHelper.TIME3);//

        inf1=cursor.getDouble(inf1out);//получаем кол-во КОЕ. 1
        inf2=cursor.getDouble(inf2out);//2
        inf3=cursor.getDouble(inf3out);//3
        double time1=cursor.getDouble(time1out);//получаем время добавления КОЕ в таблицу
        double time2=cursor.getDouble(time2out);//
        double time3=cursor.getDouble(time3out);//
        GraphView graph = (GraphView) findViewById(R.id.graph);//подключаем график
        if(inf1!=0&&inf2==0){//Рисуем график, всё также как и в начале
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint( time1,inf1)

            });
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(0);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMaxX(1000);

            // enable scaling and scrolling
            graph.getViewport().setScalable(true);
            graph.getViewport().setScalableY(true);

            graph.addSeries(series);
        } else if(inf2!=0&&inf3==0){
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                    new DataPoint( time1,inf1),
                    new DataPoint(time2, inf2)
            });
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(0);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMaxX(1000);

            // enable scaling and scrolling
            graph.getViewport().setScalable(true);
            graph.getViewport().setScalableY(true);

            graph.addSeries(series);
        } else if (inf3!=0) {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint( time1,inf1),
                    new DataPoint(time2, inf2),
                    new DataPoint(time3,inf3)
            });
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(0);
            graph.getViewport().setMaxY(200);

            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMaxX(1000);

            // enable scaling and scrolling
            graph.getViewport().setScalable(true);
            graph.getViewport().setScalableY(true);

            graph.addSeries(series);
        }
    }

    public void info(View view) {

        Intent intent=new Intent(ViewActivity.this, InfoActivity.class);//Переходим во вкладку расширенного редактирования данных по КОЕ
        startActivity(intent);

    }
}
