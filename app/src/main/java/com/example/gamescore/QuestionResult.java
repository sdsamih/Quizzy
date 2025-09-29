package com.example.gamescore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionResult {
@SerializedName("question") //Question title
    private String question;

    @SerializedName("correct_answer")//Correct option's text
    private String correctAnswer;

    @SerializedName("incorrect_answers") //List of incorrect options's respective texts
    private List<String> incorrectAnswers;


    public String getQuestion() {
        return question;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }
}
