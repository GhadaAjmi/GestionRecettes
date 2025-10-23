/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */
public class ArticleCourse {
    private int id;
    private String nom;
    private double quantite;
    
    // Constructeurs
    public ArticleCourse() {}
    
    public ArticleCourse(String nom, double quantite) {
        this.nom = nom;
        this.quantite = quantite;
    }
    
    public ArticleCourse(int id, String nom, double quantite) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public double getQuantite() { return quantite; }
    public void setQuantite(double quantite) { 
        if (quantite >= 0) {
            this.quantite = quantite; 
        }
    }
    
    // Méthodes de validation
    public boolean estValide() {
        return nom != null && !nom.trim().isEmpty() && quantite >= 0;
    }
    
    // Méthodes de formatage
    public String getQuantiteFormatee() {
        if (quantite == (long) quantite) {
            return String.format("%d", (long) quantite);
        } else {
            return String.format("%.2f", quantite);
        }
    }
    
    // Méthodes de comparaison
    public boolean estSimilaire(ArticleCourse autre) {
        if (autre == null) return false;
        return this.nom.equalsIgnoreCase(autre.nom);
    }
    
    public void fusionnerAvec(ArticleCourse autre) {
        if (estSimilaire(autre)) {
            this.quantite += autre.quantite;
        }
    }
    
    @Override
    public String toString() {
        return String.format("ArticleCourse{id=%d, nom='%s', quantite=%s}", 
                           id, nom, getQuantiteFormatee());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ArticleCourse other = (ArticleCourse) obj;
        return id == other.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}