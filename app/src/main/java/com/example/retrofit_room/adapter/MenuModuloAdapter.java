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
import com.example.retrofit_room.model.MenuModulo;

import java.util.List;


public class MenuModuloAdapter extends ArrayAdapter<MenuModulo> {

    public MenuModuloAdapter(@NonNull Context context, List<MenuModulo> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.adapter_modulo, parent, false);
        MenuModulo menuChecklist = getItem(position);

        ImageView image = root.findViewById(R.id.image);
        image.setImageResource(menuChecklist.getDrawable());

        TextView text = root.findViewById(R.id.text);
        text.setText(menuChecklist.getTitulo());

        return root;
    }

}

