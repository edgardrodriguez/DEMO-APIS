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
 * @author edgard nota esta fallando
 */
public class extractor {

    private static String api_key = "86fHw7TSZvNPfezPPzt0XKwQVWEMc7yYMu9DCyyacVQ";
    private static String host = "https://apis.paralleldots.com/v4/";

    public static String phrase_extractor(String text) throws Exception {
        if (extractor.api_key != null) {
            String url = host + "phrase_extractor";
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("api_key", extractor.api_key)
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

    public static void phrase_extractors(model mol) throws Exception {
        try {
            String texto = mol.getTextIn();
            if (extractor.api_key != null) {
                String url = host + "phrase_extractor";
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("api_key", extractor.api_key)
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
                    JsonObject extractor = jsonObject.getAsJsonObject();
                    JsonObject result = extractor.getAsJsonObject("keywords");
                    String clave = result.get("keyword").getAsString();
                    String relevancia = result.get("relevance_score").getAsString();
                    System.out.println("Resultado\n");
                    System.out.println(clave + "\n" + relevancia + "\n");

                    int relev = Integer.valueOf(relevancia);

                    mol.setClave(clave);
                    mol.setScore(relev);

                }
            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }

    }

    public static void main(String[] args) throws Exception {
        String texto = "soy victor la vida es triste, me quiero morir";
        String resultado = "";
        resultado = phrase_extractor(texto);
        System.out.println("resultado" + resultado);
    }
}
