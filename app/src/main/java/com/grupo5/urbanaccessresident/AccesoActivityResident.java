package com.grupo5.urbanaccessresident;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AccesoActivityResident extends AppCompatActivity {

    Button btnInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso_residente);

        btnInicio=(Button) findViewById(R.id.button);



        //cuando haya clic en el boton luego verificara los parametros que tenemos
        btnInicio.setOnClickListener(new View.OnClickListener(){

            //Aqui va la validacion de los codigos ingresados, si son correctos ejecuta

            @Override
            public void onClick(View view) {
                inicio(view);

            }
        });

    }

    public void inicio(View view) {
        Intent i = new Intent(this, CodigoActivity.class );
        startActivity(i);
    }



}
