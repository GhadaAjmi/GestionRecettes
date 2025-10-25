/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */

public class MenuRecette {
    private int id;
    private double portion;
    private Menu menu;
    private Recette recette;
    
    // Constructeurs
    public MenuRecette() {}
    
    public MenuRecette(double portion, Menu menu, Recette recette) {
        this.portion = portion;
        this.menu = menu;
        this.recette = recette;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public double getPortion() { return portion; }
    public void setPortion(double portion) { this.portion = portion; }
    
    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }
    
    public Recette getRecette() { return recette; }
    public void setRecette(Recette recette) { this.recette = recette; }
    
    // Méthodes de calcul
    public double getQuantiteIngredientAjustee(IngredientQuantifie ingredientOrigine) {
        return ingredientOrigine.getQuantite() * portion;
    }
    
    public String getPortionFormatee() {
        if (portion == 1.0) {
            return "Portion standard";
        } else {
            return String.format("Portion: %.1fx", portion);
        }
    }
    
    // Méthodes de validation
    public boolean estValide() {
        return portion > 0 && menu != null && recette != null;
    }
    
    @Override
    public String toString() {
        return String.format("MenuRecette{menu='%s', recette='%s', portion=%.1f}", 
                           menu != null ? menu.getNom() : "null",
                           recette != null ? recette.getTitre() : "null",
                           portion);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MenuRecette other = (MenuRecette) obj;
        return id == other.id;
    }
    
    
}