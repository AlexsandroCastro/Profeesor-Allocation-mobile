package com.example.retrofit_room.callback;

import com.example.retrofit_room.model.Departament;

public interface DepartamentCallback {

    void onDelete(Departament departament);

    void onUpdate(Departament departament);
}
