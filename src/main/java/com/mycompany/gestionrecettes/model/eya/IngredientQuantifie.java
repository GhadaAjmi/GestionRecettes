/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.eya;

/**
 *
 * @author MSI
 */
public class IngredientQuantifie {
    private int id;
    private double quantite;
    private Ingredient ingredient;
    private Recette recette;
    
    
    public IngredientQuantifie(int id, double quantite, Ingredient ingredient, Recette recette){
        this.id = id;
        this.quantite = quantite;
        this.ingredient = ingredient;
        this.recette = recette;
    }
    
    
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    
    public double getQuantite(){return quantite;}
    public void setQuantite(double quantite){this.quantite = quantite;}
    
    public Ingredient getIngredient(){return ingredient;}
    public void setIngredient(Ingredient ingredient){this.ingredient = ingredient;}
    
    public Recette getRecettte(){return recette;}
    public void setRecette(Recette recette){this.recette = recette;}
    
    @Override
    public String toString(){
        return "IngredientQuantifie{"+"id: "+id+ ",quantite: "+quantite+" , ingredient: "+ingredient.getNom()+ " ,Recette: "+ recette.getTitre() + "}";
    }
    
    
    
}
