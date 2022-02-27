package com.example.evaluacion_practica_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivityTabLay extends AppCompatActivity {

    Button btnGps, btnSalir;
    TextView txtGps1, txtGps2, txtGps3, txtGps4, txtGps5;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layaout);

        btnGps = findViewById(R.id.btnGPS);
        btnSalir = findViewById(R.id.btnSalir);
        txtGps1 = findViewById(R.id.txtGps1);
        txtGps2 = findViewById(R.id.txtGps2);
        txtGps3 = findViewById(R.id.txtGps3);
        txtGps4 = findViewById(R.id.txtGps4);
        txtGps5 = findViewById(R.id.txtGps5);

        txtGps1.setText(Html.fromHtml("<font color='#6200EE'><b>Latitud: </b><br></font>"));
        txtGps2.setText(Html.fromHtml("<font color='#6200EE'><b>Longitud: </b><br></font>"));
        txtGps3.setText(Html.fromHtml("<font color='#6200EE'><b>País: </b><br></font>"));
        txtGps4.setText(Html.fromHtml("<font color='#6200EE'><b>Localidad: </b><br></font>"));
        txtGps5.setText(Html.fromHtml("<font color='#6200EE'><b>Dirección: </b><br></font>"));

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(ActivityTabLay.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(ActivityTabLay.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
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

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Toast.makeText(ActivityTabLay.this, "Entramos", Toast.LENGTH_SHORT).show();
                Location location = task.getResult();
                if(location != null){
                    Geocoder geocoder = new Geocoder(ActivityTabLay.this, Locale.getDefault());

                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        txtGps1.setText(Html.fromHtml("<font color='#6200EE'><b>Latitude: </b><br></font>"+addresses.get(0).getLatitude()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{

                }
            }
        });
    }
}