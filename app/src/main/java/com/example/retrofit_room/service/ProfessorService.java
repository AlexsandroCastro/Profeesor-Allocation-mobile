package com.example.retrofit_room.service;

import com.example.retrofit_room.model.Professor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfessorService {

    @GET("/professors")
    Call<List<Professor>> getAllProfessors();
}
