package com.example.newsmania;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private List<Article> articleList = new ArrayList<>();
    private NewsAdapter adapter = new NewsAdapter(articleList);
    private TextView topNews;
    private Button bGen, bEnt, bBus, bHea, bSci, bSpo, bTec;
    private SearchView searchView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        bGen = findViewById(R.id.b_general);
        bEnt = findViewById(R.id.b_entertainment);
        bBus = findViewById(R.id.b_business);
        bHea = findViewById(R.id.b_health);
        bSci = findViewById(R.id.b_science);
        bSpo = findViewById(R.id.b_sports);
        bTec = findViewById(R.id.b_tech);
        bGen.setOnClickListener(this);
        bEnt.setOnClickListener(this);
        bBus.setOnClickListener(this);
        bHea.setOnClickListener(this);
        bSci.setOnClickListener(this);
        bSpo.setOnClickListener(this);
        bTec.setOnClickListener(this);

//        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
//        searchEditText.setTextColor(getResources().getColor(R.color.font_brown));
        progressBar = findViewById(R.id.progressBar2);
        searchView = findViewById(R.id.searchView);
        topNews = findViewById(R.id.textView);
        adapter = new NewsAdapter(articleList);
        getNews("GENERAL",null);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                getNews("GENERAL", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    public void setRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeScreen.this));
        recyclerView.setAdapter(adapter);
    }

    public void getNews(String category, String query) {
        NewsApiClient newsApiClient = new NewsApiClient(getText(R.string.api_key).toString());
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .language("en")
                        .q(query)
                        .category(category)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                articleList.clear();
                                articleList.addAll(response.getArticles());
                                for (Article article : articleList) {
                                    System.out.println("Article " + article.getTitle());
                                }
                                updateArticleList(articleList);

                            }
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("failed to get articles");
                    }
                }
        );
    }

    public void updateArticleList(List<Article> articles) {

        adapter = new NewsAdapter(articles);
        System.out.println("successful");
        setRecyclerView();
        adapter.updateNewsData(articles);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String category = btn.getText().toString();
        getNews(category, null);

    }

}