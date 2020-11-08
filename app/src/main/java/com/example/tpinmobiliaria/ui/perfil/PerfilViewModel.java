package com.example.tpinmobiliaria.ui.perfil;

import android.app.Application;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tpinmobiliaria.model.Propietario;
import com.example.tpinmobiliaria.repository.RepositorioPropietario;

public class PerfilViewModel extends AndroidViewModel {
    private RepositorioPropietario repositorioPropietario;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        repositorioPropietario = new RepositorioPropietario(application);
        repositorioPropietario.buscarLogeado();
    }

    public LiveData<Propietario> getUser() {
        return repositorioPropietario.getUser();
    }

    public LiveData<String> getBadRequest() {
        return repositorioPropietario.getBadRequest();
    }

    public void habilitarForm(EditText dni, EditText apellido, EditText nombre, EditText telefono, EditText mail, EditText password, Button editar, Button aplicar) {
        dni.setEnabled(true);
        apellido.setEnabled(true);
        nombre.setEnabled(true);
        telefono.setEnabled(true);
        mail.setEnabled(true);
        password.setEnabled(true);
        aplicar.setVisibility(View.VISIBLE);
        editar.setVisibility(View.GONE);
        aplicar.setEnabled(true);
    }

    public void actualizarPropietario(int id, String dni, String apellido, String nombre, String telefono, String mail, String password) {
        repositorioPropietario.actualizarPropietario(id, dni, apellido, nombre, telefono, mail, password);
    }

}
