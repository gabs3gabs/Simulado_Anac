package com.example.simuladoanac.registro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simuladoanac.R;
import com.example.simuladoanac.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarActivity extends AppCompatActivity {

    private Button btnRecuperarBtn;
    private FirebaseAuth mAuth;
    private EditText editRecuperarEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        mAuth = FirebaseAuth.getInstance();
        editRecuperarEmail = findViewById(R.id.editRecuperarEmail);
        btnRecuperarBtn = findViewById(R.id.btnRecuperarBtn);

        btnRecuperarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redefinurSenha();
            }
        });

    }

    private void redefinurSenha(){
        String email = editRecuperarEmail.getText().toString().trim();

        if (email.isEmpty()){
            editRecuperarEmail.setError("Digite seu Email!");
            editRecuperarEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editRecuperarEmail.setError("Digite um Email valido!");
            editRecuperarEmail.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(RecuperarActivity.this, "Email de recuperação enviado com sucesso!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RecuperarActivity.this, "Ocorreu um erro!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}