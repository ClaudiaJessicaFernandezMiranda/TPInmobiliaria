package com.example.tpinmobiliaria.ui.inmueble;

import androidx.fragment.app.Fragment;

public class FragmentsSelector extends Fragment {

    public static Fragment newInstance(int index) {
        Fragment fragment = new Fragment();
        switch (index) {
            case 1: fragment = new FragmentInmueblesHabilitados(); break;
            case 2: fragment = new FragmentInmueblesDeshabilitados(); break;
        }
        return fragment;
    }

}