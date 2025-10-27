package com.example.gamescore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitDailyActivity extends AppCompatActivity {

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_submit_daily);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Recebe o score passado pela Intent
        score = getIntent().getIntExtra("score", 0);

        // Mostra o score na tela
        TextView txtScore = findViewById(R.id.textView4);
        txtScore.setText("Score: " + score);

        // Botão para enviar score
        Button btnSendScore = findViewById(R.id.btn_send_score);
        btnSendScore.setOnClickListener(v -> {
            // Pega o nome do usuário do EditText
            EditText edtName = findViewById(R.id.edt_name); // supondo que exista no layout
            String username = edtName.getText().toString().trim();

            if (username.isEmpty()) {
                Toast.makeText(this, "Digite seu nome", Toast.LENGTH_SHORT).show();
                return;
            }

            sendScore(username, score);
        });
    }

    private void sendScore(String username, int score) {
        // Cria o corpo da requisição
        Map<String, Object> body = new HashMap<>();
        body.put("name", username);
        body.put("score", score);

        // URL do backend
        String url = "https://quizzy-server-aoof.onrender.com/"; // localhost do emulador Android

        ApiService apiService = RetrofitClient.getClient(url).create(ApiService.class);
        Call<Map<String, String>> call = apiService.submitScore(body);

        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SubmitDailyActivity.this, "Score enviado!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SubmitDailyActivity.this, RankingActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SubmitDailyActivity.this, "Erro ao enviar score", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Toast.makeText(SubmitDailyActivity.this, "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
