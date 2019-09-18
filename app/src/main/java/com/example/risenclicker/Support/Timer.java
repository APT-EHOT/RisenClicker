package com.example.risenclicker.Support;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.risenclicker.Activities.EndgameActivity;
import com.example.risenclicker.Activities.PlayActivity;

public class Timer extends CountDownTimer {

    private String timer;
    private TextView timerView;
    private SharedPreferences ClickerData;
    private String scoreOut;
    private Context context;
    private long savedMillis;

    public Timer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public void timerSettings(String t, TextView tv, SharedPreferences cd, String so, Context c) {
        timer = t;
        timerView = tv;
        ClickerData = cd;
        scoreOut = so;
        context = c;
    }

    public void scoreRefresh(String so) {
        scoreOut = so;
    }

    public long saveTime() {
        return savedMillis;
    }

    public void onTick(long millisUntilFinished) {
        timer = "0" + millisUntilFinished / 60000 + ":";
        if ((millisUntilFinished % 60000 / 1000) < 10)
            timer += "0";
        timer += millisUntilFinished % 60000 / 1000;
        timerView.setText(timer);
        savedMillis = millisUntilFinished;
    }

    public void onFinish() {
        timer = "00:00";
        timerView.setText(timer);
        SharedPreferences.Editor editor = ClickerData.edit();
        editor.putString("currentScore", scoreOut);
        editor.apply();
        Intent intentEnd =
                new Intent(context, EndgameActivity.class);
        context.startActivity(intentEnd);
    }
}
