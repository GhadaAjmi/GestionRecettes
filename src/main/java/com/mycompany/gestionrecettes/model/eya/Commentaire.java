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
public sealed abstract class Commentaire
        permits CommentaireConseil, CommentaireEvaluation {

    protected String contenu;
    protected int id;
    protected Date date;


    public Commentaire(String contenu, int id, Date date) {
        this.contenu = contenu;
        this.id = id;
        this.date = date;
    }

    public abstract void afficher();

    
    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }
     
    public int getId() { return id; }
    public void setId(String auteur) { this.id = id; }
    
    public  Date getDate(){return date;}
    public void setDate(Date date){this.date = date;}
    
    
    
}