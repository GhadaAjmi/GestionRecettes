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

public class RepasPlanifie {
    private int id;
    private Date date;
    private int nbPersonnes;
    private Menu menu;
    
    // Constructeurs
    public RepasPlanifie() {}
    
    public RepasPlanifie(Date date, int nbPersonnes, Menu menu) {
        this.date = date;
        setNbPersonnes(nbPersonnes);
        this.menu = menu;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    
    public int getNbPersonnes() { return nbPersonnes; }
    public void setNbPersonnes(int nbPersonnes) {
        if (nbPersonnes > 0) {
            this.nbPersonnes = nbPersonnes;
        } else {
            throw new IllegalArgumentException("Le nombre de personnes doit être positif");
        }
    }
    
    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }
    
    // Méthodes de calcul
    public void ajusterPortions() {
        if (menu != null) {
            int nbPersonnesMenu = menu.getNombreTotalPersonnes();
            if (nbPersonnesMenu != nbPersonnes) {
                double ratio = (double) nbPersonnes / nbPersonnesMenu;
                for (Recette recette : menu.getRecettes().values()) {
                    recette.ajusterPortions((int) Math.round(recette.getNbPersonnes() * ratio));
                }
            }
        }
    }
    
    public double getCoutTotalAjuste() {
        if (menu == null) return 0;
        
        ajusterPortions();
        return menu.getCoutTotalApproximatif();
    }
    
    public int getTempsPreparationAjuste() {
        return menu != null ? menu.getTempsPreparationTotal() : 0;
    }
    
    // Méthodes de validation
    public boolean estValide() {
        return date != null && nbPersonnes > 0 && menu != null && menu.estValide();
    }
    
    public boolean estPourAujourdhui() {
        if (date == null) return false;
        Date aujourdhui = new Date();
        return date.getDate() == aujourdhui.getDate() &&
               date.getMonth() == aujourdhui.getMonth() &&
               date.getYear() == aujourdhui.getYear();
    }
    
    public boolean estDansLeFutur() {
        return date != null && date.after(new Date());
    }
    
    public boolean estDansLePasse() {
        return date != null && date.before(new Date());
    }
    
    // Méthodes d'information
    public String getDateFormatee() {
        if (date == null) return "Date non définie";
        return String.format("%1$tA %1$td %1$tB %1$tY", date);
    }
    
    public String getHeureFormatee() {
        if (date == null) return "";
        return String.format("%1$tH:%1$tM", date);
    }
    
    public boolean estWeekend() {
        if (date == null) return false;
        int jour = date.getDay(); // 0 = dimanche, 6 = samedi
        return jour == 0 || jour == 6;
    }
    
    public String getStatut() {
        if (estPourAujourdhui()) return "Aujourd'hui";
        if (estDansLeFutur()) return "À venir";
        if (estDansLePasse()) return "Passé";
        return "Inconnu";
    }
    
    @Override
    public String toString() {
        return String.format("RepasPlanifié{id=%d, date=%s, personnes=%d}", 
                           id, getDateFormatee(), nbPersonnes);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RepasPlanifie other = (RepasPlanifie) obj;
        return id == other.id;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}