package com.example.retrofit_room.model;

public class Allocation {

    private int id;

    private String dayOfWeek;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return dayOfWeek;
    }

    public void setName(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
