/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */

import com.mycompany.gestionrecettes.model.eya.Ingredient;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ListeCourse {
    private int id;
    private String nom;
    private Date dateCreation;
    private HashMap<Ingredient, Double> ingredients; 
    private static int compteurId = 0;

    // -------------------- CONSTRUCTEURS --------------------
    public ListeCourse() {
        this.id = ++compteurId;
        this.dateCreation = new Date();
        this.ingredients = new HashMap<>();
    }

    public ListeCourse(String nom) {
        this();
        this.nom = nom;
    }

    public ListeCourse(String nom, Date dateCreation) {
        this();
        this.nom = nom;
        this.dateCreation = dateCreation;
    }

    // -------------------- GETTERS/SETTERS --------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public HashMap<Ingredient, Double> getIngredients() {
        return ingredients ;
    }

    // -------------------- GESTION DES INGRÉDIENTS --------------------
    public void ajouterIngredient(Ingredient ingredient, double quantite) {
        if (ingredient != null && quantite > 0) {
            ingredients.merge(ingredient, quantite, Double::sum);
        }
    }

    public void supprimerIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public double getQuantite(Ingredient ingredient) {
        return ingredients.getOrDefault(ingredient, 0.0);
    }

    public boolean contientIngredient(Ingredient ingredient) {
        return ingredients.containsKey(ingredient);
    }

    public void viderListe() {
        ingredients.clear();
    }

    public int getNombreIngredients() {
        return ingredients.size();
    }

    public boolean estVide() {
        return ingredients.isEmpty();
    }

    // -------------------- EQUALS, HASHCODE, TOSTRING --------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListeCourse that = (ListeCourse) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ListeCourse{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateCreation=" + dateCreation +
                ", nombreIngredients=" + ingredients.size() +
                '}';
    }

    // Méthode pour afficher le détail de la liste
    public String afficherDetail() {
        StringBuilder sb = new StringBuilder();
        sb.append("Liste: ").append(nom).append("\n");
        sb.append("Date: ").append(dateCreation).append("\n");
        sb.append("Ingrédients:\n");
        
        for (Map.Entry<Ingredient, Double> entry : ingredients.entrySet()) {
            sb.append("- ").append(entry.getKey().getNom())
              .append(": ").append(entry.getValue()).append("\n");
        }
        
        return sb.toString();
    }
}