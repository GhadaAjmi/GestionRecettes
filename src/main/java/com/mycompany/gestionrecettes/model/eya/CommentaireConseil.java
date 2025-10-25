/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.eya;
import java.util.Date;
import java.util.List;
/**
 *
 * @author MSI
 */
public final class CommentaireConseil extends Commentaire {
    private String auteur;
    private List<String> recommandations;
    
    public CommentaireConseil(int id, String contenu, Date date, String auteur, List<String> recommandations){
        super(contenu, id, date);
        this.auteur = auteur;
        this.recommandations = recommandations;
    }
    public String getAuteur(){return auteur;}
    public void setAuteur(String auteur){this.auteur = auteur;}
    
    public List<String> getRecommandations(){return recommandations;}
    public void setRecommandations(List<String> recommandations){this.recommandations = recommandations;}
    
    
    @Override
    public void afficher(){
        System.out.println("Conseil de : "+auteur+ "à la date: "+date);
        System.out.println("Contenu: "+contenu);
        System.out.println("Recommandations: ");
        recommandations.forEach(r ->System.out.println("-"+r));
    }
   
     
}
