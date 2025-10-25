/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */

import java.util.Date;
import java.util.HashSet;

public class ListeCourse {
    private int id;
    private String nom;
    private Date dateCreation;
    private HashSet<ArticleCourse> articles;

    // Constructeurs
    public ListeCourse() {
        this.articles = new HashSet<>();
        this.dateCreation = new Date();
    }

    public ListeCourse(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.dateCreation = new Date();
        this.articles = new HashSet<>();
    }

    // =============================
    // Méthodes principales
    // =============================

    /** Ajoute un article ou cumule la quantité s'il existe déjà */
    public void ajouterArticle(Ingredient ingredient, double quantite, String unite) {
        ArticleCourse nouveau = new ArticleCourse(ingredient, quantite, unite);

        for (ArticleCourse ac : articles) {
            if (ac.equals(nouveau)) {
                ac.ajouterQuantite(quantite);
                return;
            }
        }
        articles.add(nouveau);
    }

    /** Supprime un article correspondant à un ingrédient et unité */
    public boolean supprimerArticle(Ingredient ingredient, String unite) {
        return articles.removeIf(ac ->
            ac.getIngredient().equals(ingredient) &&
            ac.getUnite().equals(unite)
        );
    }

    /** Génère automatiquement la liste à partir d’un planificateur */
    public void genererDepuisPlanificateur(Planificateur planificateur) {
        articles.clear();
        if (planificateur == null) return;

        for (HashSet<RepasPlanifie> repasSet : planificateur.getTousLesRepas()) {
            for (RepasPlanifie repas : repasSet) {
                Menu menu = repas.getMenu();
                if (menu == null || menu.getRecettes() == null) continue;

                for (Recette recette : menu.getRecettes()) {
                    if (recette.getIngredientsQuantifies() == null) continue;

                    for (IngredientQuantifie iq : recette.getIngredientsQuantifies()) {
                        double quantiteTotale = iq.getQuantite() * repas.getNbPersonnes();
                        ajouterArticle(iq.getIngredient(), quantiteTotale, iq.getUnite());
                    }
                }
            }
        }
    }

    // =============================
    // Getters / Setters
    // =============================

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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public HashSet<ArticleCourse> getArticles() {
        return articles;
    }

    public void setArticles(HashSet<ArticleCourse> articles) {
        this.articles = articles;
    }

    // =============================
    // toString()
    // =============================
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ListeCourse{id=").append(id)
          .append(", nom='").append(nom).append('\'')
          .append(", dateCreation=").append(dateCreation)
          .append(", articles=\n");

        for (ArticleCourse ac : articles) {
            sb.append("  - ").append(ac).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
