package com.example.gamescore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button BtnStart = findViewById(R.id.btn_start);
        EditText TxtNumQuestions = findViewById(R.id.txt_num_questions);

        BtnStart.setOnClickListener(v -> {
            ApiService apiService = RetrofitClient.getClient().create(ApiService.class); //creates the client with ApiService endpoints

            Call<Object> call = apiService.getQuestions(); //makes the request

            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("API", "Sucesso: " + response.body().toString());
                    } else {
                        Log.e("API", "Erro na resposta: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Log.e("API", "Erro na chamada: " + t.getMessage());
                }
            });



            /*String numQuestoes = TxtNumQuestions.getText().toString();
            Toast.makeText(this, numQuestoes + " Questões selecionadas", Toast.LENGTH_SHORT).show();

            ArrayList<Question> questions = new ArrayList<>();
            for(int i=0; i<10;i++){
                Question question = new Question("Pergunta "+ i, new String[]{"Alternativa 1", "Alternativa 2", "Alternativa 3", "Alternativa 4"}, 0);
                questions.add(question);
            }


            Intent intentStart = new Intent(this, QuestionsActivity.class);
            intentStart.putExtra("questions", questions);

            startActivity(intentStart);
            */
        });
    }
}