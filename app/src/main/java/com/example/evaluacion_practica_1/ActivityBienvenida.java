package com.example.evaluacion_practica_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ActivityBienvenida extends AppCompatActivity {

    boolean turn = false;
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
                Intent intentVideo = new Intent(view.getContext(), ActivityVideo.class);
                startActivity(intentVideo);
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
                Intent intentPhoto = new Intent(view.getContext(), ActivityPhoto.class);
                startActivity(intentPhoto);
            }
        });

        btnLinterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FlashlightActivity.class);
                startActivity(intent);
            }
        });

        btnPestana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityTabLay.class);
                startActivity(intent);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityBienvenida.this, "Saliendo de la app", Toast.LENGTH_SHORT).show();
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