package com.example.juancastro.basededatos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
/**
 * Created by JuanCastro on 27/05/2015.
 */
public class Animaladapter extends RecyclerView.Adapter<Animaladapter.animalViewHolder> {
    private List<Modeloanimal> items;



    public static class animalViewHolder extends RecyclerView.ViewHolder{

        public TextView id;
        public TextView nombre_comun;
        public TextView nombre_cient;
        public TextView habitat;
        public TextView caracteristicas;

        public animalViewHolder(View v){
            super(v);
            id = (TextView) v.findViewById(R.id.id_a);
            nombre_comun= (TextView) v.findViewById(R.id.nombrecomun);
            nombre_cient= (TextView) v.findViewById(R.id.nombrecient);
            habitat= (TextView) v.findViewById(R.id.habit);
            caracteristicas= (TextView) v.findViewById(R.id.caract);
        }
    }

    public Animaladapter(List<Modeloanimal> items){
        this.items= items;
    }

    @Override
    public animalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.animales_card, viewGroup, false);
        return new animalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(animalViewHolder animalViewHolder, int i) {
        animalViewHolder.id.setText("id: "+ String.valueOf(items.get(i).getId_animal()));
        animalViewHolder.nombre_comun.setText("nombre comun: "+ String.valueOf(items.get(i).getNombre_comun()));
        animalViewHolder.nombre_cient.setText("nombre cientifico: " + String.valueOf(items.get(i).getNombre_cient()));
        animalViewHolder.habitat.setText("habitat:" + String.valueOf(items.get(i).getHabitat()));
        animalViewHolder.caracteristicas.setText("caracteristicas: "+ String.valueOf(items.get(i).getCaracteristicas()));


    }

    @Override
    public int getItemCount() {

        return items.size();
    }
}

