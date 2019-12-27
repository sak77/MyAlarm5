
package com.saket.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    AlarmManager mAlarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get instance of Alarm Manager system service
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        setAlarm();
    }


    private void setAlarm() {
        assert mAlarmManager != null;
        //Intent to launch Second activity
        Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);
        Bundle extras = new Bundle();
        extras.putString("name","Saket");
        secondIntent.putExtras(extras);
        //Pending intent is a wrapper around intent that allows other apps (AlarmManager in this case) to
        //invoke the intent as if it as being invoked from within the app itself.
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0123,
                secondIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        //Passing pending intent to alarmManager and telling it to execute it after 10 secs.
        mAlarmManager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 10 * 1000, pendingIntent);
        AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(
                SystemClock.elapsedRealtime() + 10 * 1000, pendingIntent);
        //mAlarmManager.setAlarmClock(alarmClockInfo, pendingIntent);
    }

    private void getNextAlarm() {
        assert mAlarmManager != null;
        AlarmManager.AlarmClockInfo info = mAlarmManager.getNextAlarmClock();
        PendingIntent pendingIntent = info.getShowIntent();
        String creatorpackage = pendingIntent.getCreatorPackage();
        long triggertime = info.getTriggerTime();
        Date date = new Date(triggertime);
        TextView txtTime = findViewById(R.id.txtTime);
        txtTime.setText("Alarm created by " + creatorpackage + " and time is: " + date.toString());

    }
}
