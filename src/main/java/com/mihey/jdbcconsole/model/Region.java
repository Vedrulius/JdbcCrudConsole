package com.mihey.jdbcconsole.model;

public class Region {
    private Integer id;
    private String name;

    public Region(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
