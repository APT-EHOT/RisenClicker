package com.example.risenclicker.Support;

public class ScoreItem {

    private double score;
    private String name;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScoreItem(double score, String name) {
        this.score = score;
        this.name = name;
    }

}
