package com.example.retrofit_room.model;

public class Allocation {

    private int id;

    private String dayOfWeek;

    private Professor professor;

    private Course course;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Course getCurso() {
        return course;
    }

    public void setCurso(Course curso) {
        this.course = curso;
    }

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
