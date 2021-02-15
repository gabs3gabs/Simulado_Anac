package com.example.simuladoanac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.example.simuladoanac.questoes.QuestoesActivity;
import com.example.simuladoanac.questoes.QuestoesMetActivity;
import com.example.simuladoanac.questoes.QuestoesModel;
import com.example.simuladoanac.questoes.QuestoesNavActivity;
import com.example.simuladoanac.questoes.QuestoesRegActivity;
import com.example.simuladoanac.questoes.QuestoesTecActivity;
import com.example.simuladoanac.questoes.QuestoesTeoActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends QuestoesActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private RecyclerView rv;
    public List<QuestoesModel> list;
    public Button btnNav, btnMet, btnTV, btnRTA, btnCT, btnAll;
    private ImageView imgVoltar;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAll = findViewById(R.id.btnAll);
        btnCT = findViewById(R.id.btnCT);
        btnMet = findViewById(R.id.btnMet);
        btnNav = findViewById(R.id.btnNav);
        btnRTA = findViewById(R.id.btnRTA);
        btnTV = findViewById(R.id.btnTV);
        imgVoltar = findViewById(R.id.imgVoltar);

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.retangulo_branco_background));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);

        imgVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, QuestoesNavActivity.class);
                startActivity(intent);
            }
        });

        btnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, QuestoesTeoActivity.class);
                startActivity(intent);
            }
        });

        btnRTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, QuestoesRegActivity.class);
                startActivity(intent);
            }
        });

        btnMet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, QuestoesMetActivity.class);
                startActivity(intent);
            }
        });

        btnCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, QuestoesTecActivity.class);
                startActivity(intent);
            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, QuestoesActivity.class);
                startActivity(intent);
            }
        });


    }
}