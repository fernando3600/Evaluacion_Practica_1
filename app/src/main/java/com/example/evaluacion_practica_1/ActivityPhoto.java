package com.example.evaluacion_practica_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ActivityPhoto extends AppCompatActivity implements View.OnClickListener{

    Button takePicture, savePicture, exit;
    ImageView imgView;
    Bitmap bitmap;

    private static final int REQUEST_PERMISSION_CAMERA = 100;
    private static final int TAKE_PICTURE = 101;
    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        takePicture = findViewById(R.id.btnTomarFoto);
        savePicture = findViewById(R.id.btnGuardar);
        exit = findViewById(R.id.btnSalir);
        imgView = findViewById(R.id.imvFoto);

        takePicture.setOnClickListener(this);
        savePicture.setOnClickListener(this);
        exit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.btnTomarFoto){
           checkPermissionCamera();
        }else if (id == R.id.btnGuardar){
            checkPermissionStorage();
            Toast.makeText(this, "guarde foto", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.btnSalir){
            Intent salirIntent = new Intent(view.getContext(), ActivityBienvenida.class);
            startActivity(salirIntent);
        }

    }

    private void checkPermissionStorage() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_PERMISSION_WRITE_STORAGE);
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                 if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                     saveImage();
                 }else{
                     ActivityCompat.requestPermissions(this,
                             new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                             REQUEST_PERMISSION_WRITE_STORAGE);
                 }
            }else{
                saveImage();
            }
        }else{
            saveImage();
        }
    }

    private void saveImage() {
        OutputStream outputStream = null;
        File file = null;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            ContentResolver resolver = getContentResolver();
            ContentValues values = new ContentValues();

            String fileName = System.currentTimeMillis() + "image_example";

            values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/ProjectMovil");
            values.put(MediaStore.Images.Media.IS_PENDING, 1);

            Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
            Uri imagesUri = resolver.insert(collection, values);

            try {
                outputStream = resolver.openOutputStream(imagesUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            values.clear();
            values.put(MediaStore.Images.Media.IS_PENDING, 0);
            resolver.update(imagesUri, values, null, null);
        }else{
            String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

            String fileName = System.currentTimeMillis() + ".jpg";

            file = new File(imageDir, fileName);

            try {
                outputStream = new FileOutputStream(file);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        Boolean saved = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        if(saved){
            Toast.makeText(this, "Se creo correctamente", Toast.LENGTH_SHORT).show();
        }
        if(outputStream != null){
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(file != null){
            MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null, null);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TAKE_PICTURE){
            if(resultCode == Activity.RESULT_OK && data!=null){
                bitmap = (Bitmap) data.getExtras().get("data");
                imgView.setImageBitmap(bitmap);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISSION_CAMERA){
            if(permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                takePicture();

            }
        }else if (requestCode == REQUEST_PERMISSION_WRITE_STORAGE){
            if(permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                saveImage();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void checkPermissionCamera() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            takePicture();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
        }
    }

    private void takePicture() {
        Intent intentPic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentPic.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intentPic, TAKE_PICTURE);
        }

    }
}