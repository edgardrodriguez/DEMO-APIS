/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import modelo.model;
import servicio.Microservicio;

/**
 *
 * @author edgard
 */
@Named(value = "microserverC")
@SessionScoped
public class microserverC implements Serializable {

    private model model;
    private Microservicio micro;

    public microserverC() {
        model = new model();
        micro = new Microservicio();
    }

    public void generar() throws Exception {
        try {
            Microservicio.microserver(model); 
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

    public Microservicio getMicro() {
        return micro;
    }

    public void setMicro(Microservicio micro) {
        this.micro = micro;
    }

}
