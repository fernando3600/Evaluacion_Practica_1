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

        EditText usuario = findViewById(R.id.EdTUsuario);
        EditText contra = findViewById(R.id.EdTPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usuario.getText().toString().equals("dm1p22A") && contra.getText().toString().equals("Gabriel")){
                    Intent intent = new Intent(view.getContext(), ActivityBienvenida.class);
                    startActivity(intent);
                }else{
                    showAlertDialog("el usuario o contraseña  incorrectas");
                    //showAlertDialog(usuario.getText().toString() +" "+contra.getText().toString());

                }

            }
        });

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Saliendo ", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void showAlertDialog(String mess){
        new AlertDialog.Builder(this)
                .setMessage(mess)
                .setPositiveButton("aceptar", null).show();
    }
}