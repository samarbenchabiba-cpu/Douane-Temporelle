
package com.example.douanetemporelle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class VerificationActivity extends Activity {

    private TextView tvNom, tvAge, tvMotif, tvStatut;
    private Button btnApprouver, btnRefuser;

    private String nomVoyageur;

    // Nethabtou ken el nombre premier.
    private boolean estPremier(int nombre) {

        //Les nombres eli a9al men 2 mahomch premiers.
        if (nombre < 2) {
            return false;
        }

        // Njarrbou les diviseurs.
        for (int i = 2; i <= Math.sqrt(nombre); i++) {
            if (nombre % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // Norbtou  les éléments mta3 l’interface.
        tvNom = findViewById(R.id.SC_tv_nom_nj);
        tvAge = findViewById(R.id.SC_tv_age_nj);
        tvMotif = findViewById(R.id.SC_tv_motif_nj);
        tvStatut = findViewById(R.id.SC_tv_statut_nj);

        btnApprouver = findViewById(R.id.SC_btn_approuver_nj);
        btnRefuser = findViewById(R.id.SC_btn_refuser_nj);

        // Nesta9blou les données.
        String matricule = AppKeys.MATRICULE;

        Intent intent = getIntent();

        nomVoyageur = intent.getStringExtra(
                AppKeys.key(matricule, "NOM_VOYAGEUR")
        );

        int naissance = intent.getIntExtra(
                AppKeys.key(matricule, "ANNEE_NAISSANCE"), 0
        );

        int destination = intent.getIntExtra(
                AppKeys.key(matricule, "ANNEE_DESTINATION"), 0
        );

        String motif = intent.getStringExtra(
                AppKeys.key(matricule, "MOTIF")
        );

        // N7asbou l’âge temporel.
        int ageTemporel = destination - naissance;

        // Na3rdhou les informations.
        tvNom.setText("VOYAGEUR : " + nomVoyageur);
        tvAge.setText("AGE TEMPOREL : " + ageTemporel + " ans");
        tvMotif.setText("MOTIF : " + motif);

        // Nethabtou ken fama paradoxe temporel wala lé
        boolean paradoxe = motif != null
                && motif.equals("Modification du passé")
                && estPremier(Math.abs(ageTemporel));

        // Na3mlou désactiver lel bouton Approuver ken el condition tet7a9a9
        if (paradoxe) {

            btnApprouver.setEnabled(false);
            btnApprouver.setAlpha(0.4f);

            tvStatut.setText("RISQUE DE PARADOXE TEMPOREL !");
            tvStatut.setTextColor(0xFFFF4444);

        } else {

            btnApprouver.setEnabled(true);
            btnApprouver.setAlpha(1f);

            tvStatut.setText("PROFIL VERIFIE");
            tvStatut.setTextColor(0xFF00E676);
        }

        // Bouton Approuver
        btnApprouver.setOnClickListener(v -> {
            envoyerVerdict(true);
        });

        // Bouton Refuser
        btnRefuser.setOnClickListener(v -> {
            envoyerVerdict(false);
        });
    }

    // Nab3thou el résultat lel activité 3
    private void envoyerVerdict(boolean verdict) {

        Intent intent = new Intent(
                VerificationActivity.this,
                ResultActivity.class
        );

        String matricule = AppKeys.MATRICULE;

        intent.putExtra(
                AppKeys.key(matricule, "VERDICT"),
                verdict
        );

        intent.putExtra(
                AppKeys.key(matricule, "NOM_VOYAGEUR"),
                nomVoyageur
        );

        startActivity(intent);
    }
}
