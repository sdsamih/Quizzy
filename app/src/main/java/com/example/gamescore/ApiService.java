package com.example.gamescore;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api.php?amount=10")
    Call<Object> getQuestions();
}
