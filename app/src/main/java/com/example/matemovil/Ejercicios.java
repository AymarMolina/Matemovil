package com.example.matemovil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.matemovil.databinding.ActivityEjerciciosBinding;
import com.example.matemovil.databinding.ActivityTemasBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Ejercicios extends AppCompatActivity {
    ActivityEjerciciosBinding binding;
    private TextView user;
    public static ArrayList<Ejercicio> listaEjercicios;
    private RequestQueue rq;
    private RecyclerView lst1;
    String id, enunciado, correcta, foto, tema;
    private AdaptadorUsuario adaptadorUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityEjerciciosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = findViewById(R.id.usuario);

        String idTema = getIntent().getStringExtra("idTema");
        String Tema=getIntent().getStringExtra("Tema");
        user.setText(Tema);

        listaEjercicios = new ArrayList<>();
        rq = Volley.newRequestQueue(this);

        lst1 = binding.lst1;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lst1.setLayoutManager(linearLayoutManager);
        adaptadorUsuario = new AdaptadorUsuario();
        lst1.setAdapter(adaptadorUsuario);

        cargarPersona();
    }
    private void cargarPersona(){
        String idTema = getIntent().getStringExtra("idTema");
        String url = "http://192.168.18.4:80/Matemovil/Ejercicios/mostrar_.php?idTema=" + idTema;

        JsonObjectRequest requerimiento = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("temas", "Respuesta JSON: " + response.toString());
                        try {
                            JSONArray jsonArray = response.getJSONArray("datos");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject objeto = jsonArray.getJSONObject(i);
                                id = objeto.getString("id");
                                enunciado = objeto.getString("enunciado");
                                correcta = objeto.getString("correcta");
                                foto = objeto.getString("urlimagen");

                                Ejercicio eje = new Ejercicio(id, enunciado, foto, correcta);
                                listaEjercicios.add(eje);
                            }
                            adaptadorUsuario.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("temas", "Error en la solicitud HTTP: " + error.toString());
                        error.printStackTrace();
                    }
                });
        rq.add(requerimiento);
    }
    private class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.AdaptadorUsuarioHolder>{
        private OnItemClickListener listener;
        @NonNull
        @Override
        public AdaptadorUsuario.AdaptadorUsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorUsuario.AdaptadorUsuarioHolder(getLayoutInflater().inflate(R.layout.ejercicio_item,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorUsuario.AdaptadorUsuarioHolder holder, int position) {
            holder.imprimir(position);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String temaId = listaEjercicios.get(position).getId();
                    Intent intent = new Intent(getApplicationContext(), Ejercicios.class);
                    intent.putExtra("idTema", temaId);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return listaEjercicios.size();
        }


        class AdaptadorUsuarioHolder extends RecyclerView.ViewHolder {
            TextView  tvEnunciado,tvCorrecta;
            ImageView ivFoto;
            public CardView cardView;
            public AdaptadorUsuarioHolder(@NonNull View itemView) {
                super(itemView);
                tvEnunciado=itemView.findViewById(R.id.enunciado);
                ivFoto=itemView.findViewById(R.id.listImage);
                tvCorrecta=itemView.findViewById(R.id.correcta);
                cardView=itemView.findViewById(R.id.main_container);
            }

            public void imprimir(int position) {
                tvEnunciado.setText(""+listaEjercicios.get(position).getEnunciado());
                tvCorrecta.setText(""+listaEjercicios.get(position).getCorrecta());
                recuperarImagen(listaEjercicios.get(position).getUrlImagen(),ivFoto);
            }

            public void recuperarImagen(String foto,ImageView iv)
            {
                ImageRequest peticion=new ImageRequest(foto,
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                iv.setImageBitmap(response);
                            }
                        },
                        0,
                        0,
                        null,
                        null,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                rq.add(peticion);
            }

        }
    }
}