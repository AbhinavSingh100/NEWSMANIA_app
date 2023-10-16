package com.example.newsmania;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kwabenaberko.newsapilib.models.Article;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> newsArticleList;
    public NewsAdapter(List<Article> newsArticleList) {
        this.newsArticleList = newsArticleList;

    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        System.out.println("onCreateViewHolder called");
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        // Bind data from the NewsArticle object to the views in the ViewHolder
        Article newsArticle = newsArticleList.get(position);
        holder.titleTextView.setText(newsArticle.getTitle());
        holder.authorTextView.setText(newsArticle.getAuthor());
        holder.sourceTextView.setText(newsArticle.getSource().getName());

        Glide.with(holder.itemView.getContext()).load(newsArticle.getUrlToImage()).error(R.drawable.image_63768a1a8179720017a5bee9_437393).placeholder(R.drawable.download__5_).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), NewsPage.class);
            intent.putExtra("url", newsArticle.getUrl());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(newsArticleList!=null) {
            return newsArticleList.size();
        }
        else
            return 0;
    }

    void updateNewsData(List<Article> newsArticleList) {
        this.newsArticleList = newsArticleList;
        this.newsArticleList.addAll(newsArticleList);
        Log.d("finding news", "updateNewsData: "+newsArticleList.size());
        notifyDataSetChanged();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView authorTextView;
        TextView sourceTextView;
        ImageView imageView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            sourceTextView = itemView.findViewById(R.id.sourceTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
