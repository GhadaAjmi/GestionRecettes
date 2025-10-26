/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.mycompany.gestionrecettes.model.eya;

/**
 *
 * @author MSI
 */
public class TempsPreparationException extends RuntimeException {
    private final int tempsDeclare;
    private final int tempsCalcule;

   
    public TempsPreparationException(int tempsDeclare, int tempsCalcule) {
        super(String.format(
            "Incoherence de temps : declare=%d min, calcule=%d min (différence: %d min)",
            tempsDeclare, tempsCalcule, Math.abs(tempsDeclare - tempsCalcule)
        ));
        this.tempsDeclare = tempsDeclare;
        this.tempsCalcule = tempsCalcule;
    }

    public int getTempsDeclare() { return tempsDeclare; }
    public int getTempsCalcule() { return tempsCalcule; }
    
    public int getDifference() {
        return Math.abs(tempsDeclare - tempsCalcule);
    }
    
    public boolean isTempsDeclareSuperieur() {
        return tempsDeclare > tempsCalcule;
    }
    
}
