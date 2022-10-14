package com.example.retrofit_room.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.retrofit_room.R;
import com.example.retrofit_room.model.Course;

import java.util.List;


public class CursoAdapter extends ArrayAdapter<Course> {

    public CursoAdapter(@NonNull Context context, List<Course> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.adapter_curso, parent, false);
        Course curso = getItem(position);


        TextView text = root.findViewById(R.id.text);
        text.setText(curso.getName());

        return root;
    }

}

