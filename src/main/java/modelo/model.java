/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import lombok.Data;

@Data
public class model {

    //emociones
    private float happy;
    private float sad;
    private float fear;
    private float angry;
    private float bored;
    private float excited;
    private String texto;
    private String textIn;

    //abuso
    private float abusive;
    private float hate_speech;
    private float neither;

    //sarcastico
    private float sarcastico;
    private float noSarcastico;

    //extractor
    private String clave;
    private int score;

    //intension
    private float noticias;
    private float pregunta;
    private float spam;
    private float marketing;
    private float comentario;

    //DNI
    private String DNI;
    private String numero;
    private String nombre_completo;
    private String nombres;
    private String apellidos;
    private String apellido_paterno;
    private String apellido_materno;
    private String ubigeo_sunat;

    //chat gpt
    private String into;
    private String respuesta;
}
