package com.example.summy.starwarsinfo.models;

import java.util.List;

public class Saga {

    private List<Episodio> episodios;


    public Saga(List<Episodio> episodios) {
        this.episodios = episodios;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }
}
