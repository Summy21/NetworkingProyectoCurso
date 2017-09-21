package com.example.summy.starwarsinfo.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.summy.starwarsinfo.PeliculaActivity;
import com.example.summy.starwarsinfo.R;
import com.example.summy.starwarsinfo.models.Episodio;

import java.util.ArrayList;
import java.util.List;


public class EpisodiosAdapter extends RecyclerView.Adapter<EpisodiosAdapter.ViewHolder> {

    private List<Episodio> dataset;
    private Context context;
    private Activity activity;

    public EpisodiosAdapter(Context context, Activity activity) {
        this.context = context;
        this.dataset = new ArrayList<>();
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episodio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Episodio e = dataset.get(position);
        holder.episodioTextView.setText("Episodio " + e.getNumero());
        holder.nombreTextView.setText(e.getNombre());

        Glide.with(context)
                .load(e.getFotoUrl())
                .into(holder.fotoImageView);

        holder.layoutEpisodio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PeliculaActivity.class);
                intent.putExtra("idEpisodio",e.getId());
                intent.putExtra("nombre", e.getNombre());
                intent.putExtra("gestion", e.getGestion());
                intent.putExtra("resumen", e.getResumen());
                intent.putExtra("foto", e.getFotoUrl());


                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoImageView;
        private TextView episodioTextView;
        private TextView nombreTextView;
        private LinearLayout layoutEpisodio;

        public ViewHolder(View itemView) {
            super(itemView);

            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            episodioTextView = (TextView) itemView.findViewById(R.id.episodioTextView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            layoutEpisodio = (LinearLayout) itemView.findViewById(R.id.layoutEpisodio);
        }


    }


    public void setDataset(List<Episodio> episodios) {
        if (episodios != null) {
            dataset.addAll(episodios);
        }
        notifyDataSetChanged();
    }

}
