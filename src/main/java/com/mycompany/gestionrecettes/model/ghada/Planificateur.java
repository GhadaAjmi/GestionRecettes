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
    private TreeMap<Date, HashSet<RepasPlanifie>> repas; 

    public Planificateur() {
        this.repas = new TreeMap<>();
    }

    public Planificateur(int id, Date dateDebut, Date dateFin) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.repas = new TreeMap<>();
    }

    // ✅ Ajouter un repas à une date donnée
    public void ajouterRepas(Date date, Menu menu, int nbPersonnes) {
        RepasPlanifie r = new RepasPlanifie(
            genererId(), date, nbPersonnes, menu
        );
        repas.computeIfAbsent(date, k -> new HashSet<>()).add(r);
    }

    // ✅ Supprimer un repas planifié
    public void supprimerRepas(Date date, RepasPlanifie r) {
        if (repas.containsKey(date)) {
            repas.get(date).remove(r);
            if (repas.get(date).isEmpty()) {
                repas.remove(date);
            }
        }
    }

    // ✅ Obtenir tous les repas d'une date donnée
    public Set<RepasPlanifie> getRepasParDate(Date date) {
        return repas.getOrDefault(date, new HashSet<>());
    }

    // ✅ Lister tous les repas planifiés
    public Collection<HashSet<RepasPlanifie>> getTousLesRepas() {
        return repas.values();
    }

    // ✅ Rechercher un repas selon le menu
    public RepasPlanifie rechercherRepasParMenu(Menu menu) {
        for (HashSet<RepasPlanifie> liste : repas.values()) {
            for (RepasPlanifie r : liste) {
                if (r.getMenu() != null && r.getMenu().equals(menu)) {
                    return r;
                }
            }
        }
        return null;
    }

    // ✅ Générer un identifiant simple pour un repas
    private int genererId() {
        return (int) (Math.random() * 100000);
    }

    // === Getters & Setters ===
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        retur
