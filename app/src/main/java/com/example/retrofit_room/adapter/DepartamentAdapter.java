package com.example.retrofit_room.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.retrofit_room.R;
import com.example.retrofit_room.callback.DepartamentCallback;
import com.example.retrofit_room.model.Departament;

import java.util.List;


public class DepartamentAdapter extends ArrayAdapter<Departament> {

    private DepartamentCallback departamentCallback;

    public DepartamentAdapter(@NonNull Context context,DepartamentCallback departamentCallback,List<Departament> list) {
        super(context, 0, list);
        this.departamentCallback = departamentCallback;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.adapter_departament, parent, false);
        Departament departament = getItem(position);


        TextView text = root.findViewById(R.id.text);
        text.setText(departament.getName());

        ImageView excluir = root.findViewById(R.id.img_lixeira);
        ImageView update = root.findViewById(R.id.img_atualizar);

        excluir.setOnClickListener(view -> {
            departamentCallback.onDelete(departament);
        });

        update.setOnClickListener(view -> {
            departamentCallback.onUpdate(departament);
        });

        return root;
    }

}

