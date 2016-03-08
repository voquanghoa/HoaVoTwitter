package com.quanghoa.hoavotwitter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by voqua on 3/8/2016.
 */
public class TwitterPost {
    private String id;
    private String author;
    private String content;
    private List<String> images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
