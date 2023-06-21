/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.util.concurrent.TimeUnit;
import modelo.model;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author edgard
 * error
 */
public class intencion {

    private static String api_key = "86fHw7TSZvNPfezPPzt0XKwQVWEMc7yYMu9DCyyacVQ";
    private static String host = "https://apis.paralleldots.com/v4/";

    public static String intent(String text) throws Exception {
        if (intencion.api_key != null) {
            String url = host + "intent";
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("api_key", intencion.api_key)
                    .addFormDataPart("text", text)
                    .build();
            Request request = new Request.Builder()
                    .url(url + "?api_key=" + intencion.api_key + "&text=" + text)
                    .post(requestBody)
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } else {
            return "{ \"Error\": \"API key does not exist\" }";
        }
    }

    public static void intento(model mol) throws Exception {
        try {
            traductor.traducir(mol);
            String texto = mol.getTextIn();
            if (intencion.api_key != null) {
                String url = host + "intent";
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("api_key", intencion.api_key)
                        .addFormDataPart("text", texto)
                        .build();
                Request request = new Request.Builder()
                        .url(url + "?api_key=" + intencion.api_key + "&text=" + texto)
                        .post(requestBody)
                        .addHeader("cache-control", "no-cache")
                        .build();
                Response response = client.newCall(request).execute();
                JsonObject jsonObject = JsonParser.parseString​(response.body().string()).getAsJsonObject();
                if (jsonObject.isJsonObject()) {
                    JsonObject rootobj = jsonObject.getAsJsonObject();
                    JsonObject result = rootobj.getAsJsonObject("intent");
                    String noticias = result.get("news").getAsString();
                    String pregunta = result.get("query").getAsString();
                    String spam = result.get("spam").getAsString();
                    String marketing = result.get("marketing").getAsString();
                    String comentario = result.get("feedback").getAsString();

                    float noticias1 = Float.parseFloat(noticias);
                    float pregunta1 = Float.parseFloat(pregunta);
                    float spam1 = Float.parseFloat(spam);
                    float marketing1 = Float.parseFloat(marketing);
                    float comentario1 = Float.parseFloat(comentario);
                    mol.setNoticias(noticias1);
                    mol.setPregunta(pregunta1);
                    mol.setSpam(spam1);
                    mol.setMarketing(marketing1);
                    mol.setComentario(comentario1);
                }
            }
        } catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }

    }

    public static void main(String[] args) throws Exception {
        String texto = "Hola, buenos dias, le gustaria iniciar una charla";
        String resultado = "";
        resultado = intent(texto);
        System.out.println("resultado" + resultado);
        
        model mol = new model();
        mol.setTexto(texto);
        intento(mol);
    }
}
