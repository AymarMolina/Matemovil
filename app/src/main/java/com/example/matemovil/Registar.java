package com.example.matemovil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registar extends AppCompatActivity {
    Button btn_registrar;
    private EditText pass,email;
    Spinner grade;
    EditText nom,age;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registar);

        mAuth = FirebaseAuth.getInstance();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pass=findViewById(R.id.txtContrasena);
        nom=findViewById(R.id.txtNombres);
        age=findViewById(R.id.txtEdad);
        grade=(Spinner) findViewById(R.id.spinnerGrado);
        email=findViewById(R.id.txtCorreo);
        btn_registrar=findViewById(R.id.btnRegistrar);



    }
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    public void redic(View v){
        Intent objint=new Intent(Registar.this, MainActivity.class);
        startActivity(objint);
        finish();
    }
    public void registrar(View view){

        try
        {
            mAuth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                    .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Alumno creado",Toast.LENGTH_SHORT).show();
                                FirebaseUser user=mAuth.getCurrentUser();
                                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);

                            }else{
                                Toast.makeText(getApplicationContext(),"Error al registrar",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            String p_nombre = nom.getText().toString();
            String p_edad = age.getText().toString();
            String p_grado = grade.getSelectedItem().toString();
            String p_correo = email.getText().toString();

            ProgressDialog progressDialog =new ProgressDialog(this);
            progressDialog.setMessage("cargando");


            if (p_nombre.isEmpty()){
                Toast.makeText(this,"Ingrese nombre",Toast.LENGTH_SHORT).show();
            }else if (p_edad.isEmpty()){
                Toast.makeText(this,"Ingrese edad",Toast.LENGTH_SHORT).show();
            }else if (p_grado.isEmpty()){
                Toast.makeText(this,"Ingrese grado",Toast.LENGTH_SHORT).show();
            }else if (p_correo.isEmpty()){
                Toast.makeText(this,"Ingrese correo",Toast.LENGTH_SHORT).show();
            }else {
                progressDialog.show();
                progressDialog.dismiss();
                StringRequest request =new StringRequest(Request.Method.POST, "http://192.168.18.137:80/Matemovil/Usuarios/insertar_.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(Registar.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registar.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
                ){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String>params=new HashMap<>();
                        params.put("nombre",p_nombre);
                        params.put("edad",p_edad);
                        params.put("grado",p_grado);
                        params.put("correo",p_correo);

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(Registar.this);
                requestQueue.add(request);
            }
            Toast.makeText(this,"Alumno registrado",Toast.LENGTH_SHORT).show();
            pass.setText("");
            email.setText("");
            nom.setText("");
            age.setText("");
            nom.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Verificar datos a ingresar",Toast.LENGTH_SHORT).show();
        }
    }
    public void insert()
    {

    }
}