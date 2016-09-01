package com.kalerkantho.Model;

/**
 * Created by hp on 9/1/2016.
 */
public class CommentInfo {
    private String connent_id;
    private String comment_text;
    private String news_id;
    private String created_at;
    private String user_id;
    private String full_name;
    private String email;

    public String getConnent_id() {
        return connent_id;
    }

    public void setConnent_id(String connent_id) {
        this.connent_id = connent_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
