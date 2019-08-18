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

    Button btnEntrar;

    EditText txtResultado;//para reflejar resultados de luego presionar el boton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);

        txtResultado=(EditText) findViewById(R.id.editText);
        btnEntrar=(Button) findViewById(R.id.button);


        //cuando haya clic en el boton luego verificara los parametros que tenemos
        btnEntrar.setOnClickListener(new View.OnClickListener(){

            //Aqui va la validacion de los codigos ingresados, si son correctos ejecuta

            @Override
            public void onClick(View view) {
                solicita("abrir");
                entrar(view);

            }
        });


    }

//boton para cambiar de ventana
    public void entrar(View view) {
        Intent i = new Intent(this, MapaActivity.class );
        startActivity(i);
    }




//Para conectar a la red y mandarle a la ip los requerimientos
    public void solicita(String comando){

        ConnectivityManager connMgr= (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connMgr.getActiveNetworkInfo();

        if(networkInfo!=null&& networkInfo.isConnected()){

            String url = "http://192.168.1.55/";

            new SolicitarDatos().execute(url+comando);

        }else{
            Toast.makeText(CodigoActivity.this,"Nueva conexion detectada",Toast.LENGTH_LONG).show();
        }
    }




//extraer datos y reflejar resultados en el txtResultado de prueba
    private class SolicitarDatos extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... url) {
            return Conexion.getDatos(url[0]);
        }
        protected void onPostExecute(String resultado){

            if(resultado !=null){
                txtResultado.setText(resultado);
            }else{
                Toast.makeText(CodigoActivity.this,"Ocurrio un error",Toast.LENGTH_LONG).show();
            }
        }
    }






}
