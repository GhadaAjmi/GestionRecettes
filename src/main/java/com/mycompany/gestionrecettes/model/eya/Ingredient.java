/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.eya;

/**
 *
 * @author MSI
 */
public class Ingredient {
    private int id;
    private String nom;
    private String description;
    private String unite;
    
    public Ingredient (int id, String nom, String description ,String unite){
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.unite = unite;
    }
    
    
    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    
    public String getNom(){return nom;}
    public void setNom(){this.nom = nom;}
    
    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}
    
    public String getUnite(){return unite;}
    public void setUnite(String unite){this.unite = unite;}
    
    
    @Override 
    public String toString(){
        return "Ingredient { " +"id: "+id+", nom: "+nom+"Description:"+description+",unite : "+unite+"}";}
    }

