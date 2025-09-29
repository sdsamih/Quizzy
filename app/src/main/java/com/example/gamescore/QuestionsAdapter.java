package com.example.gamescore;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {
    private List<Question> questions; //Question objects array (data source)
    private Boolean submitted = false;

    public QuestionsAdapter(List<Question> questions) {
        this.questions = questions;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView questionTitle;
        public RadioGroup radioGroupOptions;

        public ViewHolder(View itemView) {
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

        if ("SEND".equals(question.getQuestionText())) { //band-aid to add the button as a recyclerview item
            holder.questionTitle.setVisibility(View.GONE);
            holder.radioGroupOptions.setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.card_option).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.btn_send).setVisibility(View.VISIBLE);

            holder.itemView.findViewById(R.id.btn_send).setOnClickListener(v -> { //submit answers
                int correctAnswers = 0;
                int total = 0;
                for (Question q : questions) {
                    if ("SEND".equals(q.getQuestionText())) continue;
                    if (!q.isAnswered()) {
                        Toast.makeText(holder.itemView.getContext(), "Responda todas as questões", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (q.isAnsweredCorrectly()) correctAnswers++;
                    total++;
                }
                submitted = true;
                holder.itemView.findViewById(R.id.btn_send).setVisibility(View.GONE);
                Toast.makeText(holder.itemView.getContext(), "Score: " + correctAnswers + "/" + total, Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            });

        } else { //question item
            holder.questionTitle.setVisibility(View.VISIBLE);
            holder.radioGroupOptions.setVisibility(View.VISIBLE);
            holder.itemView.findViewById(R.id.card_option).setVisibility(View.VISIBLE);

            CardView card = holder.itemView.findViewById(R.id.card_option);

            if (question.isAnswered()) {
                if (question.isAnsweredCorrectly()) {
                    card.setBackgroundColor(Color.GREEN);
                } else {
                    card.setBackgroundColor(Color.RED);
                }
            } else {
                card.setBackgroundColor(Color.CYAN);
            }

            holder.questionTitle.setText(question.getQuestionText());
            holder.radioGroupOptions.removeAllViews();

            String[] options = question.getOptions(); //options stored in Question object
            for (int i = 0; i < options.length; i++) {
                RadioButton radioButton = new RadioButton(holder.radioGroupOptions.getContext());
                radioButton.setText(options[i]);
                holder.radioGroupOptions.addView(radioButton);

                if (i == question.getSelectedOptionIndex()) radioButton.setChecked(true);
                if (submitted){
                    radioButton.setEnabled(false); //no changes after submission
                }
            }

            if (!question.isAnswered() && !submitted) { //listener only before submitting
                holder.radioGroupOptions.setOnCheckedChangeListener((group, checkedId) -> {
                    int selectedIndex = group.indexOfChild(group.findViewById(checkedId));
                    question.setAnswered(true);
                    question.setAnsweredCorrectly(selectedIndex == question.getCorrectOptionIndex());
                    question.setSelectedOptionIndex(selectedIndex);
                });
            } else {
                holder.radioGroupOptions.setOnCheckedChangeListener(null);
            }
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
