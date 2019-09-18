package com.example.risenclicker.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.risenclicker.Support.Inits;
import com.example.risenclicker.R;
import com.example.risenclicker.Support.ScoreComparator;
import com.example.risenclicker.Support.ScoreItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class EndgameActivity extends AppCompatActivity {

    private TextView scoreText;
    private TextView rawText;
    private TextView difficultyText;
    private TextView multiText;

    private Button retryButton;
    private Button exitButton;
    private SharedPreferences ClickerData;
    private SharedPreferences RanksData;

    private String currentScore;
    private double preOutScore;
    private String outScore;
    private String saveName;

    Inits inits = new Inits();

    private ArrayList<ScoreItem> scores = new ArrayList<>();
    private ScoreComparator scoreComparator = new ScoreComparator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ClickerData = getSharedPreferences("ClickerData", Context.MODE_PRIVATE);
        RanksData = getSharedPreferences("RanksData", Context.MODE_PRIVATE);

        scoreText = findViewById(R.id.textView3);
        rawText = findViewById(R.id.textViewR1);
        difficultyText = findViewById(R.id.textViewR2);
        multiText = findViewById(R.id.textViewR3);

        retryButton = findViewById(R.id.button1);
        exitButton = findViewById(R.id.button2);

        currentScore = ClickerData.getString("currentScore", "");

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

        rawText.setText(currentScore);
        switch (ClickerData.getString("difficulty", "")) {
            case "0":
                difficultyText.setText(R.string.button_easy);
                multiText.setText(R.string.multi_easy);
                scoreText.setText(currentScore);
                preOutScore = inits.shrinkScore(Double.parseDouble(currentScore));
                if (preOutScore > scores.get(9).getScore()){
                    showDialog();
                }
                break;
            case "1":
                difficultyText.setText(R.string.button_medium);
                multiText.setText(R.string.multi_medium);
                preOutScore = inits.shrinkScore(Double.parseDouble(currentScore) * 1.25);
                outScore = Double.toString(preOutScore);
                scoreText.setText(outScore);
                if (preOutScore > scores.get(9).getScore())
                    showDialog();
                    break;
            case "2":
                difficultyText.setText(R.string.button_hard);
                multiText.setText(R.string.multi_hard);
                preOutScore = inits.shrinkScore(Double.parseDouble(currentScore) * 1.5);
                outScore = Double.toString(preOutScore);
                scoreText.setText(outScore);
                if (preOutScore > scores.get(9).getScore())
                    showDialog();
                    break;
            default:
                break;

        }

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRetry =
                        new Intent(EndgameActivity.this, PlayActivity.class);
                startActivity(intentRetry);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentExit =
                        new Intent(EndgameActivity.this, MainActivity.class);
                startActivity(intentExit);
            }
        });
    }

    public void writeScore(double score, String name) {
        scores.remove(9);
        ScoreItem newItem = new ScoreItem(score, name);
        scores.add(newItem);
        Collections.sort(scores, scoreComparator);
        SharedPreferences.Editor editor = RanksData.edit();
        Gson gson = new Gson();
        String json = gson.toJson(scores);
        editor.putString("ranks", json);
        editor.apply();
    }

    public void showDialog() {
        TextView titleView = new TextView(EndgameActivity.this);
        titleView.setText(R.string.dialTitle);
        titleView.setTextColor(ContextCompat
                .getColor(EndgameActivity.this, R.color.textButtonStandard));
        titleView.setTextSize(20);
        titleView.setPadding(64, 64,64,64);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        AlertDialog.Builder builder = new AlertDialog.Builder(EndgameActivity.this);
        final LayoutInflater inflater = getLayoutInflater();
        final View textEntryView =
                inflater.inflate(R.layout.dialog_layout, null);
        final EditText editText = textEntryView.findViewById(R.id.editText);
        editText.setText(ClickerData.getString("lastname", ""));
        builder.setView(textEntryView)
                .setCustomTitle(titleView)
                .setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        saveName = editText.getText().toString();
                        SharedPreferences.Editor editor = ClickerData.edit();
                        editor.putString("lastname", saveName);
                        editor.apply();
                        writeScore(preOutScore, saveName);
                    }
                });
        AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawableResource(R.color.backDial);
        alert.show();
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
