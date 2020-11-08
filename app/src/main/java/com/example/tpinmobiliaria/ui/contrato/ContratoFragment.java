package com.example.tpinmobiliaria.ui.contrato;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.tpinmobiliaria.R;
import com.example.tpinmobiliaria.model.Contrato;
import com.example.tpinmobiliaria.ui.inmueble.InmuebleAdapter;

import java.util.List;

public class ContratoFragment extends Fragment {
    private ContratoViewModel contratoViewModel;
    private SwipeRefreshLayout srfContratos;
    private ListView lvContratos;
    private ContratoAdapter contratoAdapter;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contratos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cargarVistas(view);

        //Refrescar
        srfContratos.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        contratoViewModel.getContratos();
                        srfContratos.setRefreshing(false);
                    }
                },300);
            }
        });

        contratoViewModel.getContratos().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                contratoAdapter = new ContratoAdapter(context, 0, contratos, getLayoutInflater());
                lvContratos.setAdapter(contratoAdapter);
            }
        });

        contratoViewModel.getBadRequest().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String badRequest) {
                Toast.makeText(context, badRequest, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void cargarVistas(View view) {
        context = getActivity();
        contratoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratoViewModel.class);
        lvContratos = view.findViewById(R.id.lvXIContratos);
        srfContratos = view.findViewById(R.id.srlXIContratos);

    }

}