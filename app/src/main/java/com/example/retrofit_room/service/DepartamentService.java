package com.example.retrofit_room.service;

import com.example.retrofit_room.model.Departament;
import com.example.retrofit_room.model.DepartamentDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DepartamentService {

    @GET("/departments")
    Call<List<Departament>> getAllDepartaments();

    @POST("/departments")
    Call<Departament> createDepatament(@Body DepartamentDTO dto);

    @GET("/departments/{department_id}")
    Call<Departament> getDepartmentById ( @Path("department_id") Long id);

    @PUT("/departments/{department_id}")
    Call<Departament> alterar(
            @Path("department_id") Long id,
            @Body DepartamentDTO dto
    );

    @DELETE("/departments/{department_id}")
    Call<Departament> deleteDepartmentById ( @Path("department_id") Long id);

}
