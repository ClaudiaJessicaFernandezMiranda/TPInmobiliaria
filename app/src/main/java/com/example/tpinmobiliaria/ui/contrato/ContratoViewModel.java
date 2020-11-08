package com.example.tpinmobiliaria.ui.contrato;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tpinmobiliaria.model.Contrato;
import com.example.tpinmobiliaria.repository.RepositorioContrato;

import java.util.List;

public class ContratoViewModel extends AndroidViewModel {
    private RepositorioContrato repositorioContrato;
    public ContratoViewModel(@NonNull Application application) {
        super(application);
        repositorioContrato = RepositorioContrato.getInstance(application);
    }

    public LiveData<String> getBadRequest() {
        return repositorioContrato.getBadRequest();
    }

    public LiveData<List<Contrato>> getContratos() {
        return repositorioContrato.getContratos();
    }

    public void setIdInmueble(int id) {
        repositorioContrato.setIdInmueble(id);
    }
}
