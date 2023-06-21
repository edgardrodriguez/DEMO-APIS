/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import modelo.model;
import servicio.Abuso;

/**
 *
 * @author edgard
 */
@Named(value = "abusoC")
@SessionScoped
public class abusoC implements Serializable {

    private model model;
    private Abuso abuso;
    private float dataFlotante;
    private String textoFlotante;
    
    public abusoC() {
        model = new model();
        abuso = new Abuso();
    }
    
    public void generar() throws Exception {
        try {
            Abuso.abuso(model);
            if (model.getAbusive()> model.getHate_speech() && model.getAbusive()> model.getNeither()) {
                dataFlotante = model.getAbusive();
                textoFlotante = "abusivo";
            } else if (model.getHate_speech() > model.getAbusive()&& model.getHate_speech() >model.getNeither()){
                dataFlotante = model.getHate_speech();
                textoFlotante = "Discurso de odio";
            }else if (model.getNeither()>model.getAbusive() && model.getNeither() > model.getHate_speech()) {
                dataFlotante = model.getNeither();
                textoFlotante ="Ni uno ni otro";
            }
        } catch (Exception e) {
            System.out.println("error"+ e.getMessage());
        }
    }
    public model getModel() {
        return model;
    }

    public void setModel(model model) {
        this.model = model;
    }

    public Abuso getAbuso() {
        return abuso;
    }

    public void setAbuso(Abuso abuso) {
        this.abuso = abuso;
    }

    public float getDataFlotante() {
        return dataFlotante;
    }

    public void setDataFlotante(float dataFlotante) {
        this.dataFlotante = dataFlotante;
    }

    public String getTextoFlotante() {
        return textoFlotante;
    }

    public void setTextoFlotante(String textoFlotante) {
        this.textoFlotante = textoFlotante;
    }
    
}
