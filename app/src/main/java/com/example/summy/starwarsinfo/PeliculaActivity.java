package com.example.summy.starwarsinfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by SUMMY.
 */

public class PeliculaActivity extends AppCompatActivity {

    private ImageView fotoImageView;
    private TextView tituloTextView;
    private TextView resumenTextView;
    private TextView gestionTextView;

    private String nombre;
    private String gestion;
    private String resumen;
    private String foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);

        fotoImageView = (ImageView) findViewById(R.id.fotoImageView);
        tituloTextView = (TextView) findViewById(R.id.tituloTextView);
        resumenTextView = (TextView) findViewById(R.id.resumenTextView);
        gestionTextView = (TextView) findViewById(R.id.gestionTextView);


        nombre = getIntent().getStringExtra("nombre");
        gestion = getIntent().getStringExtra("gestion");
        resumen = getIntent().getStringExtra("resumen");
        foto = getIntent().getStringExtra("foto");

        Glide.with(this)
                .load(foto)
                .into(fotoImageView);

        tituloTextView.setText(nombre);
        gestionTextView.setText(gestion);
        resumenTextView.setText(resumen);


    }

    public void actores(View view) {
        Intent intent = new Intent(this, ActoresActivity.class);
        this.startActivity(intent);;
    }
}
