package com.example.summy.starwarsinfo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.summy.starwarsinfo.models.Actores;

import java.util.ArrayList;


public class ListaActoresAdapter extends RecyclerView.Adapter<ListaActoresAdapter.ViewHolder> {

    private ArrayList<Actores> dataset;
    private Context context;

    public ListaActoresAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Actores a = dataset.get(position);
        holder.nombreTextView.setText(a.getName());
        holder.cumpleTextView.setText("Fecha de nacimiento: " + a.getBirth_year());
        holder.genderTextView.setText("Genero: " + a.getGender());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaActores(ArrayList<Actores> listaActores) {
        dataset.addAll(listaActores);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombreTextView;
        private TextView cumpleTextView;
        private TextView genderTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            cumpleTextView = (TextView) itemView.findViewById(R.id.cumpleTextView);
            genderTextView = (TextView) itemView.findViewById(R.id.genderTextView);

        }
    }
}
