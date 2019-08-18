package com.grupo5.urbanaccessresident;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AccesoActivityResident extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso_residente);
    }

    public void inicio(View view) {
        Intent i = new Intent(this, CodigoActivity.class );
        startActivity(i);
    }



}
