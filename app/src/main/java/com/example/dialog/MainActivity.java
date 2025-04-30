package com.example.dialog;

import android.os.Bundle;
import android.widget.*;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int licznik = 0;
    private TextView counterTextView;
    private Button resetButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.licznik);
        resetButton = findViewById(R.id.btn1);
        deleteButton = findViewById(R.id.btn2);

        if (savedInstanceState != null) {
            licznik  = savedInstanceState.getInt("licznik");
            updateCounterText();
        }

        resetButton.setOnClickListener(v -> {
            licznik = 0;
            updateCounterText();
            Toast.makeText(this, "Licznik został zresetowany", Toast.LENGTH_SHORT).show();
        });

        deleteButton.setOnClickListener(v -> showConfirmationDialog());
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Potwierdzenie")
                .setMessage("Czy na pewno chcesz usunąć dane?")
                .setPositiveButton("Tak", (dialog, which) -> {
                    licznik++;
                    updateCounterText();
                    Toast.makeText(this, "Dane zostały usunięte", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Nie", null)
                .show();
    }

    private void updateCounterText() {
        String message = "Dane usunięto: " + licznik + (licznik == 1 ? " raz" : " razy");
        counterTextView.setText(message);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putint("licznik", licznik);
    }
}
