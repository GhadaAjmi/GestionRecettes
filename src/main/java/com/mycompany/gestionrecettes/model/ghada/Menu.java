/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */

import java.util.*;

public class Menu {
    private int id;
    private String nom;
    private String description;
    private Date dateCreation;
    private LinkedHashMap<TypeMenu, Recette> recettes;
    
    // Constructeurs
    public Menu() {
        this.dateCreation = new Date();
        this.recettes = new LinkedHashMap<>();
    }
    
    public Menu(String nom, String description) {
        this();
        this.nom = nom;
        this.description = description;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }
    
    public LinkedHashMap<TypeMenu, Recette> getRecettes() { return recettes; }
    public void setRecettes(LinkedHashMap<TypeMenu, Recette> recettes) { this.recettes = recettes; }
    
    // Méthodes de gestion des recettes
    public void ajouterRecette(TypeMenu type, Recette recette) {
        if (type != null && recette != null) {
            this.recettes.put(type, recette);
        }
    }
    
    public void supprimerRecette(TypeMenu type) {
        this.recettes.remove(type);
    }
    
    public Recette getRecette(TypeMenu type) {
        return this.recettes.get(type);
    }
    
    public boolean contientRecette(TypeMenu type) {
        return this.recettes.containsKey(type);
    }
    
    public List<TypeMenu> getTypesMenuAvecRecettes() {
        return new ArrayList<>(recettes.keySet());
    }
    
    // Méthodes de calcul
    public int getTempsPreparationTotal() {
        return recettes.values().stream()
                .mapToInt(Recette::getTempsPreparation)
                .sum();
    }
    
    public double getCoutTotalApproximatif() {
        return recettes.values().stream()
                .mapToDouble(recette -> recette.getIngredients().stream()
                        .mapToDouble(IngredientQuantifie::getCoutApproximatif)
                        .sum())
                .sum();
    }
    
    public int getNombreTotalPersonnes() {
        return recettes.values().stream()
                .mapToInt(Recette::getNbPersonnes)
                .max()
                .orElse(0);
    }
    
    public Set<IngredientQuantifie> getIngredientsCombines() {
        Set<IngredientQuantifie> ingredientsCombines = new HashSet<>();
        for (Recette recette : recettes.values()) {
            ingredientsCombines.addAll(recette.getIngredients());
        }
        return ingredientsCombines;
    }
    
    // Méthodes de validation
    public boolean estValide() {
        return nom != null && !nom.trim().isEmpty() &&
               description != null && !description.trim().isEmpty() &&
               dateCreation != null;
    }
    
    public boolean estComplet() {
        return recettes.size() == TypeMenu.values().length;
    }
    
    public boolean estVide() {
        return recettes.isEmpty();
    }
    
    // Méthodes d'information
    public String getResume() {
        return String.format("%s (%d recettes, %d min)", nom, recettes.size(), getTempsPreparationTotal());
    }
    
    public boolean contientCategorie(CategorieRecette categorie) {
        return recettes.values().stream()
                .anyMatch(recette -> recette.getCategorie().getNom() == categorie);
    }
    
    public Map<CategorieRecette, Long> getStatistiquesCategories() {
        return recettes.values().stream()
                .collect(Collectors.groupingBy(
                    recette -> recette.getCategorie().getNom(),
                    Collectors.counting()
                ));
    }
    
    @Override
    public String toString() {
        return String.format("Menu{id=%d, nom='%s', recettes=%d}", id, nom, recettes.size());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Menu other = (Menu) obj;
        return id == other.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}