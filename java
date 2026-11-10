
package com.example.losowekostki;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLiczbaKostek;
    private Button buttonLosuje;
    private Button buttonRosnaco;
    private Button buttonMalejaca;
    private GridLayout gridKostki;
    private TextView textViewWyniki;

    private final List<Integer> wynik = new ArrayList<>();
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLiczbaKostek = findViewById(R.id.ileKosci);
        buttonLosuje = findViewById(R.id.btnRoll);
        buttonRosnaco = findViewById(R.id.btnAscending);
        buttonMalejaca = findViewById(R.id.btnDescending);
        gridKostki = findViewById(R.id.kostkiTu);
        textViewWyniki = findViewById(R.id.txtScores);

        buttonLosuje.setOnClickListener(v -> losuj());

        buttonRosnaco.setOnClickListener(v -> {
            if (wynik.isEmpty()) {
                Toast.makeText(this, "Najpierw wylosuj", Toast.LENGTH_SHORT).show();
            } else {
                sortujRosnaco();
                odswiezWidok();
            }
        });

        buttonMalejaca.setOnClickListener(v -> {
            if (wynik.isEmpty()) {
                Toast.makeText(this, "Najpierw wylosuj", Toast.LENGTH_SHORT).show();
            } else {
                sortujMalejaco();
                odswiezWidok();
            }
        });
    }

    private void losuj() {
        int liczba;

        try {
            liczba = Integer.parseInt(
                    editTextLiczbaKostek.getText().toString().trim()
            );
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Podaj poprawną liczbę kości", Toast.LENGTH_SHORT).show();
            return;
        }

        if (liczba <= 0) {
            Toast.makeText(this, "Podaj poprawną liczbę", Toast.LENGTH_SHORT).show();
            return;
        }

        if (liczba > 100) {
            Toast.makeText(this, "Liczba powinna być mniejsza od 100", Toast.LENGTH_SHORT).show();
            return;
        }

        wynik.clear();

        for (int i = 0; i < liczba; i++) {
            wynik.add(random.nextInt(6) + 1);
        }

        odswiezWidok();
    }

    private void sortujRosnaco() {
        int x;

        for (int i = 0; i < wynik.size() - 1; i++) {
            for (int j = 0; j < wynik.size() - 1; j++) {
                if (wynik.get(j) > wynik.get(j + 1)) {
                    x = wynik.get(j);
                    wynik.set(j, wynik.get(j + 1));
                    wynik.set(j + 1, x);
                }
            }
        }
    }

    private void sortujMalejaco() {
        int x;

        for (int i = 0; i < wynik.size() - 1; i++) {
            for (int j = 0; j < wynik.size() - 1; j++) {
                if (wynik.get(j) < wynik.get(j + 1)) {
                    x = wynik.get(j);
                    wynik.set(j, wynik.get(j + 1));
                    wynik.set(j + 1, x);
                }
            }
        }
    }

    private void odswiezWidok() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < wynik.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }

            sb.append(wynik.get(i));
        }

        textViewWyniki.setText(sb.toString());

        gridKostki.removeAllViews();

        int kolumny = 5;
        gridKostki.setColumnCount(kolumny);

        int rozmiar = (int) (56 * getResources().getDisplayMetrics().density);

        for (int wartosc : wynik) {
            TextView kostka = new TextView(this);

            kostka.setText(String.valueOf(wartosc));
            kostka.setTextSize(24f);
            kostka.setGravity(Gravity.CENTER);
            kostka.setTextColor(0xFF000000);
            kostka.setBackgroundColor(0xFFE0E0E0);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();

            params.width = rozmiar;
            params.height = rozmiar;

            kostka.setLayoutParams(params);

            gridKostki.addView(kostka);
        }
    }
}
