/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import modelo.model;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edgard
 */
public class DNI {

    public static String buscar(String text) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://apiperu.dev/api/dni/" + text)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer 8d2c7f0b2c00fda339e955c8c068a4eca54dc400b81345264662916922bb21fe")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static void buscarDniReniec(model mol) throws Exception {
        try {
            String texto = mol.getDNI();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://apiperu.dev/api/dni/" + texto)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer 8d2c7f0b2c00fda339e955c8c068a4eca54dc400b81345264662916922bb21fe")
                    .build();
            Response response = client.newCall(request).execute();
            JsonObject jsonObject = JsonParser.parseString​(response.body().string()).getAsJsonObject();
            if (jsonObject.isJsonObject()) {
                JsonObject rootobj = jsonObject.getAsJsonObject();
                JsonObject dni = rootobj.getAsJsonObject("data");
                String numero = dni.get("numero").getAsString();
                String nombre_completo = dni.get("nombre_completo").getAsString();
                String nombres = dni.get("nombres").getAsString();
                String apellido_paterno = dni.get("apellido_paterno").getAsString();
                String apellido_materno = dni.get("apellido_materno").getAsString();
                System.out.println("Resultado\n");
                System.out.println(numero+"\n"+ nombre_completo + "\n"+ nombres + "\n"+ apellido_paterno + "\n"+ apellido_materno);
                mol.setNumero(numero);
                mol.setNombre_completo(nombre_completo);
                mol.setNombres(nombres);
                mol.setApellido_paterno(apellido_paterno);
                mol.setApellido_materno(apellido_materno);
            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        String texto = "72717476";
        String resultado = "";
        resultado = buscar(texto);
        System.out.println("resultado" + resultado);
        
         model mol = new model();
        mol.setDNI(texto);
        buscarDniReniec(mol);
    }

}
