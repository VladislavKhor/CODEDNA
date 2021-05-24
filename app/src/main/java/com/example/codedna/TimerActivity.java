package com.example.codedna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {
    static EditText ethours,etminutes,etseconds;
    Button button;
    private Timer mTimer;


    static int timer;
    static long shours, sseconds, sminutes;
    static int a=0;
    private static String CHANNEL_ID = "Timer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        ethours=(EditText) findViewById(R.id.hours);//Подключаем элементы к id
        etminutes=(EditText)findViewById(R.id.minutes);//
        etseconds=(EditText)findViewById(R.id.seconds);//
        button=(Button)findViewById(R.id.btn);//
        if(DataClass.timer==1){
            button.setText("Остановить таймер");
        }
    }

    public void button(View view) {//Нажимаем на кнопку запуска таймера
        int hours=Integer.parseInt(ethours.getText().toString().trim());//Подучаем время
        int minutes=Integer.parseInt(etminutes.getText().toString().trim());//Получаем время
        int seconds=Integer.parseInt(etseconds.getText().toString().trim());//Получаем время
        if(DataClass.timer==0) {
        if (minutes < 60 && minutes >= 0 && seconds >= 0 && seconds < 60 && hours >= 0 ) {//Проверка на корректность данных
        timer = (hours * 60 * 60 * 1000 + minutes * 60 * 1000 + seconds * 1000);//Получаем время в миллисекундах
         DataClass.countDownTimer=new CountDownTimer(timer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //Каждую секунду он будет обновляет данные в ячейках для ввода времени таймера, для удобства восприятия оставшегося времени
                shours = millisUntilFinished / 3600000;
                sminutes = (millisUntilFinished - shours * 3600000) / 60000;
                sseconds = (millisUntilFinished - shours * 3600000 - sminutes * 60000) / 1000;
                TimerActivity.ethours.setText(Long.toString(shours));
                TimerActivity.etminutes.setText(Long.toString(sminutes));
                TimerActivity.etseconds.setText(Long.toString(sseconds));
            }

            @Override
            public void onFinish() {
                //Я не смог придумать что сделать в конце
            }
        }.start();//Запускам таймер;
        } else {
            Toast.makeText(getApplicationContext(), "Введите корректное время", Toast.LENGTH_SHORT).show();//Если время некорректное
            DataClass.countDownTimer=new CountDownTimer(1,1) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {

                }
            }.start();//Запускам таймер
        }
                DataClass.timer=1;
                button.setText("Остановить таймер");

        }
        else if(DataClass.timer==1){
            DataClass.countDownTimer.cancel();//Останавливаем таймер
            Toast.makeText(getApplicationContext(),"Таймер остановлен",Toast.LENGTH_SHORT).show();
            button.setText("Запустить таймер");
            DataClass.timer=0;
        }
    }

    public void backbtn(View view) {
        Intent intent=new Intent(TimerActivity.this, MainActivity.class);
        startActivity(intent);//Назад в меню
    }
}
