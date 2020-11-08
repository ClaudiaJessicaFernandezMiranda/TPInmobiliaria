package com.example.tpinmobiliaria.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpinmobiliaria.MainActivity;
import com.example.tpinmobiliaria.R;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private EditText etEmail, etClave;
    private Button btnAcceder;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cargarVistas();

        loginViewModel.getVerificacion().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean acceso) {
                if(acceso) {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    finish();
                }
            }
        });

        loginViewModel.getBadRequest().observe(LoginActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String badRequest) {
                Toast.makeText(LoginActivity.this, badRequest, Toast.LENGTH_SHORT).show();
            }
        });

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.login(etEmail.getText().toString(), etClave.getText().toString());
            }
        });
    }

    private void cargarVistas() {
        loginViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        context = LoginActivity.this;
        etEmail = findViewById(R.id.etXLEmail);
        etClave = findViewById(R.id.etXLContraseña);
        btnAcceder = findViewById(R.id.btnXLAcceder);

    }

}
