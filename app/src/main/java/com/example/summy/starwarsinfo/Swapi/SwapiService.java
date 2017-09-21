package com.example.summy.starwarsinfo.Swapi;

import com.example.summy.starwarsinfo.models.ActoresRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SwapiService {

    @GET("people")
    Call<ActoresRespuesta> obtenerListaActores(@Query("page") int page);

}
