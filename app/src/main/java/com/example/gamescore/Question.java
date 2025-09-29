package com.example.gamescore;
import java.io.Serializable;

public class Question implements Serializable {
    private String questionText;
    private String[] options;
    private int correctOptionIndex; //0-index

    public Question(String questionText, String[] options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }
    public String[] getOptions() {
        return options;
    }
    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}
