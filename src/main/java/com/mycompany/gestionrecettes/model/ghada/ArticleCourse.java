/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */

import java.util.Objects;

public class ArticleCourse {
    private Ingredient ingredient;
    private double quantiteTotale;
    private String unite;

    public ArticleCourse(Ingredient ingredient, double quantiteTotale, String unite) {
        this.ingredient = ingredient;
        this.quantiteTotale = quantiteTotale;
        this.unite = unite;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantiteTotale() {
        return quantiteTotale;
    }

    public void setQuantiteTotale(double quantiteTotale) {
        this.quantiteTotale = quantiteTotale;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public void ajouterQuantite(double qte) {
        this.quantiteTotale += qte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleCourse)) return false;
        ArticleCourse that = (ArticleCourse) o;
        return Objects.equals(ingredient, that.ingredient)
                && Objects.equals(unite, that.unite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient, unite);
    }

    @Override
    public String toString() {
        return ingredient.getNom() + " : " + quantiteTotale + " " + unite;
    }
}
