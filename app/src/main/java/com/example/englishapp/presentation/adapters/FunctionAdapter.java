package com.example.englishapp.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishapp.R;
import com.example.englishapp.data.model.Function;
import com.example.englishapp.domain.CacheManager;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.FunctionViewHolder> {

    ArrayList<Function> functions;
    public interface OnItemClickListener {
        void onItemClick(Function function);
    }

    private final OnItemClickListener listener;

    public FunctionAdapter(ArrayList<Function> functions, OnItemClickListener listener) {
        this.functions = functions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FunctionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_function, parent, false);
        return new FunctionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FunctionViewHolder holder, int position) {
        Function function = functions.get(position);
        holder.bind(function);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(function));
    }

    @Override
    public int getItemCount() {
        return functions.size();
    }

    public static class FunctionViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public FunctionViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_img);
            textView = itemView.findViewById(R.id.item_function_name);
        }

        public void bind(Function function) {
            textView.setText(function.getName());
            Future<File> future = CacheManager.cacheImageFile(imageView.getContext(),function.getImageUrl());
            try{
                File cacheFile = future.get();
                Glide.with(imageView.getContext()).load(cacheFile).into(imageView);
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
