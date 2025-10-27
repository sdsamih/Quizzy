// RankingResponse.java
package com.example.gamescore;

import java.util.List;

public class RankingResponse {
    private List<RankingEntry> ranking;

    public List<RankingEntry> getRanking() {
        return ranking;
    }

    public void setRanking(List<RankingEntry> ranking) {
        this.ranking = ranking;
    }
}