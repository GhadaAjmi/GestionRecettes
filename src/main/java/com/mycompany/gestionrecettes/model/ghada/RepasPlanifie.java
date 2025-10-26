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
import com.mycompany.gestionrecettes.model.eya.IngredientQuantifie;
import com.mycompany.gestionrecettes.model.eya.Recette;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class RepasPlanifie implements CalculateurIngredients {
    private int id;
    private LocalDate date;
    private int nbPersonnes;
    private Menu menu; 
    private static int compteurId = 0;


// -------------------- CONSTRUCTEURS --------------------

    public RepasPlanifie() {
            this.id = ++compteurId;
}

    public RepasPlanifie(LocalDate date, int nbPersonnes, Menu menu) {
        this.id = ++compteurId;
        this.date = date;
        this.nbPersonnes = nbPersonnes;
        this.menu = menu;
    }

 // -------------------- GETTERS/Setters --------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
    // -------------------- EQUALS, HASHCODE, TOSTRING --------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepasPlanifie r = (RepasPlanifie) o;
        return id == r.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RepasPlanifie{" + "id=" + id + ", date=" + date + ", nbPersonnes=" + nbPersonnes + ", menu=" + menu + '}';
    }
  // -------------------- INTERFACE IMPLEMENTATION --------------------
    @Override
    public Map<Ingredient, Double> getIngredientsEtQuantites() {
        Map<Ingredient, Double> totalIngredients = new HashMap<>();

        if (menu == null) return totalIngredients;

        // Parcourir les recettes du menu
        for (MenuRecette mr : menu.getRecettes().values()) {
            Recette recette = mr.recette();
            if (recette == null) continue;

            double ratio = (double) nbPersonnes / recette.getNbPersonne();

            for (IngredientQuantifie iq : recette.getIngredients()) {
                double quantiteTotale = iq.getQuantite() * ratio;
                totalIngredients.merge(iq.getIngredient(), quantiteTotale, Double::sum);
            }
        }

        return totalIngredients;
    }

}
