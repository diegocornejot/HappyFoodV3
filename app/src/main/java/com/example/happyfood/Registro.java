package com.example.happyfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Registro extends AppCompatActivity {
    private EditText EditTextNombre;
    private EditText EditTextEmail;
    private EditText EditTextContra;
    private Button ButtonRegistrar;

    //Variables de datos a registrar:
    private String nombre="";
    private String email="";
    private String contra="";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        EditTextNombre= (EditText)findViewById(R.id.Nombre);
        EditTextEmail= (EditText)findViewById(R.id.Email);
        EditTextContra= (EditText)findViewById(R.id.Contra);
        ButtonRegistrar=(Button)findViewById(R.id.Registrar);


        ButtonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre=EditTextNombre.getText().toString();
                email=EditTextEmail.getText().toString();
                contra=EditTextContra.getText().toString();

                mAuth= FirebaseAuth.getInstance();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                if (!nombre.isEmpty() && email.isEmpty() && contra.isEmpty()){

                    if(contra.length() >= 6){
                        registerUser();

                    }
                    else{
                        Toast.makeText(Registro.this, "La contraseña debe tener 6 caracteres", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(Registro.this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(email, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Map<String, Object> map= new HashMap<>();
                    map.put("name",nombre);
                    map.put("email",email);
                    map.put("contra",contra);
                    String id= mAuth.getCurrentUser().getUid();

                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(Registro.this, Inicio.class));
                                finish();
                            }
                            else {
                                Toast.makeText(Registro.this, "No se puedo crear correctamente ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Registro.this, "No Se pudo Registrar al usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}