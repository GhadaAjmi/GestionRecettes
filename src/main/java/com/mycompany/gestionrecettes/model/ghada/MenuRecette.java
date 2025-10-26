/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

/**
 *
 * @author farah ajmi
 */
import com.mycompany.gestionrecettes.model.eya.Recette;

public record MenuRecette(
    int id,
    double portion,
    Menu menu,
    Recette recette
) {
}