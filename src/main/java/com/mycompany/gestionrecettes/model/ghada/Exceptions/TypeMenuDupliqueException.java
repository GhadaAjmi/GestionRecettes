/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada.Exceptions;

import com.mycompany.gestionrecettes.model.ghada.TypeMenu;
import java.time.LocalDate;

/**
 *
 * @author farah ajmi
 */
public class TypeMenuDupliqueException  extends Exception {
    public TypeMenuDupliqueException(LocalDate date, TypeMenu type) {
        super("Le type de menu '" + type + "' est deja planifie pour la date " + date);
    }
    
}
