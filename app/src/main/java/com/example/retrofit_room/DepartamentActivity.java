package com.example.retrofit_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retrofit_room.adapter.DepartamentAdapter;
import com.example.retrofit_room.callback.DepartamentCallback;
import com.example.retrofit_room.model.Departament;
import com.example.retrofit_room.model.DepartamentDTO;
import com.example.retrofit_room.service.DepartamentService;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartamentActivity extends AppCompatActivity {

    private TextInputLayout tilDepartamento;
    private Button btnSalvar, btnAtualizar, btnDelete;
    private DepartamentService service;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

         service = RetrofitConfig
                .newInstance()
                .departamentService();


//        btnSalvar.setOnClickListener(view -> {
//
//            if(!Objects.requireNonNull(tilDepartamento.getEditText()).getText().toString().isEmpty()){
//                DepartamentDTO dto = new DepartamentDTO();
//                dto.setName(tilDepartamento.getEditText().getText().toString());
//                service.createDepatament(dto).enqueue(new Callback<Departament>() {
//                    @Override
//                    public void onResponse(Call<Departament> call, Response<Departament> response) {
//                        Log.e(">>>>>", "onResponse: " + response.code());
//
//                        if(response.isSuccessful()){
//                            Log.e(">>>>>", " SUCESSO " );
//                        }else{
//                            Log.e(">>>>>", " FUDEUUUUU " );
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Departament> call, Throwable t) {
//
//                    }
//                });
//            }else{
//                Toast.makeText(DepartamentActivity.this, "Departamento não pode ser vazio", Toast.LENGTH_LONG).show();
//            }
//
//
//        });
//
//        btnAtualizar.setOnClickListener(view -> {
//
//        });
//
//        btnDelete.setOnClickListener(view -> {
//
//            if(!Objects.requireNonNull(tilDepartamento.getEditText()).getText().toString().isEmpty()) {
//
//                long id = Long.parseLong(tilDepartamento.getEditText().getText().toString());
//
//                service.deleteDepartmentById(id).enqueue(new Callback<Departament>() {
//                    @Override
//                    public void onResponse(Call<Departament> call, Response<Departament> response) {
//                        Log.e(">>>>>", "onResponse: " + response.code());
//
//                        if (response.isSuccessful()) {
//                            Log.e(">>>>>", " SUCESSO deletou  ");
//
//                        } else {
//                            Log.e(">>>>>", " FUDEUUUUU ");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Departament> call, Throwable t) {
//
//                    }
//                });
//
//            }else{
//                Toast.makeText(DepartamentActivity.this, "Departamento não pode ser vazio", Toast.LENGTH_LONG).show();
//            }
//
//        });
//









    }

    @Override
    protected void onResume() {
        super.onResume();

        listAll();
    }

    private void setAdapter(List<Departament> departaments){

        DepartamentAdapter departamentAdapter = new DepartamentAdapter(DepartamentActivity.this, new DepartamentCallback() {
            @Override
            public void onDelete(Departament departament) {
                Log.e("ID", "Longgg idd: " + departament.getId() );
                AlertDialog dialog = new AlertDialog.Builder(DepartamentActivity.this).create();
                dialog.setTitle("Atenção!!!");
                dialog.setMessage("Deseja excluir o departamento " + departament.getName() + " ?");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Sim", (dialogInterface, i) -> {
                    excluirDepartament(departament.getId());
                });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Não", (dialogInterface, i) -> {

                });
                dialog.show();
            }

            @Override
            public void onUpdate(Departament departament) {
                AlertDialog.Builder atualizarDepartment = new AlertDialog.Builder(DepartamentActivity.this);
                atualizarDepartment.setTitle("Atualizar Departament!!!");
                atualizarDepartment.setMessage("Digite o nome do Department ?");

                EditText editTextDepartament = new EditText(DepartamentActivity.this);
                LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                editTextDepartament.setLayoutParams(linearLayout);
                atualizarDepartment.setView(editTextDepartament);
                atualizarDepartment.setPositiveButton("Salvar", (dialogInterface, i1) -> {

                    if(!editTextDepartament.getText().toString().isEmpty()) {

                        updateDepartament(departament, editTextDepartament.getText().toString());
                    }else{
                        Toast.makeText(DepartamentActivity.this, "Digite um Departament!!! " + editTextDepartament.getText().toString()
                                , Toast.LENGTH_LONG).show();
                    }

                });
                atualizarDepartment.setNegativeButton("Não", (dialogInterface, i12) -> {

                });
                atualizarDepartment.create().show();



            }
        }, departaments);
        departamentAdapter.setDropDownViewResource(0);
        list.setAdapter(departamentAdapter);

        list.setOnItemClickListener((adapterView, view, i, l) -> {

            Departament departament = departamentAdapter.getItem(i);
            Toast.makeText(DepartamentActivity.this, " Departament "
                    + departament.getName(), Toast.LENGTH_LONG).show();



        });

    }

    private void listAll(){
        service.getAllDepartaments().enqueue(new Callback<List<Departament>>() {
            @Override
            public void onResponse(Call<List<Departament>> call, Response<List<Departament>> response) {
                List<Departament> lista = response.body();

                for (Departament item : lista){
                    Log.i(">>>>", item.getName());
                }

                setAdapter(lista);
            }

            @Override
            public void onFailure(Call<List<Departament>> call, Throwable t) {

            }
        });
    }

    private void createdDepartament(String nome){

                DepartamentDTO dto = new DepartamentDTO();
                dto.setName(nome);
                service.createDepatament(dto).enqueue(new Callback<Departament>() {
                    @Override
                    public void onResponse(Call<Departament> call, Response<Departament> response) {
                        Log.e(">>>>>", "onResponse: " + response.code());

                        if(response.isSuccessful()){
                            Log.e(">>>>>", " SUCESSO " );
                            Toast.makeText(DepartamentActivity.this, "Cadastrado com sucesso!!", Toast.LENGTH_LONG).show();
                            listAll();
                        }else{
                            Log.e(">>>>>", " FUDEUUUUU " );
                        }
                    }

                    @Override
                    public void onFailure(Call<Departament> call, Throwable t) {

                    }

    });
    }

    private void updateDepartament(Departament departament, String edit){

        DepartamentDTO dto = new DepartamentDTO();
                dto.setName(edit);
                service.alterar(departament.getId(), dto).enqueue(new Callback<Departament>() {
                    @Override
                    public void onResponse(Call<Departament> call, Response<Departament> response) {
                        Log.e(">>>>>", "onResponse: " + response.code());

                        if(response.isSuccessful()){
                            Toast.makeText(DepartamentActivity.this, " Departament atualizado com sucesso!!! "
                                    , Toast.LENGTH_LONG).show();
                            listAll();
                        }else{
                            Toast.makeText(DepartamentActivity.this, "Não foi possivel atualizar Departament!!! "
                                    , Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Departament> call, Throwable t) {

                    }
                });



    }

    private void excluirDepartament(Long id){

                service.deleteDepartmentById(id).enqueue(new Callback<Departament>() {
                    @Override
                    public void onResponse(Call<Departament> call, Response<Departament> response) {
                        Log.e(">>>>>", "onResponse: " + response.code());

                        if (response.isSuccessful()) {
                            Log.e(">>>>>", " SUCESSO deletou  ");
                            Toast.makeText(DepartamentActivity.this, "Deletado com sucesso!!", Toast.LENGTH_LONG).show();
                            listAll();
                        } else {
                            Log.e(">>>>>", " FUDEUUUUU ");
                        }
                    }

                    @Override
                    public void onFailure(Call<Departament> call, Throwable t) {

                    }
                });
    }

    private void initView (){
//        tilDepartamento = findViewById(R.id.til_departamento);
//        btnSalvar = findViewById(R.id.btn_salvar);
//        btnAtualizar = findViewById(R.id.btn_atualizar);
//        btnDelete = findViewById(R.id.btn_delete);
        list = findViewById(R.id.listview_departament);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_add:
                AlertDialog.Builder createDepartament = new AlertDialog.Builder(DepartamentActivity.this);
                createDepartament.setTitle("Criar Departament!!!");
                createDepartament.setMessage("Digite o nome do Department?");

                EditText editTextDepartament = new EditText(DepartamentActivity.this);
                LinearLayout.LayoutParams linearLayout = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                editTextDepartament.setLayoutParams(linearLayout);
                createDepartament.setView(editTextDepartament);
                createDepartament.setPositiveButton("Salvar", (dialogInterface, i1) -> {

                    if(!editTextDepartament.getText().toString().isEmpty()) {

                        createdDepartament(editTextDepartament.getText().toString());
                    }else{
                        Toast.makeText(DepartamentActivity.this, "Digite um Departament!!! " + editTextDepartament.getText().toString()
                                , Toast.LENGTH_LONG).show();
                    }

                });
                createDepartament.setNegativeButton("Não", (dialogInterface, i12) -> {

                });
                createDepartament.create().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}