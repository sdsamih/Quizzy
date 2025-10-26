package com.example.gamescore;

import android.util.Log;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_questions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList <Question> questions = (ArrayList<Question>) getIntent().getSerializableExtra("questions");
        boolean isDailyChallenge = getIntent().getBooleanExtra("isDailyChallenge", false);


        RecyclerView recyclerView = findViewById(R.id.recycler_questions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //show itens linearly and vertically
        recyclerView.setAdapter( new QuestionsAdapter(questions)); //binds an QuestionsAdapter to the recyclervie


        for (Question q : questions){ //for debugging purposes
            Log.d("Questao", q.getQuestionText());
            Log.d("Alternativas", Arrays.toString(q.getOptions()));
        }


    }
}