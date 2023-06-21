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
public class Sarcasmo {

    private static String api_key = "86fHw7TSZvNPfezPPzt0XKwQVWEMc7yYMu9DCyyacVQ";
    private static String host = "https://apis.paralleldots.com/v4/";

    public static String sarcasm(String text) throws Exception {
        if (Sarcasmo.api_key != null) {
            String url = host + "sarcasm";
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("api_key", Sarcasmo.api_key)
                    .addFormDataPart("text", text)
                    .addFormDataPart("lang_code", "en")
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

    public static void sarcasmo(model mol) throws Exception {
        try {
            traductor.traducir(mol);
            String texto = mol.getTextIn();

            if (Sarcasmo.api_key != null) {
                String url = host + "sarcasm";
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("api_key", Sarcasmo.api_key)
                        .addFormDataPart("text", texto)
                        .addFormDataPart("lang_code", "en")
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .addHeader("cache-control", "no-cache")
                        .build();
                Response response = client.newCall(request).execute();
                JsonObject jsonObject = JsonParser.parseString​(response.body().string()).getAsJsonObject();
                if (jsonObject.isJsonObject()) {
                    JsonObject sarcasmo = jsonObject.getAsJsonObject();
                    String sarcastico = sarcasmo.get("Sarcastic").getAsString();
                    String NoSarcastico = sarcasmo.get("Non-Sarcastic").getAsString();
                    System.out.println("Resultado\n");
                    System.out.println(sarcastico + "\n" + NoSarcastico);

                    float sarcastico1 = Float.parseFloat(sarcastico);
                    float noSarcastico1 = Float.parseFloat(NoSarcastico);
                    mol.setSarcastico(sarcastico1);
                    mol.setNoSarcastico(noSarcastico1);
                }
            }

        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        String texto = "ella no te quiere";
        String resultado = "";
        resultado = sarcasm(texto);
        System.out.println("resultado" + resultado);

        model mol = new model();
        mol.setTexto(texto);
        sarcasmo(mol);
    }
}
