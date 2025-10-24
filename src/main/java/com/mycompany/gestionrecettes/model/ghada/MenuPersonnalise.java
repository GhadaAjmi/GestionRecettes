/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */

import java.util.List;

public class MenuPersonnalise extends Menu {
    private List<String> preferences;
    private List<String> restrictions;

    public MenuPersonnalise() {}

    public MenuPersonnalise(int id, String nom, String description, java.util.Date dateCreation,
                            List<String> preferences, List<String> restrictions) {
        super(id, nom, description, dateCreation);
        this.preferences = preferences;
        this.restrictions = restrictions;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public List<String> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<String> restrictions) {
        this.restrictions = restrictions;
    }

    @Override
    public String toString() {
        return super.toString() + " MenuPersonnalise{preferences=" + preferences + ", restrictions=" + restrictions + "}";
    }
}
