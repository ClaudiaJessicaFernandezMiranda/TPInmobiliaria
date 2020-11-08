package com.example.tpinmobiliaria.ui.perfil;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpinmobiliaria.R;
import com.example.tpinmobiliaria.model.Propietario;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private EditText etDni, etApellido, etNombre, etTelefono, etMail, etPassword;
    private Button btnEditar, btnAplicar;
    private Context context;
    private int id;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        cargarVistas(view);


        perfilViewModel.getBadRequest().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String badRequest) {
                Toast.makeText(context, badRequest, Toast.LENGTH_SHORT).show();
            }
        });

        perfilViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                fijarDatos(propietario);

            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilViewModel.habilitarForm(etDni, etApellido, etNombre, etTelefono, etMail, etPassword, btnEditar, btnAplicar);
            }
        });

        btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilViewModel.actualizarPropietario(
                        id,
                        etDni.getText().toString(),
                        etApellido.getText().toString(),
                        etNombre.getText().toString(),
                        etTelefono.getText().toString(),
                        etMail.getText().toString(),
                        etPassword.getText().toString());
            }
        });

        return view;
    }

    private void cargarVistas(View view) {
        context = getActivity();
        perfilViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PerfilViewModel.class);
        etDni = view.findViewById(R.id.etXPDni);
        etApellido = view.findViewById(R.id.etXPApellido);
        etNombre = view.findViewById(R.id.etXPNnombre);
        etTelefono = view.findViewById(R.id.etXPTelefono);
        etMail = view.findViewById(R.id.etXPEmail);
        etPassword = view.findViewById(R.id.etXPPassword);
        btnEditar = view.findViewById(R.id.btnXPEditar);
        btnAplicar = view.findViewById(R.id.btnXPAplicar);
    }

    public void fijarDatos(Propietario propietario){
        etDni.setText(propietario.getDni());
        etApellido.setText(propietario.getApellido());
        etNombre.setText(propietario.getNombre());
        etTelefono.setText(propietario.getTelefono());
        etMail.setText(propietario.getMail());
        etPassword.setText(propietario.getPassword());
        id = propietario.getId();
    }

}


