package com.example.tpinmobiliaria.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpinmobiliaria.model.Inmueble;
import com.example.tpinmobiliaria.request.ApiClient;
import com.example.tpinmobiliaria.request.ApiInterface;
import com.example.tpinmobiliaria.request.ApiToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositorioInmuble {
    private static RepositorioInmuble repositorioInmuble;
    private MutableLiveData<String> mensajeMLD = new MutableLiveData<>();
    private MutableLiveData<List<Inmueble>> inmueblesMLD = new MutableLiveData<>();
    private MutableLiveData<List<Inmueble>> inmueblesDesMLD = new MutableLiveData<>();
    private MutableLiveData<Inmueble> inmuebleMLD = new MutableLiveData<>();
    private ApiInterface myInterface;
    private Context context;

    public static RepositorioInmuble getInstance(Application application) {
        if(repositorioInmuble == null) repositorioInmuble = new RepositorioInmuble(application);
        return repositorioInmuble;
    }

    private RepositorioInmuble(Application application) {
        myInterface = ApiClient.getApiService();
        context = application.getApplicationContext();
    }

    public LiveData<String> getBadRequest() {
        return mensajeMLD;
    }
    public LiveData<List<Inmueble>> getInmuebles() {
        return inmueblesMLD;
    }

    public LiveData<List<Inmueble>> getInmueblesDes() {
        return inmueblesDesMLD;
    }

    public LiveData<Inmueble> getInmueble() {
        return inmuebleMLD;
    }

    public void cargarInmuebles() {
        myInterface.getInmueblesApi(ApiToken.getToken(context)).enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(@NotNull Call<List<Inmueble>> call, @NotNull Response<List<Inmueble>> response) {
                if (response.isSuccessful()){
                    inmueblesMLD.setValue(response.body());
                }else {
                    try {
                        mensajeMLD.setValue(Objects.isNull(response.errorBody()) ? response.message() : Objects.requireNonNull(response.errorBody()).string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(@NotNull Call<List<Inmueble>> call, @NotNull Throwable t) {
                mensajeMLD.setValue("No se pudo establecer conexión con el servidor");
            }
        });
    }

    public void cargarInmueblesDes() {
        myInterface.getInmueblesDesApi(ApiToken.getToken(context)).enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(@NotNull Call<List<Inmueble>> call, @NotNull Response<List<Inmueble>> response) {
                if (response.isSuccessful()){
                    inmueblesDesMLD.setValue(response.body());
                }else {
                    try {
                        mensajeMLD.setValue(Objects.isNull(response.errorBody()) ? response.message() : Objects.requireNonNull(response.errorBody()).string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(@NotNull Call<List<Inmueble>> call, @NotNull Throwable t) {
                mensajeMLD.setValue("No se pudo establecer conexión con el servidor");
            }
        });
    }

    public void setInmueble(Inmueble inmueble) {
        inmuebleMLD.setValue(inmueble);
    }

    public void bajaInmueble(int id) {
        myInterface.bajaLogicaInmuebleApi(ApiToken.getToken(context), id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NotNull Call<Boolean> call, @NotNull Response<Boolean> response) {
                if (response.isSuccessful()) {
                    cargarInmuebles();
                    cargarInmueblesDes();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Boolean> call, @NotNull Throwable t) {

            }
        });
    }
}
