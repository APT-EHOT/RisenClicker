package com.example.risenclicker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.risenclicker.Support.Inits;
import com.example.risenclicker.R;
import com.example.risenclicker.Support.Timer;

public class PlayActivity extends AppCompatActivity {

    double score = 0.0;
    String scoreOut = "0.0";
    int nums[] = new int[4];
    double numsDouble[] = new double[4];
    int signs[] = new int[4];

    Inits inits = new Inits();
    String timer;
    long savedTime;
    Timer timerNow;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    private TextView textView;
    private TextView timerView;
    private TextView textLast;

    private SharedPreferences ClickerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ClickerData = getSharedPreferences("ClickerData", Context.MODE_PRIVATE);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);

        textView = findViewById(R.id.text_view);
        timerView = findViewById(R.id.timer_view);
        textLast = findViewById(R.id.text_last);

        final String CURRENT_DIFFICULTY;
        CURRENT_DIFFICULTY = ClickerData.getString("difficulty", "");

        switch (CURRENT_DIFFICULTY) {
            case "0":
                inits.initEasy(nums, btn1, btn2, btn3, btn4);
                break;
            case "1":
                inits.initMedium(numsDouble, btn1, btn2, btn3, btn4);
                break;
            case "2":
                inits.initHard(numsDouble, signs, btn1, btn2, btn3, btn4, PlayActivity.this);
                break;
            default:
                break;
        }

        timerNow = new Timer(30000, 1000);
        timerNow.timerSettings(timer, timerView, ClickerData, scoreOut, PlayActivity.this);
        timerNow.start();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (CURRENT_DIFFICULTY) {
                    case "0":
                        score = inits.scoring(score, 0, nums[0], textLast, PlayActivity.this);
                        scoreOut = Integer.toString((int)score);
                        textView.setText(scoreOut);
                        timerNow.scoreRefresh(scoreOut);
                        inits.initEasy(nums, btn1, btn2, btn3, btn4);
                        break;
                    case "1":
                        score = inits.scoring(score, 0, numsDouble[0], textLast, PlayActivity.this);
                        scoreOut = Double.toString(inits.shrinkScore(score));
                        textView.setText(scoreOut);
                        timerNow.scoreRefresh(scoreOut);
                        inits.initMedium(numsDouble, btn1, btn2, btn3, btn4);
                        break;
                    case "2":
                        score = inits.scoring(score, signs[0], numsDouble[0], textLast, PlayActivity.this);
                        scoreOut = Double.toString(inits.shrinkScore(score));
                        textView.setText(scoreOut);
                        timerNow.scoreRefresh(scoreOut);
                        inits.initHard(numsDouble, signs, btn1, btn2, btn3, btn4, PlayActivity.this);
                        break;
                    default:
                        break;
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (CURRENT_DIFFICULTY) {
                    case "0":
                        score = inits.scoring(score, 1, nums[1], textLast, PlayActivity.this);
                        scoreOut = Integer.toString((int)score);
                        textView.setText(scoreOut);
                        timerNow.scoreRefresh(scoreOut);
                        inits.initEasy(nums, btn1, btn2, btn3, btn4);
                        break;
                    case "1":
                        score = inits.scoring(score, 1, numsDouble[1], textLast, PlayActivity.this);
                        scoreOut = Double.toString(inits.shrinkScore(score));
                        textView.setText(scoreOut);
                        timerNow.scoreRefresh(scoreOut);
                        inits.initMedium(numsDouble, btn1, btn2, btn3, btn4);
                        break;
                    case "2":
                        score = inits.scoring(score, signs[1], numsDouble[1], textLast, PlayActivity.this);
                        scoreOut = Double.toString(inits.shrinkScore(score));
                        textView.setText(scoreOut);
                        timerNow.scoreRefresh(scoreOut);
                        inits.initHard(numsDouble, signs, btn1, btn2, btn3, btn4, PlayActivity.this);
                        break;
                    default:
                        break;
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (CURRENT_DIFFICULTY) {
                    case "0":
                        score = inits.scoring(score, 2, nums[2], textLast, PlayActivity.this);
                        scoreOut = Integer.toString((int)score);
                        textView.setText(scoreOut);
                        timerNow.scoreRefresh(scoreOut);
                        inits.initEasy(nums, btn1, btn2, btn3, btn4);
                        break;
                    case "1":
                        score = inits.scoring(score, 2, numsDouble[2], textLast, PlayActivity.this);
                        scoreOut = Double.toString(inits.shrinkScore(score));
                        textView.setText(scoreOut);
                        timerNow.scoreRefresh(scoreOut);
                        inits.initMedium(numsDouble, btn1, btn2, btn3, btn4);
                        break;
                    case "2":
                        score = inits.scoring(score, signs[2], numsDouble[2], textLast, PlayActivity.this);
                        scoreOut = Double.toString(inits.shrinkScore(score));
                        textView.setText(scoreOut);
                        inits.initHard(numsDouble, signs, btn1, btn2, btn3, btn4,PlayActivity.this);
                        break;
                    default:
                        break;
                }

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (CURRENT_DIFFICULTY) {
                    case "0":
                        score = inits.scoring(score, 3, nums[3], textLast, PlayActivity.this);
                        scoreOut = Integer.toString((int)score);
                        textView.setText(scoreOut);
                        timerNow.scoreRefresh(scoreOut);
                        inits.initEasy(nums, btn1, btn2, btn3, btn4);
                        break;
                    case "1":
                        score = inits.scoring(score, 3, numsDouble[3], textLast, PlayActivity.this);
                        scoreOut = Double.toString(inits.shrinkScore(score));
                        textView.setText(scoreOut);
                        timerNow.scoreRefresh(scoreOut);
                        inits.initMedium(numsDouble, btn1, btn2, btn3, btn4);
                        break;
                    case "2":
                        score = inits.scoring(score, signs[3], numsDouble[3], textLast, PlayActivity.this);
                        scoreOut = Double.toString(inits.shrinkScore(score));
                        textView.setText(scoreOut);
                        timerNow.scoreRefresh(scoreOut);
                        inits.initHard(numsDouble, signs, btn1, btn2, btn3, btn4, PlayActivity.this);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        savedTime = timerNow.saveTime();
        timerNow.cancel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        timerNow = new Timer(savedTime, 1000);
        timerNow.timerSettings(timer, timerView, ClickerData, scoreOut, PlayActivity.this);
        timerNow.start();
    }

    @Override
    public void onBackPressed() {}

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }
}
