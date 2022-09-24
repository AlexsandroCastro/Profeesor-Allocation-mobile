package com.example.retrofit_room.model;

import java.util.List;

public class Departament {

    private int id;

    private String name;

    private transient List<Professors> professors;


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

    public List<Professors> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professors> professors) {
        this.professors = professors;
    }
}
