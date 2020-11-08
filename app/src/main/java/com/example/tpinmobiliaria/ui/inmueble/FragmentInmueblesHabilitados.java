package com.example.tpinmobiliaria.ui.inmueble;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.tpinmobiliaria.R;
import com.example.tpinmobiliaria.model.Inmueble;

import java.util.List;

public class FragmentInmueblesHabilitados extends Fragment {
    private InmuebleViewModel inmuebleViewModel;
    private SwipeRefreshLayout srfInmuebles;
    private ListView lvInmuebles;
    private NavController navController;
    private InmuebleAdapter inmuebleAdapter;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inmuebles_habilitados, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cargarVistas(view);

        //Refrescar
        srfInmuebles.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        inmuebleViewModel.getInmuebles();
                        srfInmuebles.setRefreshing(false);
                    }
                },300);
            }
        });

        inmuebleViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                inmuebleAdapter = new InmuebleAdapter(context, 0, inmuebles, getLayoutInflater());
                lvInmuebles.setAdapter(inmuebleAdapter);
            }
        });

        lvInmuebles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                inmuebleViewModel.setInmueble((Inmueble) adapterView.getAdapter().getItem(position));
                navController.navigate(R.id.action_nav_inmueble_to_inmuebleDetallesFragment);
            }
        });

        inmuebleViewModel.getBadRequest().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String badRequest) {
                Toast.makeText(context, badRequest, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void cargarVistas(View view) {
        context = getActivity();
        navController = Navigation.findNavController(view);
        inmuebleViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleViewModel.class);
        lvInmuebles = view.findViewById(R.id.lvXIInmuebles);
        srfInmuebles = view.findViewById(R.id.srlXIInmueble);

    }

}