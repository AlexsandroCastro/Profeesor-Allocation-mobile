package com.example.retrofit_room;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retrofit_room.adapter.AllocationAdapter;
import com.example.retrofit_room.adapter.CursoAdapter;
import com.example.retrofit_room.model.Allocation;
import com.example.retrofit_room.model.Curso;
import com.example.retrofit_room.service.AllocationService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllocationActivity extends AppCompatActivity {

    private AllocationService allocationService;
    private ListView list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation2);

        list = findViewById(R.id.listview_allocation);

        allocationService = RetrofitConfig.newInstance().allocationService();

        allocationService.getAllAllocations().enqueue(new Callback<List<Allocation>>() {
            @Override
            public void onResponse(Call<List<Allocation>> call, Response<List<Allocation>> response) {
                List<Allocation> lista = response.body();

                for (Allocation item : lista){
                    Log.e(" Allocation >>>>", item.getName());
                }

                setAdapter(lista);
            }

            @Override
            public void onFailure(Call<List<Allocation>> call, Throwable t) {

            }
        });
    }

    private void setAdapter(List<Allocation> allocations){

        AllocationAdapter allocationAdapter = new AllocationAdapter(AllocationActivity.this, allocations);
        allocationAdapter.setDropDownViewResource(0);
        list.setAdapter(allocationAdapter);

        list.setOnItemClickListener((adapterView, view, i, l) -> {

            Allocation allocation = allocationAdapter.getItem(i);
            Toast.makeText(AllocationActivity.this, " Allocation "
                    + allocation.getName(), Toast.LENGTH_LONG).show();

        });

    }
}