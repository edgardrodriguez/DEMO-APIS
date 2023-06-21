/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.model;
import servicio.Sarcasmo;

/**
 *
 * @author edgard
 */
@Named(value = "sarcasmoC")
@SessionScoped
public class sarcasmoC implements Serializable {

    private model model;
    private Sarcasmo sarcasmo;
    private float dataFlotante;
    private String textoFlotante;

    public sarcasmoC() {
        model = new model();
        sarcasmo = new Sarcasmo();
    }

    public void generar() throws Exception {
        try {
            Sarcasmo.sarcasmo(model);
            if (model.getSarcastico() > model.getNoSarcastico()) {
                dataFlotante = model.getSarcastico();
                textoFlotante = "sarcastico";
            } else if (model.getNoSarcastico() > model.getSarcastico()) {
                dataFlotante = model.getNoSarcastico();
                textoFlotante = "No sarastico";
            }
        } catch (Exception e) {
            System.out.println("Error en generar TextoC/elegir: " + e.getMessage());
        }
    }

    public model getModel() {
        return model;
    }

    public void setModel(model model) {
        this.model = model;
    }

    public Sarcasmo getSarcasmo() {
        return sarcasmo;
    }

    public void setSarcasmo(Sarcasmo sarcasmo) {
        this.sarcasmo = sarcasmo;
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
