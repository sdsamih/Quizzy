package com.example.gamescore;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api.php?type=multiple") //only multiple choice questions
    Call<QuestionResponse> getQuestions(@Query("amount") int amount);

    @GET("daily_quiz") //only multiple choice questions
    Call<QuestionResponse> getDaily();
}
