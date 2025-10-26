/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class MenuPersonnalise extends Menu {
    private List<String> preferences;
    private List<String> restrictions;
    
// --------------------Constructeurs-------------------------------------------------

    public MenuPersonnalise() {
        super();
        this.preferences = new ArrayList<>();
        this.restrictions = new ArrayList<>();
    }

    public MenuPersonnalise(String nom, String description, Date dateCreation, TypeMenu type) {
        super(nom, description, dateCreation, type);
        this.preferences = new ArrayList<>();
        this.restrictions = new ArrayList<>();
    }
//---------------Getters/Setters-------------------------------------------------

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

 // --------------------ToString-------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + " MenuPersonnalise{preferences=" + preferences + ", restrictions=" + restrictions + "}";
    }
    
    
// --------------------Gestion Preferences-------------------------------------------------
    public void ajouterPreference(String preference) {
            preferences.add(preference);
            System.out.println("Preference ajoutee: " + preference);
      
    }
  public String supprimerPreference(int index) {
        if (index < 0 || index >= preferences.size()) {
            throw new IllegalArgumentException("Index invalide: " + index);
        }
        
        String preferenceSupprimee = preferences.remove(index);
        System.out.println("Preference supprimee: " + preferenceSupprimee);
        return preferenceSupprimee;
    }
    public void modifierPreference(int index, String nouvellePreference) {
        if (index < 0 || index >= preferences.size()) {
            throw new IllegalArgumentException("Index invalide: " + index);
        }
       
      
        preferences.set(index, nouvellePreference);
        
        System.out.println("Preference modifiee !");
}
    
    
 public void afficherPreferences() {
        System.out.println("\n LISTE DES PREFERENCES (" + preferences.size() + "):");
        System.out.println("----------------------------");
        
        if (preferences.isEmpty()) {
            System.out.println("Aucune preference definie");
        } else {
            for (int i = 0; i < preferences.size(); i++) {
                System.out.println((i + 1) + ". " + preferences.get(i));
            }
        }
        System.out.println("----------------------------");
    }
    public void afficherPreference(int index) {
        if (index < 0 || index >= preferences.size()) {
            throw new IllegalArgumentException("Index invalide: " + index);
        }
        
        System.out.println(" Preference " + (index + 1) + ": " + preferences.get(index));
    }

// --------------------Gestion Restrictions-------------------------------------------------
    public void ajouterRestriction(String restriction) {
            restrictions.add(restriction);
            System.out.println("Restriction ajoutee: " + restriction);
      
    }
  
    public String supprimerRestriction(int index) {
        if (index < 0 || index >= restrictions.size()) {
            throw new IllegalArgumentException("Index invalide: " + index);
        }
        
        String restrictionSupprimee = restrictions.remove(index);
        System.out.println("Restriction supprimee: " + restrictionSupprimee);
        return restrictionSupprimee;
    }
    
    public void modifierRestriction(int index, String nouvelleRestriction) {
        if (index < 0 || index >= restrictions.size()) {
            throw new IllegalArgumentException("Index invalide: " + index);
        }
       
        restrictions.set(index, nouvelleRestriction);
        System.out.println("Restriction modifiee !");
    }
    
    public void afficherRestrictions() {
        System.out.println("\n LISTE DES RESTRICTIONS (" + restrictions.size() + "):");
        System.out.println("----------------------------");
        
        if (restrictions.isEmpty()) {
            System.out.println("Aucune restriction definie");
        } else {
            for (int i = 0; i < restrictions.size(); i++) {
                System.out.println((i + 1) + ". " + restrictions.get(i));
            }
        }
        System.out.println("----------------------------");
    }
    
    public void afficherRestriction(int index) {
        if (index < 0 || index >= restrictions.size()) {
            throw new IllegalArgumentException("Index invalide: " + index);
        }
        
        System.out.println(" Restriction " + (index + 1) + ": " + restrictions.get(index));
    }












}
