package com.example.abhiw.myalarm;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TimePicker alarmTime;
    TextClock currentTime;
    Button setAlarm;
    Boolean save;
    String alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTime = findViewById(R.id.timePicker);
        alarmTime.setIs24HourView(true);
        currentTime = findViewById(R.id.textClock);
        setAlarm = findViewById(R.id.setAlarm);
        save=false;
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
        final Timer t = new Timer();
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save=true;
                Toast.makeText(getApplicationContext(), "Alarm set at "+AlarmTime(), Toast.LENGTH_SHORT).show();
                alarm=AlarmTime();
            }
    });

        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if(currentTime.getText().toString().equals(alarm) && save){
                    r.play();
                }
                else{
                    r.stop();
                }
            }
        },0,1000);



    }
    public String AlarmTime(){

        Integer alarmHours = alarmTime.getCurrentHour();
        Integer alarmMinutes = alarmTime.getCurrentMinute();

        String stringHours,stringMinutes,stringAlarmTime;
        if(alarmHours<10)
        {
            stringHours = "0".concat(alarmHours.toString());
        }
        else stringHours = alarmHours.toString();

        if(alarmMinutes<10)
        {
            stringMinutes="0".concat(alarmMinutes.toString());
        }
        else stringMinutes=alarmMinutes.toString();

//        if(alarmHours>12){
//            alarmHours = alarmHours - 12;
//            stringAlarmTime = alarmHours.toString().concat(":").concat(alarmMinutes.toString()).concat(" PM");
//        }
//        else
//        {
//            stringAlarmTime = alarmHours.toString().concat(":").concat(alarmMinutes.toString()).concat(" AM");
//        }
        stringAlarmTime = stringHours.concat(":").concat(stringMinutes);
        return stringAlarmTime;
    }
}
