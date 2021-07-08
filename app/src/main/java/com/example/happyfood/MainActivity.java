package com.example.happyfood;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail        = findViewById(R.id.EmailLogin);
        mPassword     = findViewById(R.id.PassLogin);
        progressBar   = findViewById(R.id.progressBar2);
        mLoginBtn     = findViewById(R.id.loginBtn);
        mCreateBtn    = findViewById(R.id.clicHere2);

        fAuth         = FirebaseAuth.getInstance();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("El correo electrónico es requerido");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Se requiere una contraseña");
                    return;
                }
                if (password.length() < 6){
                    mPassword.setError("La contraseña debe ser mayor a 6 digitos");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                // Authenticate the user

                fAuth.signInWithEmailAndPassword(email,password) .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Ingreso exitoso", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Inicio.class));

                        }else {
                            Toast.makeText(MainActivity.this,"Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Registrar.class));
            }
        });
    }
}