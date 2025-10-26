/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada.Exceptions;

import com.mycompany.gestionrecettes.model.eya.CategorieRecette;

/**
 *
 * @author farah ajmi
 */
public class RecetteDejaExistanteException extends RuntimeException {
    public RecetteDejaExistanteException(CategorieRecette cat) {
        super("Une recette existe deja pour la categorie: " + cat);
    }
}