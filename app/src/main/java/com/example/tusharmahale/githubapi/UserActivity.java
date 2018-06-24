package com.example.tusharmahale.githubapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tusharmahale.githubapi.model.GithubUser;
import com.example.tusharmahale.githubapi.rest.APIClient;
import com.example.tusharmahale.githubapi.rest.GitHubUserEndPoints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    Button repoButton;
    String status;
    TextView followersText,
            followingText,
            emailText,
            loginText,
            usernameText,publicrepo;
    ImageView userImage;
    Bundle extras;
    Bitmap myImage;
    String strUserName;
    ProgressDialog progress;
    Intent repoIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkgray)));

        repoButton = findViewById(R.id.repositaryButton);
        repoButton.setEnabled(false);
        followersText = findViewById(R.id.followers);
        followingText = findViewById(R.id.following);
        emailText = findViewById(R.id.email);
        loginText = findViewById(R.id.logIn);
        usernameText = findViewById(R.id.userName);
        userImage = findViewById(R.id.avatar);
        publicrepo = findViewById(R.id.publicrepo);
        repoIntent = new Intent(this,Repositories.class);

        extras = getIntent().getExtras();
        strUserName = extras.getString("userName");

        dialogMethod();
        loadData();

        repoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                repoIntent.putExtra("username",strUserName);
                startActivity(repoIntent);
            }
        });

    }
    public void loadData(){
        final GitHubUserEndPoints apiService = APIClient.getCliet().create(GitHubUserEndPoints.class);
        Call<GithubUser> call = apiService.getUsername(strUserName);
        call.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {

                ImageDownloader task = new ImageDownloader();
                try {

                    myImage = task.execute(response.body().getAvatar()).get();
                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    progress.dismiss();
//                    if(response.body().getMessage().equals("Not Found")) {
//                        Toast.makeText(getApplicationContext(), "User Not found", Toast.LENGTH_SHORT).show();
//                    }
//                    else{

                    userImage.setImageBitmap(myImage);
                    userImage.getLayoutParams().width = 220;
                    userImage.getLayoutParams().height = 220;


                    if (response.body().getUsername() == null) {
                        usernameText.setText("Username: not provided");
                    } else {
                        usernameText.setText("Username: " + response.body().getUsername());
                    }
                    if (response.body().getEmail() == null) {
                        emailText.setText("Email: not provided");
                    } else {
                        emailText.setText("Email: " + response.body().getEmail());
                    }
                    followersText.setText("Followers: " + response.body().getFollowers());
                    followingText.setText("Following: " + response.body().getFollowing());
                    loginText.setText("Login:" + response.body().getLogin());
                    publicrepo.setText("Repository: "+response.body().getPublic_repos());
                        if(!response.body().getPublic_repos().equals("0"))
                        {
                            repoButton.setEnabled(true);
                }}
            }
            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(),"Error occured",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void dialogMethod()
    {progress = new ProgressDialog(this);
        progress.setMessage("Loading..please Wait");
        progress.setCancelable(false);

        progress.show();}
}
