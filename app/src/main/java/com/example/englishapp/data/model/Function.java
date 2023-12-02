package com.example.englishapp.data.model;

public class Function {
    private int id;
    private String name;
    private String imageUrl;

    public Function(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
