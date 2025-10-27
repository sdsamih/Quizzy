package com.example.gamescore;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingActivity extends AppCompatActivity {

    private static final String TAG = "RankingActivity";

    private RecyclerView recyclerView;
    private RankingAdapter adapter;
    private List<RankingEntry> rankingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ranking);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycler_ranking);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RankingAdapter(rankingList);
        recyclerView.setAdapter(adapter);

        fetchRanking();
    }

    private void fetchRanking() {
        String url = "https://quizzy-server-aoof.onrender.com/"; // endereço do backend
        ApiService apiService = RetrofitClient.getClient(url).create(ApiService.class);
        Call<RankingResponse> call = apiService.getRanking();

        call.enqueue(new Callback<RankingResponse>() {
            @Override
            public void onResponse(Call<RankingResponse> call, Response<RankingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    rankingList.clear();
                    rankingList.addAll(response.body().getRanking()); // usa getRanking() do wrapper
                    adapter.notifyDataSetChanged();
                    Log.d("RankingActivity", "Ranking recebido: " + response.body().getRanking().toString());
                } else {
                    Toast.makeText(RankingActivity.this, "Falha ao carregar ranking", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RankingResponse> call, Throwable t) {
                Toast.makeText(RankingActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
