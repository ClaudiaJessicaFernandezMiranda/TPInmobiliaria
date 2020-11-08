package com.example.tpinmobiliaria.ui.inmueble;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpinmobiliaria.R;
import com.example.tpinmobiliaria.model.Inmueble;

public class InmuebleDetallesFragment extends Fragment {
    private InmuebleViewModel inmuebleViewModel;
    private EditText etDireccion, etAmbientes, etSuperficie, etLatitud, etLongitud;
    private Spinner spTipo, spUso;
    private Context context;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inmuebles_detalles, container, false);
        cargarVistas(view);

        inmuebleViewModel.getInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                etDireccion.setText(inmueble.getDireccion());
                inmuebleViewModel.setValores(etDireccion, etAmbientes, etSuperficie, etLatitud, etLongitud, spTipo, spUso, inmueble);
            }
        });
        return view;
    }

    private void cargarVistas(View view) {
        context = getActivity();
        inmuebleViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleViewModel.class);
        etDireccion = view.findViewById(R.id.etXFInmuebleDireccion);
        etAmbientes = view.findViewById(R.id.etXFInmuebleAmbientes);
        etSuperficie = view.findViewById(R.id.etXFInmuebleSuperficie);
        etLatitud = view.findViewById(R.id.etXFInmuebleLatitud);
        etLongitud = view.findViewById(R.id.etXFInmuebleLongitud);
        spTipo = view.findViewById(R.id.spXFInmuebleTipo);
        spUso = view.findViewById(R.id.spXFInmuebleUso);

    }

}
