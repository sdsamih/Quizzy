package com.example.gamescore;

import android.content.Intent;
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

        Button btnIniciar = findViewById(R.id.btn_iniciar);
        EditText txtNumQuestoes = findViewById(R.id.txtNumQuestoes);

        btnIniciar.setOnClickListener(v -> {
            String numQuestoesStr = txtNumQuestoes.getText().toString();
            Toast.makeText(this, numQuestoesStr + " Questões selecionadas", Toast.LENGTH_SHORT).show();



            Intent intentIniciar = new Intent(this, QuestoesActivity.class);
        });
    }
}