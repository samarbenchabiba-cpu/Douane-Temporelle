package com.example.douanetemporelle;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText nom, naissance, destination;
    private RadioGroup motifs;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ربط عناصر الواجهة
        nom = findViewById(R.id.SC_et_nom_nj);
        naissance = findViewById(R.id.SC_et_naissance_nj);
        destination = findViewById(R.id.SC_et_destination_nj);
        motifs = findViewById(R.id.SC_rg_motif_nj);
        btnInspecter = findViewById(R.id.SC_btn_inspecter_nj);

        btnInspecter.setOnClickListener(v -> {

            // قراءة المعلومات المدخلة
            String nomVoyageur = nom.getText().toString().trim();
            String anneeNaissance = naissance.getText().toString().trim();
            String anneeDestination = destination.getText().toString().trim();

            // التحقق من الحقول
            if (nomVoyageur.isEmpty() ||
                    anneeNaissance.isEmpty() ||
                    anneeDestination.isEmpty()) {

                Toast.makeText(this,
                        "Veuillez remplir tous les champs",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // التحقق من اختيار الدافع
            int selectedId = motifs.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(this,
                        "Veuillez choisir un motif",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            int naissanceAnnee;
            int destinationAnnee;

            try {
                naissanceAnnee = Integer.parseInt(anneeNaissance);
                destinationAnnee = Integer.parseInt(anneeDestination);
            } catch (NumberFormatException e) {
                Toast.makeText(this,
                        "Année invalide",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // إنشاء نص الدافع
            String motif = "";

            if (selectedId == R.id.SC_rb_tourisme_nj) {
                motif = "Tourisme";
            } else if (selectedId == R.id.SC_rb_passe_nj) {
                motif = "Modification du passé";
            } else if (selectedId == R.id.SC_rb_fuite_nj) {
                motif = "Fuite";
            }

            // إنشاء Intent صريح نحو النشاط الثاني
            Intent intent = new Intent(
                    MainActivity.this,
                    VerificationActivity.class
            );

            // إرسال المعلومات باستعمال مفاتيح ديناميكية
            String matricule = AppKeys.MATRICULE;

            intent.putExtra(
                    AppKeys.key(matricule, "NOM_VOYAGEUR"),
                    nomVoyageur
            );

            intent.putExtra(
                    AppKeys.key(matricule, "ANNEE_NAISSANCE"),
                    naissanceAnnee
            );

            intent.putExtra(
                    AppKeys.key(matricule, "ANNEE_DESTINATION"),
                    destinationAnnee
            );

            intent.putExtra(
                    AppKeys.key(matricule, "MOTIF"),
                    motif
            );

            startActivity(intent);
        });
    }
}
