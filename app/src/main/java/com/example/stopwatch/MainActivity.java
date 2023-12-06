package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private boolean isRunning = false;
    private int seconds = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edtStop);
        runner();
        checkInstanceState(savedInstanceState);
    }

    public void checkInstanceState(Bundle outState){
        if(outState != null){
            isRunning=outState.getBoolean("isRunning");
            seconds= outState.getInt("seconds");
        }
    }
    public void btnStartOnClick(View view) {
        isRunning = true;
    }

    public void btnStopOnClick(View view) {
        isRunning=false;
    }

    public void btnResetOnClick(View view) {
        isRunning = false;
        seconds=0;
    }

    public void runner(){
        final TextView textView = findViewById(R.id.txtTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(isRunning){
                    seconds++;
                }
                int hours = seconds /3600;
                int minutes = seconds /60;
                int realSeconds = seconds % 60;

                String time = "" + hours + " : " + minutes + " : " + realSeconds ;
                textView.setText(time);
                handler.postDelayed(this , 1000);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("isRunning", isRunning);
    }
}