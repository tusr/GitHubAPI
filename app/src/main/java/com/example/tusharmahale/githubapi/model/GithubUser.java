package com.example.tusharmahale.githubapi.model;

import com.google.gson.annotations.SerializedName;

public class GithubUser {
    @SerializedName("followers")
    private String followers;
    @SerializedName("following")
    private String following;
    @SerializedName("avatar_url")
    private String avatar;
    @SerializedName("email")
    private String email;
    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String username;

    @SerializedName("message")
    private String message;

    @SerializedName("public_repos")
    private String public_repos;

    public GithubUser(String followers, String following, String email, String login, String username, String avatar,String public_repos) {

        this.setFollowers(followers);
        this.setFollowing(following);
        this.setEmail(email);
        this.setLogin(login);
        this.setUsername(username);
        this.setAvatar(avatar);
        this.setPublic_repos(public_repos);
//        this.setMessage(message);
//

    }

    public String getPublic_repos() {
        return public_repos;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPublic_repos(String public_repos) {
        this.public_repos = public_repos;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
