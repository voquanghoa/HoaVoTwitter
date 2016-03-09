package com.quanghoa.hoavotwitter.model;

import java.util.List;

/**
 * Created by voqua on 3/8/2016.
 */

/***
 * The prototype describe each twitter post
 */
public class TwitterPost {
    private String id;
    private String author;
    private String content;
    private List<String> images;

    /***
     * Get the given id
     * @return The given id
     */
    public String getId() {
        return id;
    }

    /***
     * Set the post's id
     * @param id The post's id
     */
    public void setId(String id) {
        this.id = id;
    }

    /***
     * Get the post's author
     * @return The post's author
     */
    public String getAuthor() {
        return author;
    }

    /***
     * Set the post's author
     * @param author The post's author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /***
     * Get the post's content
     * @return The post's content
     */
    public String getContent() {
        return content;
    }

    /***
     * Set the post's content
     * @param content The post's content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /***
     * Get the list of url images
     * @return The list of url images
     */
    public List<String> getImages() {
        return images;
    }


    /***
     * Set the list of url images
     * @param images The list of url images
     */
    public void setImages(List<String> images) {
        this.images = images;
    }
}
