package com.example.gamescore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        public ViewHolder(View itemView){
            super(itemView);
            questionTitle = itemView.findViewById(R.id.txt_question_title);
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
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
