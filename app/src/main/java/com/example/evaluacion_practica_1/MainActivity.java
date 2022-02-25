package com.example.evaluacion_practica_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btnIngresar);
        Button btnsalir = findViewById(R.id.btnCancelar);

        EditText user = findViewById(R.id.EdTUsuario);
        EditText pass = findViewById(R.id.EdTPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( user.getText().toString().equals("DM1p22A")){
                    Toast.makeText(MainActivity.this, "El usuario es correcto", Toast.LENGTH_SHORT).show();
                }else{
                    showAlertDialog();
                }

            }
        });

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void showAlertDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Error de Autentificacion: ")
                .setMessage("el Usuario es incorrecto")
                .setPositiveButton("aceptar", null).show();
    }
}