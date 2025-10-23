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
import java.util.List;

public class MenuPersonnalise extends Menu {
    private List<String> preferences;
    private List<String> restrictions;
    
    // Constructeurs
    public MenuPersonnalise() {
        super();
        this.preferences = new ArrayList<>();
        this.restrictions = new ArrayList<>();
    }
    
    public MenuPersonnalise(String nom, String description) {
        super(nom, description);
        this.preferences = new ArrayList<>();
        this.restrictions = new ArrayList<>();
    }
    
    // Getters et Setters
    public List<String> getPreferences() { return preferences; }
    public void setPreferences(List<String> preferences) { this.preferences = preferences; }
    
    public List<String> getRestrictions() { return restrictions; }
    public void setRestrictions(List<String> restrictions) { this.restrictions = restrictions; }
    
    // Méthodes de gestion des préférences
    public void ajouterPreference(String preference) {
        if (preference != null && !preference.trim().isEmpty()) {
            this.preferences.add(preference.toLowerCase());
        }
    }
    
    public void supprimerPreference(String preference) {
        this.preferences.remove(preference.toLowerCase());
    }
    
    public boolean contientPreference(String preference) {
        return this.preferences.contains(preference.toLowerCase());
    }
    
    // Méthodes de gestion des restrictions
    public void ajouterRestriction(String restriction) {
        if (restriction != null && !restriction.trim().isEmpty()) {
            this.restrictions.add(restriction.toLowerCase());
        }
    }
    
    public void supprimerRestriction(String restriction) {
        this.restrictions.remove(restriction.toLowerCase());
    }
    
    public boolean contientRestriction(String restriction) {
        return this.restrictions.contains(restriction.toLowerCase());
    }
    
    // Méthodes de validation avancée
    @Override
    public boolean estValide() {
        return super.estValide() && preferences != null && restrictions != null;
    }
    
    public boolean estCompatibleAvecRestrictions(Recette recette) {
        if (recette == null || restrictions.isEmpty()) return true;
        
        String titreLower = recette.getTitre().toLowerCase();
        String descLower = recette.getDescription().toLowerCase();
        
        for (String restriction : restrictions) {
            if (titreLower.contains(restriction) || descLower.contains(restriction)) {
                return false;
            }
        }
        
        // Vérifier les ingrédients
        for (IngredientQuantifie iq : recette.getIngredients()) {
            String nomIngredient = iq.getIngredient().getNom().toLowerCase();
            for (String restriction : restrictions) {
                if (nomIngredient.contains(restriction)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public boolean correspondAuxPreferences(Recette recette) {
        if (recette == null || preferences.isEmpty()) return true;
        
        String titreLower = recette.getTitre().toLowerCase();
        String descLower = recette.getDescription().toLowerCase();
        
        for (String preference : preferences) {
            if (titreLower.contains(preference) || descLower.contains(preference)) {
                return true;
            }
        }
        
        return false;
    }
    
    // Méthodes de génération automatique
    public boolean peutGenererMenuAutomatique() {
        return !preferences.isEmpty() && getRecettes().isEmpty();
    }
    
    public String getResumePersonnalisation() {
        return String.format("Préférences: %d, Restrictions: %d", 
                           preferences.size(), restrictions.size());
    }
    
    @Override
    public String toString() {
        return String.format("MenuPersonnalisé{id=%d, nom='%s', %s}", 
                           getId(), getNom(), getResumePersonnalisation());
    }
}