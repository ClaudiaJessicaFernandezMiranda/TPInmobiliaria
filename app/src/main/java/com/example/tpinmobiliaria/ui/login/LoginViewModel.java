package com.example.tpinmobiliaria.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tpinmobiliaria.repository.RepositorioPropietario;

public class LoginViewModel extends AndroidViewModel {
    private RepositorioPropietario repositorioPropietario;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repositorioPropietario = new RepositorioPropietario(application);
    }

    public void login(String email, String clave) {
        repositorioPropietario.login(email, clave);
    }

    public LiveData<Boolean> getVerificacion() {
        return repositorioPropietario.getVerificacion();
    }

    public LiveData<String> getBadRequest() {
        return repositorioPropietario.getBadRequest();
    }
}

