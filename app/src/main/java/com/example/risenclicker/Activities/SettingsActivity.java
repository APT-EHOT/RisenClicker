package com.example.risenclicker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.risenclicker.R;

public class SettingsActivity extends AppCompatActivity {

    private Button buttonEasy;
    private Button buttonMedium;
    private Button buttonHard;
    private Button buttonBack;

    private TextView description;

    private SharedPreferences ClickerData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ClickerData = getSharedPreferences("ClickerData", Context.MODE_PRIVATE);

        description = findViewById(R.id.textView2);
        buttonEasy = findViewById(R.id.button1);
        buttonMedium = findViewById(R.id.button2);
        buttonHard = findViewById(R.id.button3);
        buttonBack = findViewById(R.id.button4);

        switch (ClickerData.getString("difficulty", "")) {
            case "0":
                description.setText(getString(R.string.description_easy));
                highlightEasy(true);
                highlightMedium(false);
                highlightHard(false);
                break;
            case "1":
                description.setText(getString(R.string.description_medium));
                highlightEasy(false);
                highlightMedium(true);
                highlightHard(false);
                break;
            case "2":
                description.setText(getString(R.string.description_hard));
                highlightEasy(false);
                highlightMedium(false);
                highlightHard(true);
                break;
            default:
                break;
        }

        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = ClickerData.edit();
                editor.putString("difficulty", "0" );
                editor.apply();
                highlightEasy(true);
                highlightMedium(false);
                highlightHard(false);
                description.setText(getString(R.string.description_easy));
            }
        });

        buttonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = ClickerData.edit();
                editor.putString("difficulty", "1" );
                editor.apply();
                highlightEasy(false);
                highlightMedium(true);
                highlightHard(false);
                description.setText(getString(R.string.description_medium));
            }
        });

        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = ClickerData.edit();
                editor.putString("difficulty", "2" );
                editor.apply();
                highlightEasy(false);
                highlightMedium(false);
                highlightHard(true);
                description.setText(getString(R.string.description_hard));
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent =
                        new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(backIntent);
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

    public void highlightEasy(boolean selected) {
        if (selected) {
            buttonEasy.setBackgroundColor(ContextCompat
                    .getColor(SettingsActivity.this,
                            R.color.foneButtonAccent));
            buttonEasy.setTextColor(ContextCompat
                    .getColor(SettingsActivity.this,
                            R.color.textButtonAccent));
        } else {
            buttonEasy.setBackgroundColor(ContextCompat
                    .getColor(SettingsActivity.this,
                            R.color.foneButtonStandard));
            buttonEasy.setTextColor(ContextCompat
                    .getColor(SettingsActivity.this,
                            R.color.textButtonStandard));
        }
    }

    public void highlightMedium(boolean selected) {
        if (selected) {
            buttonMedium.setBackgroundColor(ContextCompat
                    .getColor(SettingsActivity.this,
                            R.color.foneButtonAccent));
            buttonMedium.setTextColor(ContextCompat
                    .getColor(SettingsActivity.this,
                            R.color.textButtonAccent));
        } else {
            buttonMedium.setBackgroundColor(ContextCompat
                    .getColor(SettingsActivity.this,
                            R.color.foneButtonStandard));
            buttonMedium.setTextColor(ContextCompat
                    .getColor(SettingsActivity.this,
                            R.color.textButtonStandard));
        }
    }

    public void highlightHard(boolean selected) {
        if (selected) {
            buttonHard.setBackgroundColor(ContextCompat
                    .getColor(SettingsActivity.this,
                            R.color.foneButtonAccent));
            buttonHard.setTextColor(ContextCompat
                    .getColor(SettingsActivity.this,
                            R.color.textButtonAccent));
        } else {
            buttonHard.setBackgroundColor(ContextCompat
                    .getColor(SettingsActivity.this,
                            R.color.foneButtonStandard));
            buttonHard.setTextColor(ContextCompat
                    .getColor(SettingsActivity.this,
                            R.color.textButtonStandard));
        }
    }

}
