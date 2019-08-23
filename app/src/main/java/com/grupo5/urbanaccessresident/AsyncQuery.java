package com.grupo5.urbanaccessresident;

import android.os.AsyncTask;
import android.os.Bundle;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.util.Random;

public class AsyncQuery extends AsyncTask<String[],Void,String[]> {

    private Connection conexionMySQL=null;
    private Statement st = null;
    private ResultSet rs = null;

    @Override
    protected String[] doInBackground(String[]... datos) {
        String nombre1;
        String nombre2;
        String apellido1;
        String apellido2;
        String cedula;
        String user;
        String pass;
        String mz,villa,longitud,latitud;
        String email;
        String perfil;
        String codigo=datos[0][5];
        String resultadoSQL = "";
        String[] totalResultadoSQL = null;
        int numColumnas = 0;
        int numFilas = 0;
        String SERVIDOR = datos[0][0];
        String PUERTO = datos[0][1];
        String BD = datos[0][2];
        String USUARIO = datos[0][3];
        String PASSWORD = datos[0][4];
        String clave_acceso;

        try{
            conexionMySQL = DriverManager.getConnection("jdbc:mysql://" + SERVIDOR + ":" + PUERTO + "/" + BD,
                    USUARIO,PASSWORD);

            st = conexionMySQL.createStatement();

            if(codigo.equals("1")) {
                user= datos[0][6];
                pass= datos[0][7];
                rs = st.executeQuery("SELECT User,Password,Id_Usuario FROM Usuario WHERE User='"+user+"';");
                rs.last();
                numFilas = rs.getRow();
                if (numFilas == 0) {
                    resultadoSQL = "No se ha producido ningún resultado. Revise la consulta realizada.\n";
                } else {
                    rs.beforeFirst();
                    while (rs.next()) {
                        numColumnas = rs.getMetaData().getColumnCount();
                        totalResultadoSQL=new String[3];
                        for (int i = 1; i <= numColumnas; i++) {
                            totalResultadoSQL[i - 1] = rs.getString(i);
                        }
                    }
                }

            }if(codigo.equals("2")){
                clave_acceso=datos[0][6];
                    rs = st.executeQuery("SELECT Id_Usuario,clave_acceso FROM Usuario WHERE clave_acceso='"+clave_acceso+"';");
                    rs.last();
                    numFilas = rs.getRow();
                    if(numFilas == 0)
                    {
                        resultadoSQL = "No se ha producido ningún resultado. Revise la consulta realizada.\n";
                    }else
                    {
                        rs.beforeFirst();
                        while (rs.next())
                        {
                            numColumnas = rs.getMetaData().getColumnCount();
                            totalResultadoSQL = new String[2];
                            for(int i=1;i<=numColumnas;i++){
                                totalResultadoSQL[i-1]= rs.getString(i);
                            }
                        }
                        String q = "INSERT INTO Entrada (Id_Usuario,Fecha,Id_Equipo) VALUES " +
                                "('" + totalResultadoSQL[0] + "','2019-08-21','1')";
                        Log.d("Query: ",q);
                        st.executeUpdate(q);
                        String vv;
                    }

            }


        }catch(SQLException ex)
        {
            Log.d("Error de conexión", ex.getMessage());
        }
        finally
        {
            try
            {
                if(rs != null)
                {
                    rs.close();
                }
                st.close();
                conexionMySQL.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return totalResultadoSQL;
    }

}


