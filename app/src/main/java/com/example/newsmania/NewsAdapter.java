package com.example.newsmania;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kwabenaberko.newsapilib.models.Article;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> newsArticleList;
    private SavedArticle savedArticle;
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
//        savedArticle = new SavedArticle();
//        savedArticle.setAuthor(newsArticle.getAuthor());
//        savedArticle.setTitle(newsArticle.getTitle());
//        savedArticle.setSourceName(newsArticle.getSource().getName());
//        savedArticle.setUrl(newsArticle.getUrl());
//        savedArticle.setUrlToImage(newsArticle.getUrlToImage());
        holder.titleTextView.setText(newsArticle.getTitle());
        holder.authorTextView.setText(newsArticle.getAuthor());
        holder.sourceTextView.setText(newsArticle.getSource().getName());

        Glide.with(holder.itemView.getContext()).load(newsArticle.getUrlToImage()).error(R.drawable.image_63768a1a8179720017a5bee9_437393).placeholder(R.drawable.download__5_).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), NewsPage.class);
            intent.putExtra("url", newsArticle.getUrl());
            v.getContext().startActivity(intent);
        });
        holder.popupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showPopupDialog(v.getContext());
                Toast.makeText(v.getContext(), "mnggkuyh", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showPopupDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View popupView = inflater.inflate(R.layout.popup_menu, null);

        // Customize the popup view as needed (e.g., set content, buttons, etc.)

        builder.setView(popupView);
//        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        ImageButton cross = popupView.findViewById(R.id.cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
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
        ImageView popupButton;

        public NewsViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            sourceTextView = itemView.findViewById(R.id.sourceTextView);
            imageView = itemView.findViewById(R.id.imageView);
            popupButton = itemView.findViewById(R.id.popupButton);
        }
    }

}
