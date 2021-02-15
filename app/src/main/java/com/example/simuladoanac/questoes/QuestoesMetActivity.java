package com.example.simuladoanac.questoes;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.simuladoanac.R;
import com.example.simuladoanac.resultado.PontuacaoActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class QuestoesMetActivity extends AppCompatActivity {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        private TextView txtQuestao, numeroQuestao;
        private LinearLayout opcaoQuestao;
        private Button btnProximo;
        private int contagem = 0;
        public List<QuestoesModel> list;
        private int posicao = 0;
        private int pontuacao = 0;
        private String categoria;
        private int setNumero;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questoes_met);

        txtQuestao = findViewById(R.id.txtQuestao);
        btnProximo = findViewById(R.id.btnProximo);
        opcaoQuestao = findViewById(R.id.opcaoQuestao);
        numeroQuestao = findViewById(R.id.numeroQuestao);

        categoria = getIntent().getStringExtra("categoria");
        setNumero = getIntent().getIntExtra("setNumero", 1);

        list = new ArrayList<>();

        questaoNav();

        for (int i = 0; i < 4; i++){
            opcaoQuestao.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    conferirRespostas((Button) v);
                }
            });
        }

        playAnim(txtQuestao, 0, list.get(posicao).getQuestao());
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnProximo.setEnabled(false);
                btnProximo.setAlpha(0.7f);
                habilitarOpcao(true);
                posicao++;
                if (posicao == list.size()){
                    Intent pontosInt = new Intent(QuestoesMetActivity.this, PontuacaoActivity.class);
                    pontosInt.putExtra("pontuacao", pontuacao);
                    pontosInt.putExtra("total", list.size());
                    startActivity(pontosInt);
                    finish();
                    return;
                }
                contagem = 0;
                playAnim(txtQuestao, 0, list.get(posicao).getQuestao());
            }
        });

    }

        private void playAnim(View view, int value, String data){

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (value == 0 && contagem < 4){
                    String opcao = "";

                    //Ordem que as opções vão aparecer

                    if (contagem == 0){
                        opcao = list.get(posicao).getOpcaoA();
                    }else if (contagem == 1){
                        opcao = list.get(posicao).getOpcaoB();
                    }else if (contagem == 2){
                        opcao = list.get(posicao).getOpcaoC();
                    }else if (contagem == 3){
                        opcao = list.get(posicao).getOpcaoD();
                    }
                    playAnim(opcaoQuestao.getChildAt(contagem), 0, opcao);
                    contagem ++;

                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (value == 0){
                    try {
                        ((TextView)view).setText(data);
                        numeroQuestao.setText(posicao + 1 + "/" + list.size());
                    }catch (ClassCastException ex){
                        ((Button)view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view, 1, data);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

        private void conferirRespostas(Button selecionarOpcao){
        habilitarOpcao(true);
        btnProximo.setEnabled(true);
        btnProximo.setAlpha(1);
        if (selecionarOpcao.getText().toString().equals(list.get(posicao).getRespostaCorreta())){
            pontuacao++;
            selecionarOpcao.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));

        }else{
            selecionarOpcao.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
            Button respostaCorreta = (Button) opcaoQuestao.findViewWithTag(list.get(posicao).getRespostaCorreta());
        }
    }

        private void habilitarOpcao(boolean habilitar){
        for (int i = 0; i < 4; i++){
            opcaoQuestao.getChildAt(i).setEnabled(habilitar);
            if (habilitar){
                opcaoQuestao.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#7EC6C6C6")));
            }
        }
    }

        public void questaoNav() {

        list.add(new QuestoesModel(
                "2",
                "a",
                "b",
                "c",
                "d",
                "b"
        ));
        list.add(new QuestoesModel(
                "",
                "",
                "",
                "",
                "",
                ""
        ));
        list.add(new QuestoesModel(
                "",
                "",
                "",
                "",
                "",
                ""
        ));
        list.add(new QuestoesModel(
                "",
                "",
                "",
                "",
                "",
                ""
        ));
        list.add(new QuestoesModel(
                "",
                "",
                "",
                "",
                "",
                ""
        ));
        list.add(new QuestoesModel(
                "",
                "",
                "",
                "",
                "",
                ""
        ));
    }
    }