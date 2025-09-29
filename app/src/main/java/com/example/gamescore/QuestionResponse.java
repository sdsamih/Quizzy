package com.example.gamescore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionResponse { //Class to store the actual response (in json) in an actual object
    @SerializedName("response_code")
    private int responseCode;

    @SerializedName("results")
    private List<QuestionResult> results;

    public int getResponseCode() { return responseCode; } //API's own response codes, used to represent internal issues with correctly made (200) requests
    public List<QuestionResult> getResults() { return results; }//List of individual api's questions (QuestionResult)
}
