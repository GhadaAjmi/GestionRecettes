/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.eya;
import java.util.*;
/**
 *
 * @author MSI
 */
public class Recette {
    private int id;
    private String titre;
    private String description;
    private int tempsPreparation;
    private int nbPersonne;
    
    private TreeMap<Integer, Etape> etapes;
    private HashSet<IngredientQuantifie> ingredients;
    private List<Commentaire> commentaires;
    
    public Recette(int id, String titre, String description, int tempsPreparation, int nbPersonne) {
        this.id = id;
    this.titre = titre;
    this.description = description;
    this.tempsPreparation = tempsPreparation;
    this.nbPersonne = nbPersonne;

    etapes = new TreeMap<>();
    ingredients = new HashSet<>();
    commentaires = new ArrayList<>();
    }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getTempsPreparation() { return tempsPreparation; }
    public void setTempsPreparation(int tempsPreparation) { this.tempsPreparation = tempsPreparation; }
    
    public String getTitre(){return titre;}
    public void setTitre(String titre){this.titre=titre;}

    public int getNbPersonne() { return nbPersonne; }
    public void setNbPersonne(int nbPersonne) { this.nbPersonne = nbPersonne; }

    public TreeMap<Integer, Etape> getEtapes() { return etapes; }
    public void setEtapes(TreeMap<Integer, Etape> etapes) { this.etapes = etapes; }

    public HashSet<IngredientQuantifie> getIngredients() { return ingredients; }
    public void setIngredients(HashSet<IngredientQuantifie> ingredients) { this.ingredients = ingredients; }

    public List<Commentaire> getCommentaires() { return commentaires; }
    public void setCommentaires(List<Commentaire> commentaires) { this.commentaires = commentaires; }
    
    public void ajouterEtape(int ordre, Etape e) {
        etapes.put(ordre, e);
    }
    
    public void ajouterIngredient(IngredientQuantifie iq) {
        ingredients.add(iq);
    }
    
    public void ajouterCommentaire(Commentaire c) {
        commentaires.add(c);
    }
    
    public void supprimerEtape(int ordre) {
    if (etapes.containsKey(ordre)) {
        etapes.remove(ordre);
        System.out.println("Étape " + ordre + " supprimée avec succès.");
    } else {
        System.out.println("Aucune étape trouvée pour l’ordre " + ordre + ".");
    }
}
    public void supprimerIngredient(IngredientQuantifie iq) {
    if (ingredients.remove(iq)) {
        System.out.println("Ingrédient supprimé : " + iq);
    } else {
        System.out.println("Ingrédient non trouvé : " + iq);
    }
}
    public void supprimerCommentaire(Commentaire c) {
    if (commentaires.remove(c)) {
        System.out.println("Commentaire supprimé : " + c);
    } else {
        System.out.println("Commentaire non trouvé.");
    }
}
    
public void afficherEtapes() {
    System.out.println("Etapes de la recette _____" + titre + "____ :");
    
        for (Map.Entry<Integer, Etape> entry : etapes.entrySet()) {
            System.out.println("  " + entry.getKey() + ". " + entry.getValue());
        
    }
}
  public void afficherIngredients() {
    System.out.println("Ingedients de la recette ____" + titre + "____ :");
    
        for (IngredientQuantifie iq : ingredients) {
            System.out.println("  - " + iq);
        }
    }
  
public void afficherCommentaires() {
    System.out.println("Commentaires de la recette ____" + titre + "____ :");
    
        for (Commentaire c : commentaires) {
            System.out.println("  • " + c);
       
    }
}

    
    @Override
    public String toString() {
        return "Recette {" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", tempsPreparation=" + tempsPreparation +
                ", nbPersonne=" + nbPersonne +
                ", nbEtapes=" + etapes.size() +
                ", nbIngredients=" + ingredients.size() +
                ", nbCommentaires=" + commentaires.size() +
                '}';
    }
}
