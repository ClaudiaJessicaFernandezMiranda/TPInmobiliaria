package com.example.tpinmobiliaria.ui.inmueble;

import android.app.Application;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpinmobiliaria.model.Inmueble;
import com.example.tpinmobiliaria.model.Propietario;
import com.example.tpinmobiliaria.repository.RepositorioInmuble;
import com.example.tpinmobiliaria.repository.RepositorioPropietario;

import java.util.List;

public class InmuebleViewModel extends AndroidViewModel {
    private RepositorioInmuble repositorioInmuble;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        repositorioInmuble = RepositorioInmuble.getInstance(application);
        repositorioInmuble.cargarInmuebles();
        repositorioInmuble.cargarInmueblesDes();
    }

    public LiveData<List<Inmueble>> getInmuebles() {
        return repositorioInmuble.getInmuebles();
    }

    public LiveData<List<Inmueble>> getInmueblesDes() {
        return repositorioInmuble.getInmueblesDes();
    }

    public LiveData<Inmueble> getInmueble() {
        return repositorioInmuble.getInmueble();
    }

    public LiveData<String> getBadRequest() {
        return repositorioInmuble.getBadRequest();
    }

    public void setInmueble(Inmueble inmueble) {
        repositorioInmuble.setInmueble(inmueble);
    }

    public void setValores(EditText direccion, EditText ambientes, EditText superficie, EditText latitud, EditText longitud, Spinner tipo, Spinner uso, Inmueble inmueble) {
        direccion.setText(inmueble.getDireccion());
        ambientes.setText(inmueble.getAmbientes()+"");
        superficie.setText(inmueble.getSuperficie()+"");
        latitud.setText(inmueble.getLatitud()+"");
        longitud.setText(inmueble.getLongitud()+"");
    }

    public void bajaInmueble(int id) {
        repositorioInmuble.bajaInmueble(id);
    }
}
