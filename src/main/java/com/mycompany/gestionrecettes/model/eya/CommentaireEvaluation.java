/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.eya;
import java.util.Date;
import java.util.Objects;
/**
 *
 * @author MSI
 */
public final class CommentaireEvaluation extends Commentaire {
     private final double note;
    public CommentaireEvaluation( String contenu,int id, Date date, double note) {
        super(contenu, id, date);
        this.note = note;}
     @Override 
     public void afficher(){
         System.out.println("Id :"+getId());
         System.out.println("Evaluation :"+note);
         System.out.println("Contenu :"+getContenu());
         System.out.println("Date :"+getDate());
     }
     public double getNote(){return note;}
     
     
     
     }

