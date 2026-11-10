package com.example.douanetemporelle;

public class AppKeys {

    // Nesta3mlou ekher 4 ar9am men carte étudiant
    public static final String MATRICULE = "1234";

    // Ncréyiw les clés dynamiquement 7asb numéro étudiant.
    public static String key(String matricule, String nom) {
        return matricule + "_" + nom;
    }
}
