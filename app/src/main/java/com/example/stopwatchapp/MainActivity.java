package com.example.stopwatchapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;
    private Button btnStart, btnStop, btnReset;
    private int second = 0;
    private boolean isRunning = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvTimer = findViewById(R.id.tvTimer);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning= true;
                runTimer();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = false;
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = false;
                second = 0;
                tvTimer.setText("00:00:00");
            }
        });

    }

    public void runTimer(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second / 3600;
                int minutes = (second % 3600) / 60;
                int secs = second % 60;

                tvTimer.setText(String.format("%02d:%02d:%02d", hours, minutes, secs));
                if(isRunning){
                    second++;
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }
}