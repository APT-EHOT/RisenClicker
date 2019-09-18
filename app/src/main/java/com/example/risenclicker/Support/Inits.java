package com.example.risenclicker.Support;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.risenclicker.Activities.SettingsActivity;
import com.example.risenclicker.R;

public class Inits {

    String SS[] = new String[4];
    String service = " ";

    public void initEasy(int nums[], Button btn1, Button btn2, Button btn3, Button btn4) {
        for (int i = 0; i < 4; i++) {
            nums[i] = (int) (Math.random() * (5 - 1 + 1) + 1);
        }
        SS[0] = "+" + nums[0];
        btn1.setText(SS[0]);
        SS[1] = "-" + nums[1];
        btn2.setText(SS[1]);
        SS[2] = "x" + nums[2];
        btn3.setText(SS[2]);
        SS[3] = "/" + nums[3];
        btn4.setText(SS[3]);
    }

    public void initMedium(double nums[], Button btn1, Button btn2, Button btn3, Button btn4) {
        for (int i = 0; i < 4; i++) {
            long rawResult = Math.round((Math.random() * 5 + 0.1) * 10);
            double readyResult = (double) rawResult / 10;
            nums[i] = readyResult;
        }
        SS[0] = "+" + nums[0];
        btn1.setText(SS[0]);
        SS[1] = "-" + nums[1];
        btn2.setText(SS[1]);
        SS[2] = "x" + nums[2];
        btn3.setText(SS[2]);
        SS[3] = "/" + nums[3];
        btn4.setText(SS[3]);
    }

    public void initHard(double nums[], int signs[], Button btn1, Button btn2, Button btn3, Button btn4, Context context) {

        int colorText[] = new int[4];
        int colorBack[] = new int[4];

        for (int i = 0; i < 4; i++) {
            long rawResult = Math.round((Math.random() * 5 + 0.1) * 10);
            double readyResult = (double) rawResult / 10;
            nums[i] = readyResult;
        }
        for (int i = 0; i < 4; i++) {
            signs[i] = (int) (Math.random() * 4);
            switch (signs[i]) {
                case 0: //+
                    SS[i] = "+" + nums[i];
                    colorText[i] = R.color.plusText;
                    colorBack[i] = R.color.plusFone;
                    break;
                case 1: //-
                    SS[i] = "-" + nums[i];
                    colorText[i] = R.color.minusText;
                    colorBack[i] = R.color.minusFone;
                    break;
                case 2: //*
                    SS[i] = "x" + nums[i];
                    colorText[i] = R.color.multiText;
                    colorBack[i] = R.color.multiFone;
                    break;
                case 3: //:
                    SS[i] = "/" + nums[i];
                    colorText[i] = R.color.divText;
                    colorBack[i] = R.color.divFone;
                    break;
                default:
                    SS[i] = "ERROR";
                    break;
            }
        }
        btn1.setText(SS[0]);
        btn1.setTextColor(ContextCompat
                .getColor(context, colorText[0]));
        btn1.setBackgroundColor(ContextCompat
                .getColor(context, colorBack[0]));
        btn2.setText(SS[1]);
        btn2.setTextColor(ContextCompat
                .getColor(context, colorText[1]));
        btn2.setBackgroundColor(ContextCompat
                .getColor(context, colorBack[1]));
        btn3.setText(SS[2]);
        btn3.setTextColor(ContextCompat
                .getColor(context, colorText[2]));
        btn3.setBackgroundColor(ContextCompat
                .getColor(context, colorBack[2]));
        btn4.setText(SS[3]);
        btn4.setTextColor(ContextCompat
                .getColor(context, colorText[3]));
        btn4.setBackgroundColor(ContextCompat
                .getColor(context, colorBack[3]));
    }

    public double scoring(double score, int signId, double variable, TextView tv, Context context) {
        switch (signId) {
            case 0:
                score += variable;
                service = "+" + variable;
                tv.setTextColor(ContextCompat
                    .getColor(context, R.color.plusText));
                tv.setText(service);
                break;
            case 1:
                score -= variable;
                service = "-" + variable;
                tv.setTextColor(ContextCompat
                        .getColor(context, R.color.minusText));
                tv.setText(service);
                break;
            case 2:
                score *= variable;
                service = "X" + variable;
                tv.setTextColor(ContextCompat
                        .getColor(context, R.color.multiText));
                tv.setText(service);
                break;
            case 3:
                score /= variable;
                service = "/" + variable;
                tv.setTextColor(ContextCompat
                        .getColor(context, R.color.divText));
                tv.setText(service);
                break;
            default:
                break;
        }
        return score;
    }

    public double shrinkScore(double score) {
        score *= 100;
        int scoreRaw = (int) score;
        score = (double) scoreRaw / 100;
        return score;
    }
}
