package com.example.matemovil;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.papercut_lp_intro);
        mediaPlayer.start();

        // Para eliminar la barra de estado (barra de notificaciones)
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rotarImagen();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mediaPlayer.stop();
                mediaPlayer.release();
                mostrarIniciar();
            }
        }, 5800);//4 segundos
    }
    private void rotarImagen() {
        image = findViewById(R.id.imageView);
        RotateAnimation animation = new RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(4000); // Tiempo en rotar imagen
        animation.setRepeatCount(Animation.ABSOLUTE);
        animation.setRepeatMode(Animation.REVERSE);
        image.startAnimation(animation);

    }

    private void mostrarIniciar() {
        Intent intent = new Intent(Splash.this, MainActivity.class);
        startActivity(intent);
        finish(); // Para cerrar este activity
    }
}