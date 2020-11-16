package com.mihey.jdbcconsole.model;

import java.sql.Timestamp;

public class Post {
    private Integer id;
    private Integer userId;
    private String content;
    private Timestamp created;
    private Timestamp updated;

    public Post(String content,Integer userId) {
        this.userId = userId;
        this.content = content;
    }

    public Post(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public Post(Integer id, Integer userId, String content, Timestamp created, Timestamp updated) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
