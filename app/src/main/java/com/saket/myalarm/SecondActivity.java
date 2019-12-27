package com.saket.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle = getIntent().getExtras();
        assert bundle!= null;
        String name = bundle.getString("name");
        TextView textView = findViewById(R.id.text);
        textView.setText("Wake up "  + name);
        Uri ringtoneUri = RingtoneManager.getValidRingtoneUri(SecondActivity.this);
        assert ringtoneUri != null;
        Ringtone ringtone = RingtoneManager.getRingtone(SecondActivity.this, ringtoneUri);
        ringtone.play();
    }
}
