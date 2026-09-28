package com.ksawery.gebicz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText wpisImie, wpisNazwisko, wpisEmail, wpisHaslo;
    private Button przyciskWyślij;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        wpisImie = findViewById(R.id.imie);
        wpisNazwisko = findViewById(R.id.nazwisko);
        wpisEmail = findViewById(R.id.email);
        wpisHaslo = findViewById(R.id.haslo);
        przyciskWyślij = findViewById(R.id.przeslij);

        przyciskWyślij.setOnClickListener(v -> {
            String txtImie = wpisImie.getText().toString();
            String txtNazwisko = wpisNazwisko.getText().toString();
            String txtEmail = wpisEmail.getText().toString();
            String txtHaslo = wpisHaslo.getText().toString();

            if (czyTekstWpisany(txtImie) && czyTekstWpisany(txtNazwisko) && czyEmailPrawny(txtEmail) && czyHasloDobre(txtHaslo)) {
                Toast.makeText(this, "Formularz wysłany poprawnie!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Niepoprawne dane w formularzu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean czyTekstWpisany(String tekst) {
        return !tekst.isEmpty();
    }

    public boolean czyEmailPrawny(String tekst) {
        return tekst.contains("@") && tekst.contains(".");
    }

    public boolean czyHasloDobre(String tekst) {
        if (tekst.length() < 8) return false;
        if (!tekst.matches(".*[a-z].*")) return false;
        if (!tekst.matches(".*[A-Z].*")) return false;
        if (!tekst.matches(".*[0-9].*")) return false;
        return true;
    }
}