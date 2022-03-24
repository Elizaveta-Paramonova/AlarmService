package com.example.alarmservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText edtSeconds;
    Button btnStartTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSeconds = findViewById(R.id.edtSeconds);
        btnStartTimer = findViewById(R.id.btnStartTimer);
        btnStartTimer.setOnClickListener(view -> {
            //Получаем значение из текстового поля и переводим в Integer
            int seconds = Integer.parseInt(edtSeconds.getText().toString());
            //При помощи класса Intent обращаемся к логичке класса Alarm
            Intent intent = new Intent( MainActivity.this, Alarm.class);
            //Обращение к службе Alarm Service осуществляется при помощи объекта AlarmManager
            //Служба Alarm Service используется для отправки пользователю разовых или повторяющихся
            //сообщений в заданное время
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            //Срабатывание AlarmManager
            //System.currentTimeMillis() + seconds * 1000L - срабатывание логике через N-секунд и выполнение
            //логики из класса Alarm.java
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + seconds * 1000L,
                    PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0));
        });
    }
}