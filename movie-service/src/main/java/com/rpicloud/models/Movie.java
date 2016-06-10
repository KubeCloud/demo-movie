package com.rpicloud.models;

/**
 * Created by mixmox on 10/06/16.
 */
public class Movie {
    private String title;
    private String pictureUrl;
    private String actors;
    private int number;
    private int year;

    public Movie(String title, String pictureUrl, String actors, int year, int number) {
        this.title = title;
        this.pictureUrl = pictureUrl;
        this.actors = actors;
        this.year = year;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
