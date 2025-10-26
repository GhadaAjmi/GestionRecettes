/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionrecettes.model.ghada.Exceptions;

import com.mycompany.gestionrecettes.model.ghada.TypeMenu;
import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author farah ajmi
 */
public class TypeMenuManquantException extends Exception {
        public TypeMenuManquantException(LocalDate date, Set<TypeMenu> typesManquants) {
        super("La date " + date + " manque les types de menu: " + typesManquants);
    }
    
}
