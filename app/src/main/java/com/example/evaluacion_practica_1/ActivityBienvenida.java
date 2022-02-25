package com.example.evaluacion_practica_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ActivityBienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        ImageButton btnYo = findViewById(R.id.imageButtonYo);
        ImageButton btnRadio = findViewById(R.id.imageButtonRadio);
        ImageButton btnTv = findViewById(R.id.imageButtonTv);
        ImageButton btnCamara = findViewById(R.id.imageButtonCamara);
        ImageButton btnLinterna = findViewById(R.id.imageButtonLinterna);
        ImageButton btnPestana = findViewById(R.id.imageButtonPestana);
        Button btnSalir = findViewById(R.id.buttonSalir);

        btnYo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("Fernando Antonio Gonzalez\n" +
                        "ITToluca" +
                        "Profesora Rocio Elizabeth Pulido ALba\n" +
                        "Periodo 2022A", "Creditos");
            }
        });

        btnRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnLinterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnPestana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }
    public void showAlertDialog(String mess, String title){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(mess)
                .setPositiveButton("aceptar", null).show();
    }
}