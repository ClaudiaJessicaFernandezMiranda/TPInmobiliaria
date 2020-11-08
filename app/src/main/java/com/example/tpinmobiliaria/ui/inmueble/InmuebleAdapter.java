package com.example.tpinmobiliaria.ui.inmueble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpinmobiliaria.MainActivity;
import com.example.tpinmobiliaria.R;
import com.example.tpinmobiliaria.model.Inmueble;

import java.util.List;

public class InmuebleAdapter extends ArrayAdapter<Inmueble> {

    private Context context;
    private List<Inmueble> listaInmuebles;
    private LayoutInflater layoutInflater;
    private InmuebleViewModel inmuebleViewModel;

    public InmuebleAdapter(@NonNull Context context, int resource, @NonNull List<Inmueble> inmuebles, LayoutInflater layoutInflater) {
        super(context, resource, inmuebles);
        this.context = context;
        this.listaInmuebles = inmuebles;
        this.layoutInflater = layoutInflater;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = layoutInflater.inflate(R.layout.adapter_inmuebles_row,parent,false);
        }

        final Inmueble inmueble = listaInmuebles.get(position);

        TextView direccion = itemView.findViewById(R.id.tvXADireccion);
        TextView ambientes = itemView.findViewById(R.id.tvXAAmbientes);
        TextView uso = itemView.findViewById(R.id.tvXAUso);
        ToggleButton tbDesHab = itemView.findViewById(R.id.tvXADesHab);

        direccion.setText(String.format("Dirección: %s", inmueble.getDireccion()));
        ambientes.setText(String.format("Ambientes: %s", inmueble.getAmbientes()));
        uso.setText(String.format("Uso: %s", inmueble.getUso()));

        if(inmueble.getEstado() == 1) tbDesHab.setChecked(true);
        else tbDesHab.setChecked(false);

        inmuebleViewModel = new ViewModelProvider((MainActivity) context).get(InmuebleViewModel.class);

        tbDesHab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inmuebleViewModel.bajaInmueble(inmueble.getId());
            }
        });

        return itemView;

    }
}

