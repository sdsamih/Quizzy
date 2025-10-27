package com.example.gamescore;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import java.util.List;
import java.util.Map;

public interface ApiService {
    @GET("api.php?type=multiple") // only multiple choice questions
    Call<QuestionResponse> getQuestions(@Query("amount") int amount);

    @GET("daily_quiz") // daily quiz
    Call<QuestionResponse> getDaily();

    // Sends user score for daily quiz
    @POST("submit_score")
    Call<Map<String, String>> submitScore(@Body Map<String, Object> body);

    // receives daily quiz ranking
    @GET("ranking")
    Call<RankingResponse> getRanking();
}
