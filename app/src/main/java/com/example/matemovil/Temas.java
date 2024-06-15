package com.example.matemovil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Temas extends AppCompatActivity {
   /* ActivityListarBinding binding;

    ImageButton boton_imagen;
    public static ArrayList<Tema> listaTemas;
    private RequestQueue rq;
    private RecyclerView lst1;
    private AdaptadorUsuario adaptadorTema;
    String id , nombre, foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temas);
        binding = ActivityListarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        boton_imagen=findViewById(R.id.imageButton);
        lst1=findViewById(R.id.lst1);

        boton_imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Temas.this, "Registrar Datos", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        listaTemas=new ArrayList<>();
        rq= Volley.newRequestQueue(this);

        cargarPersona();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        lst1.setLayoutManager(linearLayoutManager);
        adaptadorTema=new AdaptadorUsuario();
        lst1.setAdapter(adaptadorTema);

    }
    private void cargarPersona() {
        String url="http://192.168.18.137:80/Matemovil/Temas/mostrar_.php";
        JsonObjectRequest requerimiento=new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String valor=response.get("datos").toString();
                            JSONArray arreglo=new JSONArray(valor);
                            JSONArray jsonArray =response.getJSONArray("datos");

                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject objeto = new JSONObject(arreglo.get(i).toString());
                                id = objeto.getString("id");
                                nombre = objeto.getString("nombre");
                                foto = objeto.getString("urlimagen");


                                Tema usuario = new Tema(id,nombre, foto);
                                listaTemas.add(usuario);
                                adaptadorTema.notifyItemRangeInserted(listaTemas.size(), i);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        rq.add(requerimiento);
    }
    private class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.AdaptadorUsuarioHolder>{

        @NonNull
        @Override
        public AdaptadorUsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorUsuarioHolder(getLayoutInflater().inflate(R.layout.item_lst,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorUsuarioHolder holder, int position) {
            holder.imprimir(position);
        }

        @Override
        public int getItemCount() {
            return listaTemas.size();
        }

        class AdaptadorUsuarioHolder extends RecyclerView.ViewHolder {
            TextView tvNombre;
            ImageView ivFoto;
            public AdaptadorUsuarioHolder(@NonNull View itemView) {
                super(itemView);
                tvNombre=itemView.findViewById(R.id.tema);
                ivFoto=itemView.findViewById(R.id.listImage);
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
    }*/
}