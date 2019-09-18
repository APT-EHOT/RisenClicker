package com.example.risenclicker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.risenclicker.R;
import com.example.risenclicker.Support.ScoreItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RanksActivity extends AppCompatActivity {

    private SharedPreferences RanksData;

    private Button btn;

    private TextView tvl1;
    private TextView tvl2;
    private TextView tvl3;
    private TextView tvl4;
    private TextView tvl5;

    private TextView tvl6;
    private TextView tvl7;
    private TextView tvl8;
    private TextView tvl9;
    private TextView tvl10;

    private TextView tvr1;
    private TextView tvr2;
    private TextView tvr3;
    private TextView tvr4;
    private TextView tvr5;

    private TextView tvr6;
    private TextView tvr7;
    private TextView tvr8;
    private TextView tvr9;
    private TextView tvr10;

    private ArrayList<ScoreItem> scores = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranks);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        RanksData = getSharedPreferences("RanksData", Context.MODE_PRIVATE);
        btn = findViewById(R.id.button);

        tvl1 = findViewById(R.id.textViewL1);
        tvl2 = findViewById(R.id.textViewL2);
        tvl3 = findViewById(R.id.textViewL3);
        tvl4 = findViewById(R.id.textViewL4);
        tvl5 = findViewById(R.id.textViewL5);

        tvl6 = findViewById(R.id.textViewL6);
        tvl7 = findViewById(R.id.textViewL7);
        tvl8 = findViewById(R.id.textViewL8);
        tvl9 = findViewById(R.id.textViewL9);
        tvl10 = findViewById(R.id.textViewL10);

        tvr1 = findViewById(R.id.textViewR1);
        tvr2 = findViewById(R.id.textViewR2);
        tvr3 = findViewById(R.id.textViewR3);
        tvr4 = findViewById(R.id.textViewR4);
        tvr5 = findViewById(R.id.textViewR5);

        tvr6 = findViewById(R.id.textViewR6);
        tvr7 = findViewById(R.id.textViewR7);
        tvr8 = findViewById(R.id.textViewR8);
        tvr9 = findViewById(R.id.textViewR9);
        tvr10 = findViewById(R.id.textViewR10);

        Gson gson = new Gson();
        String json = RanksData.getString("ranks", "");
        Type type = new TypeToken<ArrayList<ScoreItem>>() {}.getType();
        scores = gson.fromJson(json, type);
        ScoreItem emptyItem = new ScoreItem(0.0, "-----");
        if (scores == null) {
            ArrayList<ScoreItem> helper = new ArrayList<>();
            scores = helper;
        }
        if (scores.size() < 10) {
            for (int i = scores.size() - 1; i < 10; i++) {
                scores.add(emptyItem);
            }
        }

        String setName;
        String setScore;

        setName = "1. " + scores.get(0).getName();
        tvl1.setText(setName);
        setName = "2. " + scores.get(1).getName();
        tvl2.setText(setName);
        setName = "3. " + scores.get(2).getName();
        tvl3.setText(setName);
        setName = "4. " + scores.get(3).getName();
        tvl4.setText(setName);
        setName = "5. " + scores.get(4).getName();
        tvl5.setText(setName);

        setName = "6. " + scores.get(5).getName();
        tvl6.setText(setName);
        setName = "7. " + scores.get(6).getName();
        tvl7.setText(setName);
        setName = "8. " + scores.get(7).getName();
        tvl8.setText(setName);
        setName = "9. " + scores.get(8).getName();
        tvl9.setText(setName);
        setName = "10. " + scores.get(9).getName();
        tvl10.setText(setName);

        setScore = Double.toString(scores.get(0).getScore());
        tvr1.setText(setScore);
        setScore = Double.toString(scores.get(1).getScore());
        tvr2.setText(setScore);
        setScore = Double.toString(scores.get(2).getScore());
        tvr3.setText(setScore);
        setScore = Double.toString(scores.get(3).getScore());
        tvr4.setText(setScore);
        setScore = Double.toString(scores.get(4).getScore());
        tvr5.setText(setScore);

        setScore = Double.toString(scores.get(5).getScore());
        tvr6.setText(setScore);
        setScore = Double.toString(scores.get(6).getScore());
        tvr7.setText(setScore);
        setScore = Double.toString(scores.get(7).getScore());
        tvr8.setText(setScore);
        setScore = Double.toString(scores.get(8).getScore());
        tvr9.setText(setScore);
        setScore = Double.toString(scores.get(9).getScore());
        tvr10.setText(setScore);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReturn =
                        new Intent(RanksActivity.this, MainActivity.class);
                startActivity(intentReturn);
            }
        });
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
