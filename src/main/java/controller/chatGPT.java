/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import modelo.model;
import servicio.ChatGPT;

/**
 *
 * @author edgard
 */
@Named(value = "chatGPT")
@SessionScoped
public class chatGPT implements Serializable {

    private model model;
    private ChatGPT chatgpt;
   
    public chatGPT() {
        model = new model();
        chatgpt = new ChatGPT();
    }
    public void generar() throws Exception {
        try {
           
            ChatGPT.ChatGPT1(model); 
            model.setRespuesta(ChatGPT.getResult()); 
        } catch (Exception e) {
            System.out.println("error"+ e.getMessage());
        }
    }
     public void limpiar() {
        model = new model();
    }
    public model getModel() {
        return model;
    }

    public void setModel(model model) {
        this.model = model;
    }

    public ChatGPT getChatgpt() {
        return chatgpt;
    }

    public void setChatgpt(ChatGPT chatgpt) {
        this.chatgpt = chatgpt;
    }

}
