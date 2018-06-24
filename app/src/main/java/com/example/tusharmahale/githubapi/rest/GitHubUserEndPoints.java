package com.example.tusharmahale.githubapi.rest;

import com.example.tusharmahale.githubapi.model.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubUserEndPoints {

        @GET("/users/{user}")
    Call<GithubUser>getUsername(@Path("user") String user);




}
