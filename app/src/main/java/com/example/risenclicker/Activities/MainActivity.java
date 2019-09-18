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
import com.example.risenclicker.Support.ExitDialog;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    private SharedPreferences ClickerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClickerData = getSharedPreferences("ClickerData", Context.MODE_PRIVATE);

        if(!ClickerData.contains("difficulty")) {
            SharedPreferences.Editor editor = ClickerData.edit();
            editor.putString("difficulty", "0" );
            editor.apply();
        }

        if(!ClickerData.contains("lastname")) {
            SharedPreferences.Editor editor = ClickerData.edit();
            editor.putString("lastname", "DefaultName" );
            editor.apply();
        }

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGame =
                        new Intent(MainActivity.this, PlayActivity.class);
                startActivity(intentGame);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSettings =
                        new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRanks =
                        new Intent(MainActivity.this, RanksActivity.class);
                startActivity(intentRanks);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitDialog exitDialog = new ExitDialog();
                exitDialog.setContextMine(MainActivity.this);
                exitDialog.show(getSupportFragmentManager(), "Exit dialog");
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
