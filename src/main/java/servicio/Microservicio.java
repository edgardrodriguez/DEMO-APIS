/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.HttpURLConnection;
import modelo.model;

/**
 *
 * @author edgard
 */
public class Microservicio {

    public static void microserver(model mol) throws IOException {
        String texto = mol.getMicro();
        URL ul1 = new URL("http://localhost:8081/products/" + texto);

        HttpURLConnection urlC = (HttpURLConnection) ul1.openConnection();
        urlC.setRequestMethod("GET");

        InputStream inputStream = urlC.getInputStream();
        BufferedInputStream reader = new BufferedInputStream(inputStream);

        int readerBuffer = reader.read();

        String myJson = "";

        while (readerBuffer != -1) {
            char u = (char) readerBuffer;
            myJson += u;
            readerBuffer = reader.read();
        }

        reader.close();
        inputStream.close();

        JsonObject jsonObj = JsonParser.parseString​(myJson).getAsJsonObject();
        
        String id = jsonObj.get("id").getAsString();
        String name = jsonObj.get("nombre").getAsString();
        String cantidad = jsonObj.get("cantidad").getAsString();
        String precio = jsonObj.get("precio").toString();
        System.out.println("Resultado\n");
        System.out.println("id: "+id + "\n" + "name: "+name + "\n" +"cantidad: "+ cantidad + "\n" + "precio: "+precio );
        
        mol.setIdMicro(id);
        mol.setNameMicro(name);
        mol.setCantidadMicro(cantidad);
        mol.setPrecioMicro(precio);
    }

    public static void main(String[] args) throws Exception {
        String texto = "64935a666bae25643e2526c1";
        model mol = new model();
        mol.setMicro(texto);
        microserver(mol);
    }
}
