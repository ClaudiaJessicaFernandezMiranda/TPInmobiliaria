package com.example.tpinmobiliaria.ui.contrato;

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
import com.example.tpinmobiliaria.model.Contrato;
import com.example.tpinmobiliaria.model.Inmueble;
import com.example.tpinmobiliaria.ui.inmueble.InmuebleViewModel;

import java.util.List;

public class ContratoAdapter extends ArrayAdapter<Contrato> {

    private Context context;
    private List<Contrato> listaContratos;
    private LayoutInflater layoutInflater;

    public ContratoAdapter(@NonNull Context context, int resource, @NonNull List<Contrato> inmuebles, LayoutInflater layoutInflater) {
        super(context, resource, inmuebles);
        this.context = context;
        this.listaContratos = inmuebles;
        this.layoutInflater = layoutInflater;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = layoutInflater.inflate(R.layout.adapter_contratos_row,parent,false);
        }

        final Contrato contrato = listaContratos.get(position);

        TextView direccion = itemView.findViewById(R.id.tvXADireccion);
        TextView ambientes = itemView.findViewById(R.id.tvXAAmbientes);
        TextView uso = itemView.findViewById(R.id.tvXAUso);

        direccion.setText(String.format("Descripcion: %s", contrato.getDescripcion()));
        ambientes.setText(String.format("Monto: %s", contrato.getMonto()));
        uso.setText(String.format("Alquilado Por: %s %s", contrato.getInquilino().getApellido(), contrato.getInquilino().getNombre()));

        return itemView;

    }
}

