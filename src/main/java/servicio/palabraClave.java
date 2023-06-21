/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author edgard
 */
public class palabraClave {
    
    private static String api_key = "86fHw7TSZvNPfezPPzt0XKwQVWEMc7yYMu9DCyyacVQ";
    private static String host = "https://apis.paralleldots.com/v4/";

     public static String keywords(String text) throws Exception {
        if(palabraClave.api_key!=null){
            String url = host + "keywords";
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("api_key", palabraClave.api_key)
            .addFormDataPart("text", text)
            .build();
            Request request = new Request.Builder()
              .url(url)
              .post(requestBody)
              .addHeader("cache-control", "no-cache")
              .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }else{
            return "{ \"Error\": \"API key does not exist\" }";
        }		
    }
     public static void main(String[] args) throws Exception {
        String texto = "soy victor la vida es triste, me quiero morir";
        String resultado = "";
        resultado = keywords(texto);
        System.out.println("resultado" + resultado);
    }
}
