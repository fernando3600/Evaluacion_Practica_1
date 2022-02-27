package com.example.evaluacion_practica_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
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
                if(user.getText().toString().equals("DM1p22A") && pass.getText().toString().equals("Fernando")){
                    Intent intent = new Intent(view.getContext(), ActivityBienvenida.class);
                    startActivity(intent);
                }else if (!pass.getText().toString().equals("Fernando") && !user.getText().toString().equals("DM1p22A")){

                    showAlertDialog("el Usuario no existe, por favor verifica");
                }else{
                    showAlertDialog("el usuario o la contraseña son incorrectas, por favor verifica");
                }

            }
        });

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Saliendo de la App", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void showAlertDialog(String mess){
        new AlertDialog.Builder(this)
                .setTitle("Error de Autentificacion: ")
                .setMessage(mess)
                .setPositiveButton("aceptar", null).show();
    }
}