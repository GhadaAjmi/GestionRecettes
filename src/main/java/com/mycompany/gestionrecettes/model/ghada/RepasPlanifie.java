/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */

import java.util.Date;

public class RepasPlanifie {
    private int id;
    private Date date;
    private int nbPersonnes;
    private Menu menu; 

    public RepasPlanifie() {}

    public RepasPlanifie(int id, Date date, int nbPersonnes, Menu menu) {
        this.id = id;
        this.date = date;
        this.nbPersonnes = nbPersonnes;
        this.menu = menu;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "RepasPlanifie{" +
                "id=" + id +
                ", date=" + date +
                ", nbPersonnes=" + nbPersonnes +
                ", menu=" + (menu != null ? menu.getNom() : "null") +
                '}';
    }
}
