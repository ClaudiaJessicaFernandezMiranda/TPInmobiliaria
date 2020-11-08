package com.example.tpinmobiliaria.ui.inmueble;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.tpinmobiliaria.R;
import com.google.android.material.tabs.TabLayout;

public class InmuebleFragment extends Fragment {
    private InmuebleViewModel inmuebleViewModel;
    private ViewPager vpInmuebles;
    private Context context;
    private TabLayout tlInmuebles;
    private FragmentsAdapter fragmentsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inmueble, container, false);
        cargarVistas(view);
        return view;
    }

    private void cargarVistas(View view) {
        inmuebleViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmuebleViewModel.class);
        context = getActivity();
        fragmentsAdapter = new FragmentsAdapter(context, getActivity().getSupportFragmentManager());
        vpInmuebles = view.findViewById(R.id.vpXIInmueble);
        vpInmuebles.setAdapter(fragmentsAdapter);
        tlInmuebles = view.findViewById(R.id.tlXILogin);
        tlInmuebles.setupWithViewPager(vpInmuebles);
    }

}


