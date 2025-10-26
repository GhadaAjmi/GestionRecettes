/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada;

import com.mycompany.gestionrecettes.model.eya.Ingredient;
import java.util.Map;

/**
 *
 * @author farah ajmi
 */
public interface CalculateurIngredients {
    public interface SourceIngredients {
        Map<Ingredient, Double> getIngredientsEtQuantites();

    
}

}
