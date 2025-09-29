package com.example.gamescore;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final  String BASE_URL = "https://opentdb.com"; //api domain
    private static Retrofit client; //client that will make the requests

    public static Retrofit  getClient(){ //builder method that returns the client
        if(client == null){ //(Singleton)
            client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return client;
    }
}
