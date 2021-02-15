package com.example.simuladoanac.registro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simuladoanac.R;
import com.example.simuladoanac.MainActivity;
import com.example.simuladoanac.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button btnEntrar, btnRecuperar, btnCriar;
    private EditText editEmail, editSenha;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        btnCriar = findViewById(R.id.btnCriar);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnRecuperar = findViewById(R.id.btnRecuperar);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RecuperarActivity.class);
                startActivity(intent);
            }
        });

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CriarContaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnEntrar.setOnClickListener(this::onClick);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnEntrar:
                entrarUsuario();
                break;
        }
    }

    private void entrarUsuario() {
        String email = editEmail.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();

        if (email.isEmpty()){
            editEmail.setError("Verifique se o Email esta correto!");
            editEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Email invalido!");
            editEmail.requestFocus();
            return;
        }

        if (senha.isEmpty()){
            editSenha.setError("Digite sua senha!");
            editSenha.requestFocus();
            return;
        }

        if (senha.length() < 6){
            editSenha.setError("Sua senha deve conter no minimo 6 caracteres!");
            editSenha.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(LoginActivity.this, "Por favor confira se os dados estão corretos!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}