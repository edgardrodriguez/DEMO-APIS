/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.model;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author edgard
 */
public class Abuso {

    private static String api_key = "86fHw7TSZvNPfezPPzt0XKwQVWEMc7yYMu9DCyyacVQ";
    private static String host = "https://apis.paralleldots.com/v4/";

    public static String abuse(String text) throws Exception {
        if (Abuso.api_key != null) {
            String url = host + "abuse";
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("api_key", Abuso.api_key)
                    .addFormDataPart("text", text)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } else {
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }

    public static void abuso(model mol) throws Exception {
        try {
            traductor.traducir(mol);
            String texto = mol.getTextIn();
            if (Abuso.api_key != null) {
                String url = host + "abuse";
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("api_key", Abuso.api_key)
                        .addFormDataPart("text", texto)
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .addHeader("cache-control", "no-cache")
                        .build();
                Response response = client.newCall(request).execute();
                JsonObject jsonObject = JsonParser.parseString​(response.body().string()).getAsJsonObject();
                if (jsonObject.isJsonObject()) {
                    JsonObject abuso = jsonObject.getAsJsonObject();
                    String abusive = abuso.get("abusive").getAsString();
                    String hate_speech = abuso.get("hate_speech").getAsString();
                    String neither = abuso.get("neither").getAsString();
                    System.out.println("Resultado\n");
                    System.out.println(abusive + "\n" + hate_speech + "\n" + neither + "\n");

                    float abusive1 = Float.parseFloat(abusive);
                    float hate_speech1 = Float.parseFloat(hate_speech);
                    float neither1 = Float.parseFloat(neither);
                    mol.setAbusive(abusive1);
                    mol.setHate_speech(hate_speech1);
                    mol.setNeither(neither1);
                }
            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        String texto = "soy victor la vida es triste, me quiero morir";
        String resultado = "";
        resultado = abuse(texto);
        System.out.println("resultado" + resultado);

        model mol = new model();
        mol.setTexto(texto);
        abuso(mol);
    }
}
