package com.example.gamescore;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit client; //client that will make the requests

    public static Retrofit  getClient(String baseUrl){ //builder method that returns the client
        if(client == null){ //(Singleton)
            client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return client;
    }
}
