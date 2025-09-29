package com.example.gamescore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
    private List<Question> questions; //Question objects array (data source)

    public QuestionsAdapter(List<Question> questions) {
        this.questions=questions;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView questionTitle;
        public RadioGroup radioGroupOptions;

        public ViewHolder(View itemView){
            super(itemView);
            questionTitle = itemView.findViewById(R.id.txt_question_title);
            radioGroupOptions = itemView.findViewById(R.id.rdo_group_options);
        }
    }

    @NonNull
    @Override
    public QuestionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.questionTitle.setText(question.getQuestionText());

        String[] options = question.getOptions(); //options stored in Question object
        for(int i=0; i<options.length; i++){ //for each option in the Question
            RadioButton radioButton = new RadioButton(holder.radioGroupOptions.getContext()); //creates a radio-button
            radioButton.setText(options[i]); //sets the option text
            holder.radioGroupOptions.addView(radioButton); //then binds it to the radioGroup
        }

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
