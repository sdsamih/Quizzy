package com.example.gamescore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    private List<RankingEntry> ranking;

    public RankingAdapter(List<RankingEntry> ranking) {
        this.ranking = ranking;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, txtScore;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtScore = itemView.findViewById(R.id.txt_score);
        }
    }

    @NonNull
    @Override
    public RankingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ranking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RankingEntry entry = ranking.get(position);
        holder.txtName.setText(entry.getName());
        holder.txtScore.setText(String.valueOf(entry.getScore()));
    }

    @Override
    public int getItemCount() {
        return ranking.size();
    }
}
