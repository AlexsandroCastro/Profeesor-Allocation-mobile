package com.example.retrofit_room;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retrofit_room.adapter.CursoAdapter;
import com.example.retrofit_room.adapter.DepartamentAdapter;
import com.example.retrofit_room.model.Curso;
import com.example.retrofit_room.model.Departament;
import com.example.retrofit_room.service.CursoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CursoActivity extends AppCompatActivity {

    private CursoService servicee;
    private ListView list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        list = findViewById(R.id.listview_curso);


        servicee = RetrofitConfig
                .newInstance()
                .courseService();

        servicee.getAllCousers().enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
                List<Curso> lista = response.body();

                for (Curso item : lista){
                    Log.e(">>>>", item.getName());
                }

                  setAdapter(lista);
            }

            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {

            }
        });
    }

    private void setAdapter(List<Curso> cursos){

        CursoAdapter cursoAdapter = new CursoAdapter(CursoActivity.this, cursos);
        cursoAdapter.setDropDownViewResource(0);
        list.setAdapter(cursoAdapter);

        list.setOnItemClickListener((adapterView, view, i, l) -> {

            Curso curso = cursoAdapter.getItem(i);
            Toast.makeText(CursoActivity.this, " Curso "
                    + curso.getName(), Toast.LENGTH_LONG).show();

        });

    }
}