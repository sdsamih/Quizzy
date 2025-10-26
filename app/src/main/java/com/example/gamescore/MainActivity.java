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
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

        Button BtnDaily = findViewById(R.id.btn_daily);
        Button BtnStart = findViewById(R.id.btn_start);
        EditText TxtNumQuestions = findViewById(R.id.txt_num_questions);

        BtnStart.setOnClickListener(v -> startQuiz("quiz", TxtNumQuestions));
        BtnDaily.setOnClickListener(v -> startQuiz("daily", TxtNumQuestions));
    }

    private void startQuiz(String mode, EditText TxtNumQuestions) {
        final boolean isDailyChallenge = mode.equals("daily");
        int numQuestions = 10;
        String url;

        if (isDailyChallenge) {
            url = "http://10.0.2.2:5000";
        }
        else{
            String numStr = TxtNumQuestions.getText().toString();
            if (numStr.isEmpty()) {
                Toast.makeText(this, "Digite o número de questões!", Toast.LENGTH_SHORT).show();
                return;
            }
            numQuestions = Integer.parseInt(numStr);
            url = "https://opentdb.com";
        }

        final int numQuestionsFinal = numQuestions;
        ApiService apiService = RetrofitClient.getClient(url).create(ApiService.class); //creates the HTTP client with the set domain


        Call<QuestionResponse> call;
        if (isDailyChallenge){
            call = apiService.getDaily(); //makes the request using the endpoint set in ApiService
        }
        else{
            call = apiService.getQuestions(numQuestions); //makes the request using the endpoint set in ApiService
        }


        call.enqueue(new Callback<QuestionResponse>() { //assincronously creates the request (so it doesn't freeze the UI thread)
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) { //if it gets a response
                if (response.isSuccessful() && response.body() != null) { //if the response says success (200)
                    Log.d("API", "Sucesso: " + response.body().toString());

                    Toast.makeText(MainActivity.this, numQuestionsFinal + " Questões selecionadas", Toast.LENGTH_SHORT).show();

                    QuestionResponse questionResponse = response.body();
                    ArrayList<Question> questions = new ArrayList<>(); //to-be filled Question array that will be used to populate QuestionActivity

                    for (QuestionResult questionResult : questionResponse.getResults()) { //iterates trough the response's QuestionResults array to create the Question objects
                        String questionText = questionResult.getQuestion(); //gets the question title

                        String[] options = new String[4]; //creates the (to-be-filled) questions array

                        Random random = new Random(); //Creates a random index for the correct option
                        int correctOptionIndex = random.nextInt(4);

                        List<String> shuffled_incorrect_options = questionResult.getIncorrectAnswers(); //creates the incorrect options list (arrays can't be shuffled directly)
                        Collections.shuffle(shuffled_incorrect_options); //shuffles the incorrect options

                        for (int i =0;i<4;i++){// fills the options array with the correct and incorrect options
                            if(i == correctOptionIndex){
                                options[i] = questionResult.getCorrectAnswer();
                            }
                            else{
                                options[i] = shuffled_incorrect_options.remove(shuffled_incorrect_options.size()-1); //Pops the last option (should have used a stack here)
                            }
                        }

                        Question question = new Question(questionText, options, correctOptionIndex); //creates and adds the Question object to the array
                        questions.add(question);
                    }

                    questions.add(new Question("SEND", new String[]{}, 0)); //part of the band-aid fix to keep the send buton in the recyclerview

                    Intent intentStart = new Intent(MainActivity.this, QuestionsActivity.class);
                    intentStart.putExtra("questions", questions); //sends the Question array to the next Activity (QuestionsActivity)

                    if(isDailyChallenge) {
                        intentStart.putExtra("isDailyChallenge", true);
                    }//sends the Question array to the next Activity (QuestionsActivity)
                    else{
                        intentStart.putExtra("isDailyChallenge", false);
                    }

                    startActivity(intentStart);

                } else { //if the response says failure (not 200)
                    Log.e("API", "Erro na resposta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) { //if fails to receive any response
                Log.e("API", "Erro na chamada: " + t.getMessage());
            }
        });
    }
}
