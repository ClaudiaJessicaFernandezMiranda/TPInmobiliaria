package com.example.tpinmobiliaria.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpinmobiliaria.model.Contrato;
import com.example.tpinmobiliaria.model.Inmueble;
import com.example.tpinmobiliaria.request.ApiClient;
import com.example.tpinmobiliaria.request.ApiInterface;
import com.example.tpinmobiliaria.request.ApiToken;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositorioContrato {
    private static RepositorioContrato repositorioContrato;
    private MutableLiveData<String> mensajeMLD = new MutableLiveData<>();
    private MutableLiveData<List<Contrato>> contratosMLD = new MutableLiveData<>();
    private ApiInterface myInterface;
    private Context context;
    private int idInmueble;

    public static RepositorioContrato getInstance(Application application) {
        if(repositorioContrato == null) repositorioContrato = new RepositorioContrato(application);
        return repositorioContrato;
    }

    private RepositorioContrato(Application application) {
        myInterface = ApiClient.getApiService();
        context = application.getApplicationContext();
    }

    public LiveData<String> getBadRequest() {
        return mensajeMLD;
    }

    public LiveData<List<Contrato>> getContratos() {
        cargarContratos(idInmueble);
        return contratosMLD;
    }

    public void setIdInmueble(int id){
        idInmueble=id;
    }

    public void cargarContratos(int id) {
        myInterface.getContratosApi(ApiToken.getToken(context), id).enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(@NotNull Call<List<Contrato>> call, @NotNull Response<List<Contrato>> response) {
                if (response.isSuccessful()){
                    contratosMLD.setValue(response.body());
                }
            }
            @Override
            public void onFailure(@NotNull Call<List<Contrato>> call, @NotNull Throwable t) {
                mensajeMLD.setValue("No se pudo establecer conexión con el servidor");
            }
        });
    }

}
