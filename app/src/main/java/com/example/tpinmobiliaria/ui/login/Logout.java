package com.example.tpinmobiliaria.ui.login;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tpinmobiliaria.R;
import com.example.tpinmobiliaria.request.ApiToken;
import com.example.tpinmobiliaria.request.ApiUser;

public class Logout extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        logout(view);

        return view;
    }

    private void logout(final View view) {

        new AlertDialog.Builder(getContext())
                .setIcon(R.drawable.ic_baseline_exit_to_app_24)
                .setTitle("Cerrar Aplicacion")
                .setCancelable(false)
                .setMessage("Estas seguro de esto?")
                .setPositiveButton("Si, Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ApiToken.limpiar(getContext());
                        ApiUser.limpiar(getContext());
                        System.exit(0);
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Navigation.findNavController(view).navigate(R.id.nav_home, null);
                    }
                })
                .show();
    }

}