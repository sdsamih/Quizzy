package com.example.gamescore;

public class RankingEntry {
    private String name;
    private int score;
    private String created_at;

    public RankingEntry(String name, int score, String created_at) {
        this.name = name;
        this.score = score;
        this.created_at = created_at;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getCreated_at() {
        return created_at;
    }
}
