package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageView egg;
    SeekBar seekBar;
    TextView timerTextView;
    public int time;
    Button start,stp,reset;


    CountDownTimer countDownTimer;
    public int i=1;
     RotateAnimation rotae ,ff;
     RotateAnimation rev;
    RotateAnimation fro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        egg = findViewById(R.id.egg);
        seekBar = findViewById(R.id.seekBar);
        timerTextView = findViewById(R.id.textView);
        start = findViewById(R.id.startButton);
        stp = findViewById(R.id.stop);
        reset = findViewById(R.id.reset);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        rotae = (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rotate);
        rev = (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rev);
        fro = (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.fro);
        ff = (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.ff);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });










    }
    public void start(View view) {
        start.setVisibility(View.INVISIBLE);
        stp.setVisibility(View.VISIBLE);
        reset.setVisibility(View.INVISIBLE);

        timerTextView.setTextSize(95);

        countDownTimer = new CountDownTimer(seekBar.getProgress()*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {


                if (i == 1) {
                    egg.startAnimation(rev);
                    i = 2;
                } else {
                    egg.startAnimation(fro);
                    i = 1;
                }
                updateTimer((int)millisUntilFinished/1000);
                seekBar.setProgress(seekBar.getProgress()-1);


            }

            @Override
            public void onFinish() {
                if (i == 1)
                    egg.setAnimation(ff);
                else {
                    egg.setAnimation(rotae);

                }
                timerTextView.setTextSize(81);


            }
        }.start();
    }

    public void updateTimer(int timeSeconds)
    {
        int minutes = timeSeconds / 60;
        int seconds = timeSeconds - minutes * 60;
        String dstring = Integer.toString(seconds);
        if (dstring.equals("0"))
            dstring = "00";
        timerTextView.setText(Integer.toString(minutes) + ":" + dstring);
    }

    public void stop(View view)
    {
        stp.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.VISIBLE);
        start.setVisibility(View.VISIBLE);

        countDownTimer.cancel();


    }

    public void reset(View view)
    {
        reset.setVisibility(View.INVISIBLE);
        stp.setVisibility(View.INVISIBLE);
        seekBar.setProgress(0);
        

    }









}
