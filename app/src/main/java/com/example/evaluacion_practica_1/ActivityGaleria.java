package com.example.evaluacion_practica_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

public class ActivityGaleria extends AppCompatActivity {

    int PICK_IMAGE = 100;
    Uri imageUri;
    Button btnGaleria, btnSalir;
    GridView gvImagenes;

    List<Uri> listaImagenes = new ArrayList<>();
    GridViewAdapter baseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        gvImagenes = findViewById(R.id.gvImagenes);
        btnGaleria = findViewById(R.id.btnGaleria);
        btnSalir = findViewById(R.id.buttonSalir);


        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityBienvenida.class);
                startActivity(intent);
            }
        });

    }



    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "SELECCIONA LAS IMAGENES"), PICK_IMAGE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ClipData clipData = data.getClipData();

        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            if(clipData == null) {
                imageUri = data.getData();
                listaImagenes.add(imageUri);
            } else {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    listaImagenes.add(clipData.getItemAt(i).getUri());
                }
            }
        }

        baseAdapter = new GridViewAdapter(ActivityGaleria.this, listaImagenes);
        gvImagenes.setAdapter(baseAdapter);

    }
}