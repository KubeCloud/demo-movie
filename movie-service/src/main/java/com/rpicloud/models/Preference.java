package com.rpicloud.models;

/**
 * Created by mixmox on 10/06/16.
 */
public class Preference {
    private int id;
    private String name;

    public Preference() {
    }

    public Preference(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
