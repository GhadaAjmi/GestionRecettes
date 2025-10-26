/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */

import com.mycompany.gestionrecettes.model.eya.Ingredient;
import com.mycompany.gestionrecettes.model.ghada.Exceptions.TypeMenuDupliqueException;
import com.mycompany.gestionrecettes.model.ghada.Exceptions.TypeMenuManquantException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public final class Planificateur implements CalculateurIngredients{
    private int id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private TreeMap<LocalDate, HashMap<TypeMenu, RepasPlanifie>> repas; 
    private static int compteurId = 0;
    private boolean actif=false; 
// --------------------Constructeurs-------------------------------------------------

 

    public Planificateur() {
        this.repas = new TreeMap<>(); 
        this.id = ++compteurId;
    }

    public Planificateur(int id, LocalDate dateDebut, LocalDate dateFin) {
        this.id = ++compteurId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.repas = new TreeMap<>();
    }

  // --------------------Getters/Setters-------------------------------------------------

    public int getId() {
        return id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public TreeMap<LocalDate, HashMap<TypeMenu,RepasPlanifie>> getRepas() {
        return repas;
    }

    public static int getCompteurId() {
        return compteurId;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setRepas(TreeMap<LocalDate, HashMap<TypeMenu,RepasPlanifie>> repas) {
        this.repas = repas;
    }
       public boolean isActif() {
        return actif;
    }
       public void setActif(Boolean actif) {
        this.actif=actif;
    }
       
    // -------------------- EQUALS, HASHCODE, TOSTRING --------------------

   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Planificateur other = (Planificateur) obj;
        return this.id == other.id;
    }
     @Override
    public int hashCode() {
            return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Planificateur{" + "id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }

    // -------------------- GESTION REPAS PLANIFIES --------------------
 
public void planifier(Collection<RepasPlanifie> repasPlanifies)throws TypeMenuDupliqueException, TypeMenuManquantException {
    if (repasPlanifies == null || repasPlanifies.isEmpty()) {
        throw new IllegalArgumentException("La collection de repas ne peut pas etre null ou vide");
    }
    
    if (dateDebut == null || dateFin == null) {
        throw new IllegalStateException("Les dates de debut et fin doivent être definies");
    }

    // Initialiser les jours
    LocalDate d = dateDebut;
    while (!d.isAfter(dateFin)) {
        repas.putIfAbsent(d, new HashMap<>());
        d = d.plusDays(1);
    }

    // Map pour suivre les types par date
    Map<LocalDate, Set<TypeMenu>> typesParDate = new HashMap<>();

    for (RepasPlanifie rp : repasPlanifies) {
        LocalDate dateRp = rp.getDate();

        // Validations
        if (dateRp == null) throw new IllegalArgumentException("Date null");
        if (dateRp.isBefore(dateDebut) || dateRp.isAfter(dateFin)) {
                        continue;        }
        if (rp.getMenu() == null) throw new IllegalArgumentException("Menu null");

        TypeMenu type = rp.getMenu().getType();
        
        // Vérifier doublon dans le planificateur
        if (repas.get(dateRp).containsKey(type)) {
            throw new TypeMenuDupliqueException(dateRp, type);
        }

        // Ajouter au suivi
        typesParDate.putIfAbsent(dateRp, new HashSet<>());
        typesParDate.get(dateRp).add(type);

        // Ajouter effectivement le repas
        repas.get(dateRp).put(type, rp);
    }

    // VÉRIFICATION DE COMPLÉTUDE
    for (LocalDate date : typesParDate.keySet()) {
        Set<TypeMenu> typesManquants = new HashSet<>();
        
        // Vérifier chaque type requis
        for (TypeMenu typeRequise : TypeMenu.values()) {
            if (!typesParDate.get(date).contains(typeRequise)) {
                typesManquants.add(typeRequise);
            }
        }
        
        // Si des types manquent, lancer l'exception
        if (!typesManquants.isEmpty()) {
            throw new TypeMenuManquantException(date, typesManquants);
        }
    }
}

// Récupère tous les repas d'une date spécifique
 
public Map <TypeMenu, RepasPlanifie> getRepasParDate(LocalDate date) {
    if (repas.containsKey(date)) {
        return repas.get(date);
    }
    return null ;
}

 // recuperer un repas par date et type
public RepasPlanifie getRepas(LocalDate date, TypeMenu type) {
    if (repas.containsKey(date)) {
        return repas.get(date).get(type);
    }
    return null;
}

    public ListeCourse genererListeCourse(String nomListe) {
        ListeCourse liste = new ListeCourse(nomListe);
        
        // Récupérer tous les ingrédients nécessaires
        Map<Ingredient, Double> ingredientsTotaux = this.getIngredientsEtQuantites();
        
        // Remplir directement la liste avec les ingrédients
        for (Ingredient ing : ingredientsTotaux.keySet()) {
            double quantite = ingredientsTotaux.get(ing);
            liste.ajouterIngredient(ing, quantite);
        }
        
        return liste;
    }
// Récupérer tous les repas d'un type spécifique avec Stream
    public List<RepasPlanifie> getRepasParType(TypeMenu type) {
        return repas.values().stream()                           
            .map(repasDuJour -> repasDuJour.get(type))           
            .filter(Objects::nonNull)                            
            .collect(Collectors.toList());                       
    }


      // -------------------- Classe Anonyme (Comparator) --------------------
 
    public List<RepasPlanifie> trierAvecListSort() {
    List<RepasPlanifie> tousRepas = new ArrayList<>();
    for (HashMap<TypeMenu, RepasPlanifie> repasDuJour : repas.values()) {
        tousRepas.addAll(repasDuJour.values());
    }
    
    tousRepas.sort(new Comparator<RepasPlanifie>() {
        @Override
        public int compare(RepasPlanifie r1, RepasPlanifie r2) {
            return Integer.compare(r1.getNbPersonnes(), r2.getNbPersonnes());
        }
    });
    
    return tousRepas;
}

   // -------------------- INTERFACE IMPLEMENTATION --------------------
    @Override
    public Map<Ingredient, Double> getIngredientsEtQuantites() {
        Map<Ingredient, Double> totalIngredients = new HashMap<>();

        // Parcourir tous les repas planifiés
        for (HashMap<TypeMenu, RepasPlanifie> repasDuJour : repas.values()) {
            for (RepasPlanifie rp : repasDuJour.values()) {
                // Utiliser la méthode de RepasPlanifie qui implémente déjà l'interface
                Map<Ingredient, Double> ingredientsRepas = rp.getIngredientsEtQuantites();
                
                // Fusionner dans le total
                for (Ingredient ing : ingredientsRepas.keySet()) {
                    double quantite = ingredientsRepas.get(ing);
                    totalIngredients.merge(ing, quantite, Double::sum);
                }
            }
        }

        return totalIngredients;
    }













}