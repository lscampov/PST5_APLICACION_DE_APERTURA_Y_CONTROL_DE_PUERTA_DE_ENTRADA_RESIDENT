package com.grupo5.urbanaccessresident;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AccesoActivityResident extends AppCompatActivity {
    private String serverIP = "remotemysql.com";
    private String port = "3306";
    private String userMySQL = "WAgQ6gLNl1";
    private String pwdMySQL = "oPAE3gP5fa";
    private String database = "WAgQ6gLNl1";
    private String[] datosConexion = null;
    private EditText txt1,txt2;
    private String codigo,user,psw,id_Usuario;

    Button btnInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso_residente);
        txt1 = (EditText) findViewById(R.id.editText);
        txt2 = (EditText) findViewById(R.id.editText2);
        btnInicio = (Button) findViewById(R.id.button);
    }


    public void ingreso(View view){
        String[] resultadoSQL = null;
        try {
            //Toast.makeText(this, "Debe ingresar todos los datos.", Toast.LENGTH_LONG).show();
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            //Toast.makeText(this, "lalaos los datos.", Toast.LENGTH_LONG).show();

            datosConexion = new String[]{
                    serverIP,
                    port,
                    database,
                    userMySQL,
                    pwdMySQL,
                    codigo="1",
                    txt1.getText().toString(),
                    txt2.getText().toString()

            };

            if(txt1.getText().toString().equals("")){
                Toast.makeText(this, "Debe ingresar todos los datos.", Toast.LENGTH_LONG).show();
            }else{
                resultadoSQL = new AsyncQuery().execute(datosConexion).get();
                Toast.makeText(AccesoActivityResident.this,"Conexión Establecida", Toast.LENGTH_LONG).show();
                user=resultadoSQL[0];
                psw=resultadoSQL[1];
                id_Usuario=resultadoSQL[2];
                if(txt2.getText().toString().equals(psw)){
                    Intent i = new Intent(this, CodigoActivity.class );
                    i.putExtra("nombre",user);
                    i.putExtra("contraseña",psw);
                    i.putExtra("id_Usuario",id_Usuario);
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