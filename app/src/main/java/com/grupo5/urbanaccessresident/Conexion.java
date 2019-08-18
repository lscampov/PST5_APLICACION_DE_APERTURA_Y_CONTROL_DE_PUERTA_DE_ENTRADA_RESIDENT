package com.grupo5.urbanaccessresident;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Conexion {

    public static String getDatos(String urlUsuario){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlUsuario)
                .build();
        try{
            Response response = client.newCall(request).execute();
            return response.body().string();

        }catch (IOException error){
            return null;

        }


    }
}
