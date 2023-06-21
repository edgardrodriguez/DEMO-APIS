/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.model;
import servicio.DNI;

/**
 *
 * @author edgard
 */
@Named(value = "dniC")
@SessionScoped
public class dniC implements Serializable {

    private model model;
    private DNI dni;

    public dniC() {
        model = new model();
        dni = new DNI();
    }
    
    public void generar() throws Exception {
        try {
           DNI.buscarDniReniec(model);
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

    public DNI getDni() {
        return dni;
    }

    public void setDni(DNI dni) {
        this.dni = dni;
    }

  

}
