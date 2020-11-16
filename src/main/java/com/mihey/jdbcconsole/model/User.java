package com.mihey.jdbcconsole.model;

import java.util.List;

public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private List<Post> post;
    private Region region;
    private Role role;


    public User(String firstName, String lastName, Region region) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
    }

    public User(Integer id, String firstName, String lastName, List<Post> post, Region region, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.post = post;
        this.region = region;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", post=" + post +
                ", region=" + region +
                ", role=" + role +
                '}';
    }
}
