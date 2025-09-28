package com.example.gamescore;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api.php")
    Call<Object> getQuestions(@Query("amount") int amount);
}
