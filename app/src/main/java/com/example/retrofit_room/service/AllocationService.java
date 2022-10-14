package com.example.retrofit_room.service;

import com.example.retrofit_room.model.Allocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AllocationService {

    @GET("/allocations")
    Call<List<Allocation>> getAllAllocations();
}
