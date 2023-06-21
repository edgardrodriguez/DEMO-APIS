/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import modelo.model;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

/**
 *
 * @author edgard
 */
public class ChatGPT {
     private static String result;
     
    public static void chatGPT(String text) throws Exception {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer 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");

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", text);
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        System.out.println(new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text"));
    }

    public static void ChatGPT1(model mol) throws Exception {
        String texto = mol.getInto();
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer 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");

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", texto);
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines().reduce((a, b) -> a + b).get();

        result = new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");

        System.out.print(result);

    }

    

    public static void main(String[] args) throws Exception {
        String texto = "cuentame una historia";
        
        model mol = new model();
        mol.setInto(texto);
        ChatGPT1(mol);
    }

    public static String getResult() {
        return result;
    }

    public static void setResult(String aResult) {
        result = aResult;
    }
}
