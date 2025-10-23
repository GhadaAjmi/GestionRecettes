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

public class Planificateur {
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private TreeMap<Date, RepasPlanifie> repas;
    
    // Constructeurs
    public Planificateur() {
        this.repas = new TreeMap<>();
    }
    
    public Planificateur(Date dateDebut, Date dateFin) {
        this();
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    
    public TreeMap<Date, RepasPlanifie> getRepas() { return repas; }
    public void setRepas(TreeMap<Date, RepasPlanifie> repas) { this.repas = repas; }
    
    // Méthodes de gestion des repas
    public void ajouterRepas(RepasPlanifie repas) {
        if (repas != null && repas.getDate() != null && repas.estValide()) {
            this.repas.put(repas.getDate(), repas);
        }
    }
    
    public void supprimerRepas(Date date) {
        this.repas.remove(date);
    }
    
    public RepasPlanifie getRepas(Date date) {
        return this.repas.get(date);
    }
    
    public boolean contientRepas(Date date) {
        return this.repas.containsKey(date);
    }
    
    public List<RepasPlanifie> getRepasParPeriode(Date debut, Date fin) {
        return repas.subMap(debut, true, fin, true).values().stream()
                .toList();
    }
    
    // Méthodes de calcul
    public int getNombreTotalRepas() {
        return repas.size();
    }
    
    public int getNombreJoursAvecRepas() {
        return (int) repas.keySet().stream()
                .map(date -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    return cal.get(Calendar.DAY_OF_YEAR) + cal.get(Calendar.YEAR) * 1000;
                })
                .distinct()
                .count();
    }
    
    public double getCoutTotalPeriod() {
        return repas.values().stream()
                .mapToDouble(RepasPlanifie::getCoutTotalAjuste)
                .sum();
    }
    
    public int getTempsPreparationTotal() {
        return repas.values().stream()
                .mapToInt(RepasPlanifie::getTempsPreparationAjuste)
                .sum();
    }
    
    public Map<Date, Integer> getTempsPreparationParJour() {
        Map<Date, Integer> tempsParJour = new HashMap<>();
        for (Map.Entry<Date, RepasPlanifie> entry : repas.entrySet()) {
            Date date = entry.getKey();
            int temps = entry.getValue().getTempsPreparationAjuste();
            tempsParJour.merge(date, temps, Integer::sum);
        }
        return tempsParJour;
    }
    
    // Méthodes de validation
    public boolean estValide() {
        return dateDebut != null && dateFin != null && 
               !dateDebut.after(dateFin) && repas != null;
    }
    
    public boolean estEnCours() {
        Date maintenant = new Date();
        return dateDebut != null && dateFin != null &&
               !dateDebut.after(maintenant) && !dateFin.before(maintenant);
    }
    
    public boolean estTermine() {
        return dateFin != null && dateFin.before(new Date());
    }
    
    public boolean estAVenir() {
        return dateDebut != null && dateDebut.after(new Date());
    }
    
    // Méthodes de planification
    public boolean peutAjouterRepas(Date date) {
        if (date == null || dateDebut == null || dateFin == null) return false;
        return !date.before(dateDebut) && !date.after(dateFin);
    }
    
    public List<Date> getJoursSansRepas() {
        List<Date> joursSansRepas = new ArrayList<>();
        if (dateDebut == null || dateFin == null) return joursSansRepas;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateDebut);
        
        while (!cal.getTime().after(dateFin)) {
            Date currentDate = cal.getTime();
            if (!contientRepas(currentDate)) {
                joursSansRepas.add(currentDate);
            }
            cal.add(Calendar.DATE, 1);
        }
        
        return joursSansRepas;
    }
    
    public Map<String, Long> getStatistiquesMenus() {
        return repas.values().stream()
                .collect(Collectors.groupingBy(
                    repas -> repas.getMenu().getNom(),
                    Collectors.counting()
                ));
    }
    
    public ListeCourse genererListeCourseComplete() {
        ListeCourse listeCourse = new ListeCourse("Liste de courses - " + new Date());
        
        // Regrouper tous les ingrédients de tous les repas
        Map<Ingredient, Double> ingredientsCombines = new HashMap<>();
        
        for (RepasPlanifie repas : repas.values()) {
            for (IngredientQuantifie iq : repas.getMenu().getIngredientsCombines()) {
                Ingredient ingredient = iq.getIngredient();
                double quantite = iq.getQuantite();
                
                ingredientsCombines.merge(ingredient, quantite, Double::sum);
            }
        }
        
        // Créer les articles de course
        for (Map.Entry<Ingredient, Double> entry : ingredientsCombines.entrySet()) {
            ArticleCourse article = new ArticleCourse(
                entry.getKey().getNom(),
                entry.getValue(),
                entry.getKey().getUnite()
            );
            listeCourse.ajouterArticle(article);
        }
        
        return listeCourse;
    }
    
    // Méthodes d'information
    public String getDureeFormatee() {
        if (dateDebut == null || dateFin == null) return "Période non définie";
        
        long diff = dateFin.getTime() - dateDebut.getTime();
        long jours = diff / (1000 * 60 * 60 * 24) + 1;
        
        if (jours == 1) return "1 jour";
        return jours + " jours";
    }
    
    public String getResume() {
        return String.format("Planificateur %s - %d repas sur %d jours", 
                           getDureeFormatee(), getNombreTotalRepas(), getNombreJoursAvecRepas());
    }
    
    @Override
    public String toString() {
        return String.format("Planificateur{id=%d, période=%s, repas=%d}", 
                           id, getDureeFormatee(), repas.size());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Planificateur other = (Planificateur) obj;
        return id == other.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}