package com.grupo5.urbanaccessresident;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//En esta clase se define la apertura de la puerta, si los codigos son correctos, manda a ejecutar al NodeMCU

public class CodigoActivity extends AppCompatActivity {
    private String serverIP = "remotemysql.com";
    private String port = "3306";
    private String userMySQL = "WAgQ6gLNl1";
    private String pwdMySQL = "oPAE3gP5fa";
    private String database = "WAgQ6gLNl1";
    private String[] datosConexion = null;
    private EditText txt1;
    private String codigo,user,psw;
    Button btnEntrar;

    EditText txtResultado;//para reflejar resultados de luego presionar el boton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);
        txt1 = (EditText) findViewById(R.id.editText);
        btnEntrar = (Button) findViewById(R.id.button);


    }


    public void ingreso(View view){
        String[] resultadoSQL = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();

            datosConexion = new String[]{
                    serverIP,
                    port,
                    database,
                    userMySQL,
                    pwdMySQL,
                    codigo="2",
                    txt1.getText().toString()

            };

            if(txt1.getText().toString().equals("")){
                Toast.makeText(this, "Debe ingresar todos los datos.", Toast.LENGTH_LONG).show();
            }else{
                resultadoSQL = new AsyncQuery().execute(datosConexion).get();
                Toast.makeText(CodigoActivity.this,"Conexión Establecida", Toast.LENGTH_LONG).show();
                psw=resultadoSQL[0];
                if(txt1.getText().toString().equals(psw)){
                    Intent i = new Intent(this, MapaActivity.class );
                    startActivity(i);
                    Toast.makeText(this, "Ingreso Exitoso.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Se ha producido un error.", Toast.LENGTH_LONG).show();
                }
                }



        }catch(Exception ex)
        {
            Toast.makeText(this, "Error: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



}
