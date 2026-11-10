
package com.example.douanetemporelle;

public class AppKeys {

    // رقم بطاقة الطالب: عوضه بآخر 4 أرقام من بطاقتك
    public static final String MATRICULE = "1234";

    // إنشاء المفاتيح بطريقة ديناميكية
    public static String key(String matricule, String nom) {
        return matricule + "_" + nom;
    }
}

