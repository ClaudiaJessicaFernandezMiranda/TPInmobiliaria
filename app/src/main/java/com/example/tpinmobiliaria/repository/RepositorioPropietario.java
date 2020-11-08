package com.example.tpinmobiliaria.repository;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpinmobiliaria.model.Login;
import com.example.tpinmobiliaria.model.Propietario;
import com.example.tpinmobiliaria.request.ApiClient;
import com.example.tpinmobiliaria.request.ApiInterface;
import com.example.tpinmobiliaria.request.ApiToken;
import com.example.tpinmobiliaria.request.ApiUser;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositorioPropietario {
    private MutableLiveData<String> mensajeMLD = new MutableLiveData<>();
    private MutableLiveData<Propietario> propietarioMLD = new MutableLiveData<>();
    private MutableLiveData<Boolean> verificacionMLD = new MutableLiveData<>();
    private ApiInterface myInterface;
    private Context context;

    public RepositorioPropietario(Application application) {
        myInterface = ApiClient.getApiService();
        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getUser() {
        return propietarioMLD;
    }
    public LiveData<Boolean> getVerificacion() {
        return verificacionMLD;
    }
    public LiveData<String> getBadRequest() {
        return mensajeMLD;
    }

    public void buscarLogeado() {
        myInterface.getUserApi(ApiToken.getToken(context)).enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(@NotNull Call<Propietario> call, @NotNull Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietarioMLD.setValue(response.body());
                    ApiUser.setUser(context, propietarioMLD.getValue().getApellido(), propietarioMLD.getValue().getMail());
                }else {
                    try {
                        mensajeMLD.setValue(Objects.isNull(response.errorBody()) ? response.message() : Objects.requireNonNull(response.errorBody()).string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(@NotNull Call<Propietario> call, @NotNull Throwable t) {
                mensajeMLD.setValue("No se pudo establecer conexión con el servidor");
            }
        });
    }
    public void login(String usuario, String password) {
        Login propietario = new Login(usuario, password);
        myInterface.loginApi2(propietario).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                if(response.isSuccessful()){
                    ApiToken.setToken(context, "Bearer " + response.body());
                    verificacionMLD.setValue(true);
                }else {
                    try {
                        verificacionMLD.setValue(false);
                        mensajeMLD.setValue(Objects.isNull(response.errorBody()) ? response.message() : Objects.requireNonNull(response.errorBody()).string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                verificacionMLD.setValue(false);
                mensajeMLD.setValue("No se pudo establecer conexión con el servidor");
            }
        });
    }
    public void actualizarPropietario(int id, String dni, String apellido, String nombre, String telefono, String mail, String password) {
        Propietario propietario = new Propietario(id, dni, apellido, nombre, telefono, mail, password);
        myInterface.modificarPropietarioApi(ApiToken.getToken(context), propietario).enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(@NotNull Call<Propietario> call, @NotNull Response<Propietario> response) {
                if(response.isSuccessful()){
                    mensajeMLD.setValue("Perfil modificado correctamente");
                }else {
                    try {
                        mensajeMLD.setValue(Objects.isNull(response.errorBody()) ? response.message() : Objects.requireNonNull(response.errorBody()).string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<Propietario> call, @NotNull Throwable t) {
                mensajeMLD.setValue("No se pudo establecer conexión con el servidor");
            }
        });
    }

}
