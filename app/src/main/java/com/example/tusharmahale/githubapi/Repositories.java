package com.example.tusharmahale.githubapi;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.tusharmahale.githubapi.model.GitHubRepo;
import com.example.tusharmahale.githubapi.model.ReposAdapter;
import com.example.tusharmahale.githubapi.rest.APIClient;
import com.example.tusharmahale.githubapi.rest.GitHubRepoEndPoint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories extends AppCompatActivity {
    String receivedUserName;
    TextView userNameTV;
    RecyclerView mrecyclerView;
    ProgressDialog pd;
    List<GitHubRepo> myDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.repobackground)));

        loadDialog();
        Bundle extras = getIntent().getExtras();
        receivedUserName = extras.getString("username");
        userNameTV = findViewById(R.id.userNameTV);

        userNameTV.setText("User: " + receivedUserName);

        mrecyclerView = (RecyclerView) findViewById(R.id.repos_recycler_view);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ReposAdapter(myDataSource, R.layout.list_item_repo,
                this);
        mrecyclerView.setAdapter(myAdapter);
        loadRepositories();
    }

    private void loadRepositories() {
        GitHubRepoEndPoint apiService = APIClient.getCliet().create(GitHubRepoEndPoint.class);
        Call<List<GitHubRepo>> call = apiService.getRepo(receivedUserName);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                pd.dismiss();
                myDataSource.clear();
                myDataSource.addAll(response.body());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<com.example.tusharmahale.githubapi.model.GitHubRepo>> call, Throwable t) {
pd.dismiss();
            }
        });
    }
    public void loadDialog()
    {
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait:Loading");
        pd.setCancelable(false);
        pd.show();
    }
}