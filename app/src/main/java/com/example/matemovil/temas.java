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
import com.example.matemovil.databinding.ActivityTemasBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class temas extends AppCompatActivity {
    ActivityTemasBinding binding;
    private TextView user;
    public static ArrayList<Tema> listaTemas;
    private RequestQueue rq;
    private RecyclerView lst1;
    String id,nombre,foto,grado;
    private AdaptadorUsuario adaptadorUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temas);
        String userEmail = getIntent().getStringExtra("userEmail");

        binding = ActivityTemasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user=findViewById(R.id.usuario);
        user.setText(userEmail);

        listaTemas=new ArrayList<>();
        rq= Volley.newRequestQueue(this);

        cargarPersona();
        lst1 = binding.lst1;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lst1.setLayoutManager(linearLayoutManager);
        adaptadorUsuario = new AdaptadorUsuario();
        lst1.setAdapter(adaptadorUsuario);


    }
    private void cargarPersona(){
        String userEmail = getIntent().getStringExtra("userEmail");
        String url = "http://192.168.18.4:80/Matemovil/Temas/mostrar_.php?userEmail=" + userEmail;
        JsonObjectRequest requerimiento=new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("temas", "Respuesta JSON: " + response.toString());
                        try {
                            String valor = response.get("datos").toString();
                            JSONArray arreglo = new JSONArray(valor);
                            JSONArray jsonArray = response.getJSONArray("datos");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject objeto = jsonArray.getJSONObject(i);
                                id = objeto.getString("id");
                                nombre = objeto.getString("nombre");
                                foto = objeto.getString("urlimagen");
                                grado = objeto.getString("grado");

                                Tema tema = new Tema(id, nombre, foto, grado);
                                listaTemas.add(tema);
                                adaptadorUsuario.notifyItemInserted(listaTemas.size() - 1);
                            }
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
        public AdaptadorUsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorUsuarioHolder(getLayoutInflater().inflate(R.layout.item_list,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorUsuarioHolder holder, int position) {
            holder.imprimir(position);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String temaId = listaTemas.get(position).getId();
                    String Tema=listaTemas.get(position).getNombre();
                    Intent intent = new Intent(getApplicationContext(), Ejercicios.class);
                    intent.putExtra("idTema", temaId);
                    intent.putExtra("Tema",Tema);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return listaTemas.size();
        }


        class AdaptadorUsuarioHolder extends RecyclerView.ViewHolder {
            TextView tvNombre;
            ImageView ivFoto;
            public CardView cardView;
            public AdaptadorUsuarioHolder(@NonNull View itemView) {
                super(itemView);
                tvNombre=itemView.findViewById(R.id.nombre);
                ivFoto=itemView.findViewById(R.id.listImage);
                cardView=itemView.findViewById(R.id.main_container);
            }

            public void imprimir(int position) {
                tvNombre.setText(""+listaTemas.get(position).getNombre());
                recuperarImagen(listaTemas.get(position).getUrlImagen(),ivFoto);
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