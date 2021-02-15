package com.example.simuladoanac.registro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.simuladoanac.R;
import com.example.simuladoanac.MainActivity;
import com.example.simuladoanac.R;
import com.example.simuladosanac.registro.adapter.Usuarios;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class CriarContaActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnVoltar, btnCriarConta;
    private EditText editNomeC, editEmailC, editSenhaC, editAero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        mAuth = FirebaseAuth.getInstance();
        editAero = findViewById(R.id.editAero);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnCriarConta = findViewById(R.id.btnCriarConta);
        editNomeC = findViewById(R.id.editNomeC);
        editSenhaC = findViewById(R.id.editSenhaC);
        editEmailC = findViewById(R.id.editEmailC);



        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CriarContaActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnCriarConta.setOnClickListener(this::onClick);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnCriarConta:
                validar();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                CriarContaActivity.this.finish();

                break;
        }
    }

    private void validar(){

        String nome = editNomeC.getText().toString().trim();
        String escola = editAero.getText().toString().trim();
        String email = editEmailC.getText().toString().trim();
        String senha = editSenhaC.getText().toString().trim();

        if (nome.isEmpty()){
            editNomeC.setError("Digite seu Nome!");
            editNomeC.requestFocus();
            return;
        }

        if (escola.isEmpty()){
            editAero.setError("Digite a escola de aviação onde estuda ou pretende!");
            editAero.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editEmailC.setError("Digite seu E-mail!");
            editEmailC.requestFocus();
            return;
        }

        if (senha.isEmpty()){
            editSenhaC.setError("Digite sua Senha!");
            editSenhaC.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmailC.setError("Por favor digite um E-mail valido!");
            editEmailC.requestFocus();
            return;
        }

        if (senha.length() < 6){
            editSenhaC.setError("Sua senha deve conter no minimo 6 caracteres!");
            editSenhaC.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Usuarios usuarios = new Usuarios(nome, escola, email);

                            FirebaseDatabase.getInstance().getReference("usuarios")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(usuarios).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(CriarContaActivity.this, "Usuario cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(CriarContaActivity.this, "Tente novamente!", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                        }else{
                            Toast.makeText(CriarContaActivity.this, "Falha!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}