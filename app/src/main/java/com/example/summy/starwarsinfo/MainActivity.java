package com.example.summy.starwarsinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.summy.starwarsinfo.adapters.EpisodiosAdapter;
import com.example.summy.starwarsinfo.models.Saga;
import com.example.summy.starwarsinfo.util.Util;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    private RecyclerView episodiosRecyclerView;
    private EpisodiosAdapter episodiosAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        episodiosRecyclerView = (RecyclerView) findViewById(R.id.episodiosRecyclerView);
        episodiosRecyclerView.setHasFixedSize(true);
        episodiosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        episodiosAdapter = new EpisodiosAdapter(this, this);
        episodiosRecyclerView.setAdapter(episodiosAdapter);

    }


    protected void onStart() {
        super.onStart();

        cargarDatos();
    }

    private void cargarDatos() {
        String json = Util.leerJSON(this);

        Saga saga = gson.fromJson(json, Saga.class);

        episodiosAdapter.setDataset(saga.getEpisodios());
    }



}
