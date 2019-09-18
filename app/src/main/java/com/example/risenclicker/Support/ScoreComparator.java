package com.example.risenclicker.Support;

import java.util.Comparator;

public class ScoreComparator implements Comparator<ScoreItem> {

    public int compare(ScoreItem scoreItem1, ScoreItem scoreItem2) {

        double rank1 = scoreItem1.getScore();
        double rank2 = scoreItem2.getScore();

        if (rank1 > rank2){
            return -1;
        } else if (rank1 < rank2){
            return +1;
        } else {
            return 0;
        }
    }
}
