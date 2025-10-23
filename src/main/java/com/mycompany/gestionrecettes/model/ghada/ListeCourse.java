/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */

import java.util.*;
import java.util.stream.Collectors;

public class ListeCourse {
    private int id;
    private String nom;
    private Date dateCreation;
    private List<ArticleCourse> articles;
    
    // Constructeurs
    public ListeCourse() {
        this.dateCreation = new Date();
        this.articles = new ArrayList<>();
    }
    
    public ListeCourse(String nom) {
        this();
        this.nom = nom;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }
    
    public List<ArticleCourse> getArticles() { return articles; }
    public void setArticles(List<ArticleCourse> articles) { this.articles = articles; }
    
    // Méthodes de gestion des articles
    public void ajouterArticle(ArticleCourse article) {
        if (article != null && article.estValide()) {
            // Vérifier si un article similaire existe déjà
            Optional<ArticleCourse> articleExistant = articles.stream()
                    .filter(a -> a.estSimilaire(article))
                    .findFirst();
            
            if (articleExistant.isPresent()) {
                // Fusionner avec l'article existant
                articleExistant.get().fusionnerAvec(article);
            } else {
                // Ajouter le nouvel article
                articles.add(article);
            }
        }
    }
    
    public void supprimerArticle(ArticleCourse article) {
        articles.remove(article);
    }
    
    public void supprimerArticleParId(int id) {
        articles.removeIf(article -> article.getId() == id);
    }
    
    public ArticleCourse getArticleParId(int id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public void viderListe() {
        articles.clear();
    }
    
    // Méthodes de calcul
    public int getNombreArticles() {
        return articles.size();
    }
    
    public double getQuantiteTotale() {
        return articles.stream()
                .mapToDouble(ArticleCourse::getQuantite)
                .sum();
    }
    
    // Méthodes de recherche
    public List<ArticleCourse> rechercherParNom(String nom) {
        return articles.stream()
                .filter(article -> article.getNom().toLowerCase().contains(nom.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public boolean contientArticle(String nomArticle) {
        return articles.stream()
                .anyMatch(article -> article.getNom().equalsIgnoreCase(nomArticle));
    }
    
    // Méthodes de tri
    public void trierParNom() {
        articles.sort((a1, a2) -> a1.getNom().compareToIgnoreCase(a2.getNom()));
    }
    
    public void trierParQuantite() {
        articles.sort((a1, a2) -> Double.compare(a2.getQuantite(), a1.getQuantite()));
    }
    
    // Méthodes de validation
    public boolean estValide() {
        return nom != null && !nom.trim().isEmpty() && articles != null;
    }
    
    public boolean estVide() {
        return articles.isEmpty();
    }
    
    // Méthodes d'information
    public String getResume() {
        return String.format("%s - %d articles", nom, getNombreArticles());
    }
    
    public String getDateCreationFormatee() {
        return String.format("%1$td/%1$tm/%1$tY", dateCreation);
    }
    
    @Override
    public String toString() {
        return String.format("ListeCourse{id=%d, nom='%s', articles=%d}", 
                           id, nom, articles.size());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ListeCourse other = (ListeCourse) obj;
        return id == other.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}