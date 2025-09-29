package com.example.gamescore;
import java.io.Serializable;

public class Question implements Serializable {
    private String questionText;
    private String[] options;
    private int correctOptionIndex; //0-index
    private Boolean answeredCorrectly; //used for uptading the QuestionActivity when finishing the game
    private Boolean answered; //used for uptading the QuestionActivity when finishing the game

    private int selectedOptionIndex; //used for uptading the QuestionActivity when finishing the game

    public Question(String questionText, String[] options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.answeredCorrectly = false;
        this.answered= false;
        this.selectedOptionIndex = -1;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }


    public Boolean isAnsweredCorrectly(){
        return answeredCorrectly;
    }

    public void setAnsweredCorrectly(Boolean answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }
    public void selectedOptionIndex(int index){
        this.correctOptionIndex = index;
    }

    public Boolean isAnswered(){
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public int getSelectedOptionIndex(){
        return selectedOptionIndex;
    }

    public void setSelectedOptionIndex(int index){
        this.selectedOptionIndex  = index;
    }
}
