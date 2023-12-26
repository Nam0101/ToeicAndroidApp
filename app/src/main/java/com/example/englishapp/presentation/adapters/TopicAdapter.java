package com.example.englishapp.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.data.model.Topic;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {
    private final List<Topic> topics;
    private final OnItemClickListener listener;

    public TopicAdapter(List<Topic> topics, OnItemClickListener listener) {
        this.topics = topics;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        holder.bind(topics.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Topic topic);
    }

    class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvNumber;

        TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.topic_name);
            tvNumber = itemView.findViewById(R.id.topic_number);
        }

        void bind(Topic topic, OnItemClickListener listener) {
            tvName.setText(topic.getTopic());
            tvNumber.setText(String.valueOf(topic.getId()));
            itemView.setOnClickListener(v -> listener.onItemClick(topic));
        }
    }
}