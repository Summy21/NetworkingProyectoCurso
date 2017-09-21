package com.example.summy.starwarsinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.summy.starwarsinfo.Swapi.SwapiService;
import com.example.summy.starwarsinfo.models.Actores;
import com.example.summy.starwarsinfo.models.ActoresRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SUMMY
 */

public class ActoresActivity extends AppCompatActivity {

    private static final String TAG = "ACTORES";
    private Retrofit retrofit;

    private RecyclerView actoresRecyclerView;

    private ListaActoresAdapter listaActoresAdapter;

    private int page;
    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actores);

        actoresRecyclerView = (RecyclerView) findViewById(R.id.actoresRecyclerView);
        listaActoresAdapter = new ListaActoresAdapter(this);
        actoresRecyclerView.setAdapter(listaActoresAdapter);
        actoresRecyclerView.setHasFixedSize(true);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        actoresRecyclerView.setLayoutManager(layoutManager);

        actoresRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 1){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItem = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar){
                        if ((visibleItemCount + pastVisibleItem) >= totalItemCount) {
                            Log.i(TAG, "Llegamos al final.");
                            aptoParaCargar = false;

                            page+=1;
                            obtenerDatos(page);
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aptoParaCargar = true;
        page = 1;
        obtenerDatos(page);

    }

    private void obtenerDatos(int page) {
        SwapiService service = retrofit.create(SwapiService.class);
        Call<ActoresRespuesta> actoresRespuestaCall = service.obtenerListaActores(page);

        actoresRespuestaCall.enqueue(new Callback<ActoresRespuesta>() {
            @Override
            public void onResponse(Call<ActoresRespuesta> call, Response<ActoresRespuesta> response) {
                aptoParaCargar = true;
                if (response.isSuccessful()) {

                    ActoresRespuesta actoresRespuesta = response.body();
                    ArrayList<Actores> listaActores = actoresRespuesta.getResults();

                    listaActoresAdapter.adicionarListaActores(listaActores);

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ActoresRespuesta> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });

    }
}
