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
import java.util.HashMap;

public class Menu {
    private int id;
    private String nom;
    private String description;
    private Date dateCreation;
    private HashMap<TypeMenu, MenuRecette> recettes;

    public Menu() {
        this.recettes = new HashMap<>();
    }

    public Menu(int id, String nom, String description, Date dateCreation) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateCreation = dateCreation;
        this.recettes = new HashMap<>();
    }

    public void ajouterRecette(TypeMenu type, MenuRecette recette) {
        recettes.put(type, recette);
    }

    public void supprimerRecette(TypeMenu type) {
        recettes.remove(type);
    }

    public MenuRecette getRecette(TypeMenu type) {
        return recettes.get(type);
    }

    // Getters/Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public HashMap<TypeMenu, MenuRecette> getRecettes() {
        return recettes;
    }

    @Override
    public String toString() {
        return "Menu{id=" + id + ", nom='" + nom + "', description='" + description + "', dateCreation=" + dateCreation + ", recettes=" + recettes + "}";
    }
}
