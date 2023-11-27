/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author mto_l
 */
public class HistClient {
    private String register_date;
    private double weight;
 
    public HistClient(String register_date, double height) {
        this.register_date = register_date;
        this.weight = weight;
    }
 
    public String getDay() {
        return register_date;
    }
 
    public double getHeight() {
        return weight;
    }
 
    public void setDay(String day) {
        this.register_date = register_date;
    }
 
    public void setHeight(double weight) {
        this.weight = weight;
    }

}

