/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.eya;

/**
 *
 * @author MSI
 */
public final class Etape {
    private String Description;
    private String Conseils;
    private int Duree;
    public Etape (String Description, String Conseils, int Duree )
    {
        this.Description = Description;
        this.Conseils = Conseils;
        this.Duree = Duree;
    }
    
    public String getDescription (){return Description;}
    public String getConseils (){return Conseils;}
    public int getDuree(){return Duree;}
    
    public void setDescription (String Description){this.Description = Description;}
    public void setConseils(String Conseils){this.Conseils = Conseils;}
    public void setDuree(int Duree){this.Duree = Duree;}
    
    
   @Override
   public String toString() {
       return Description + "conseils: "+ Conseils +"( Duree: " +Duree+ "min)";
   }
}
