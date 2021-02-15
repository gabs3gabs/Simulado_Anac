package com.example.simuladoanac.resultado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.simuladoanac.MainActivity;
import com.example.simuladoanac.R;
import com.example.simuladoanac.questoes.QuestoesModel;

import java.util.ArrayList;
import java.util.List;

public class PontuacaoActivity extends AppCompatActivity {

    private TextView pontuacao, total;
    private Button btnfinal;
    private Dialog loadingDialog;
    private RecyclerView questoesErradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontuacao);

        pontuacao = findViewById(R.id.pontuacao);
        btnfinal = findViewById(R.id.btnFinal);
        total = findViewById(R.id.total);
        questoesErradas = findViewById(R.id.questoesErradas);

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.retangulo_branco_background));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);

        pontuacao.setText(String.valueOf(getIntent().getIntExtra("pontuacao", 0)));
        pontuacao.setText(String.valueOf(getIntent().getIntExtra("pontuacao", 0)));
        total.setText(String.valueOf(getIntent().getIntExtra("total", 0)));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        questoesErradas.setLayoutManager(layoutManager);

        List<QuestoesModel> list = new ArrayList<>();
        list.add(new QuestoesModel(
                "",
                "",
                "",
                "",
                "",
                ""));

        ErradasAdapter adapter = new ErradasAdapter(list);
        questoesErradas.setAdapter(adapter);


        btnfinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaPrincipal = new Intent(PontuacaoActivity.this, MainActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        });

    }
}