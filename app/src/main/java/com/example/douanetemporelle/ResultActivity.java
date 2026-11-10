
package com.example.douanetemporelle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends Activity {

    private LinearLayout layoutResultat;
    private TextView tvResultat;
    private Button btnNouveau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Rabet  les éléments mta3 l’interface.
        layoutResultat = findViewById(R.id.layout_resultat_nj);
        tvResultat = findViewById(R.id.tv_resultat_nj);
        btnNouveau = findViewById(R.id.btn_nouveau_nj);

        // Nesta9blou el résultat w esm el voyageur.
        String matricule = AppKeys.MATRICULE;

        Intent intent = getIntent();

        boolean verdict = intent.getBooleanExtra(
                AppKeys.key(matricule, "VERDICT"),
                false
        );

        String nom = intent.getStringExtra(
                AppKeys.key(matricule, "NOM_VOYAGEUR")
        );

        if (nom == null) {
            nom = "Voyageur";
        }

        // Nbadlou el couleur w el message 7asb el résultat
        if (verdict) {

            layoutResultat.setBackgroundColor(
                    Color.parseColor("#087F46")
            );

            tvResultat.setText(
                    "✓ BON VOYAGE " + nom +
                            "\n\nVotre saut temporel est approuvé !"
            );

        } else {

            layoutResultat.setBackgroundColor(
                    Color.parseColor("#B71C1C")
            );

            tvResultat.setText(
                    "✖ " + nom +
                            " est en état d'arrestation temporelle !"
            );
        }

        // Narj3ou lel activité 1 w nsakrou les activités eli 9bal
        btnNouveau.setOnClickListener(v -> {

            Intent retour = new Intent(
                    ResultActivity.this,
                    MainActivity.class
            );

            retour.addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(retour);
            finish();
        });
    }
}
