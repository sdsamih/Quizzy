package com.example.gamescore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

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
            String numQuestoes = TxtNumQuestions.getText().toString();
            Toast.makeText(this, numQuestoes + " Questões selecionadas", Toast.LENGTH_SHORT).show();

            ArrayList<Question> questions = new ArrayList<>();
            for(int i=0; i<10;i++){
                Question question = new Question("Pergunta "+ i, new String[]{"Alternativa 1", "Alternativa 2", "Alternativa 3", "Alternativa 4"}, 0);
                questions.add(question);
            }


            Intent intentStart = new Intent(this, QuestionsActivity.class);
            intentStart.putExtra("questions", questions);

            startActivity(intentStart);
        });
    }
}