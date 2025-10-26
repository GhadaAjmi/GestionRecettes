/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */

import com.mycompany.gestionrecettes.model.eya.CategorieRecette;
import com.mycompany.gestionrecettes.model.ghada.Exceptions.MenuIncompletException;
import com.mycompany.gestionrecettes.model.ghada.Exceptions.RecetteDejaExistanteException;
import com.mycompany.gestionrecettes.model.ghada.Exceptions.RecetteNonTrouveeException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public  sealed class Menu permits MenuPersonnalise {
    private int id;
    private String nom;
    private String description;
    private Date dateCreation;
    private HashMap<CategorieRecette, MenuRecette> recettes;
    private TypeMenu type ;
    private static int compteurId = 0;


// --------------------Constructeurs-------------------------------------------------

    public Menu() {
        this.id = ++compteurId;
        this.recettes = new HashMap<>();
    }

    public Menu( String nom, String description, Date dateCreation,TypeMenu type) {
        this.id = ++compteurId;
        this.nom = nom;
        this.description = description;
        this.dateCreation = dateCreation;
        this.type= type ;
        this.recettes = new HashMap<>();
    }


    // --------------------Getters/Setters-------------------------------------------------
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

    public TypeMenu getType() {
        return type;
    }

    public void setType(TypeMenu type) {
        this.type = type;
    }

    public HashMap<CategorieRecette, MenuRecette> getRecettes() {
        return recettes;
    }
    // -------------------- EQUALS, HASHCODE, TOSTRING --------------------

    @Override
    public String toString() {
        return "Menu{id=" + id + ", nom='" + nom + "', description='" + description + "', dateCreation=" + dateCreation + ", recettes=" + recettes + "}";
    }
      @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id == menu.id; }

    @Override
    public int hashCode() {
        return Objects.hash(id); 
    }
    // --------------------Gestion Recettes------------------------------------------------


    public void ajouterRecette(CategorieRecette categorie, MenuRecette recette) throws RecetteDejaExistanteException {
   
         if (recettes.containsKey(categorie)) {
        throw new RecetteDejaExistanteException(categorie);
    }
        recettes.put(categorie, recette);
    }

      public void supprimerRecette(CategorieRecette categorie) throws RecetteNonTrouveeException {
        if (!recettes.containsKey(categorie)) {
            throw new RecetteNonTrouveeException(categorie);
        }
        recettes.remove(categorie);
    }
    
 
  public MenuRecette getRecette(CategorieRecette categorie) throws RecetteNonTrouveeException {
        MenuRecette recette = recettes.get(categorie);
        if (recette == null) {
            throw new RecetteNonTrouveeException(categorie);
        }
        return recette;
    }
   
     public void validerMenuComplet() throws MenuIncompletException {
        Set<CategorieRecette> categoriesManquantes = new HashSet<>();
        for (CategorieRecette catRequise : CategorieRecette.values()) {
            if (!recettes.containsKey(catRequise)) {
                categoriesManquantes.add(catRequise);
            }
        }
        if (!categoriesManquantes.isEmpty()) {
            throw new MenuIncompletException(categoriesManquantes);
        }
    }
}