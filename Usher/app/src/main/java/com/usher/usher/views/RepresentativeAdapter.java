package com.usher.usher.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.usher.usher.R;
import com.usher.usher.models.RepresentativeVO;

import java.util.ArrayList;

public class RepresentativeAdapter extends RecyclerView.Adapter<RepresentativeAdapter.RepresentativeViewHolder> {

    ArrayList<RepresentativeVO> listRepresentative;

    public RepresentativeAdapter(ArrayList<RepresentativeVO> listRepresentative) {
        this.listRepresentative = listRepresentative;
    }

    //Cada uno de lso datos a referenciar va a ser obsrvado por los 3 metodos del Adapter
    @NonNull
    @Override
    public RepresentativeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_representative,null,false);

        //Asi abajo el ViewHolder recive este view
        return new RepresentativeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepresentativeViewHolder holder, int position) {
        //Llenado de datos
        holder.vName.setText(listRepresentative.get(position).getName());
        holder.vInformation.setText(listRepresentative.get(position).getTextInformation());
        //aca puse la misma imagen porque deberiamso armar la logica de todo el grafico en un futuro cercanisimo



        /**Glide.with(holder.vGraphic)
                .load(listRepresentative.get(position).getGraphicPresentism())
                .circleCrop()
                .into(holder.vGraphic);
         */

    }

    @Override
    public int getItemCount() {
        return listRepresentative.size();
    }

    public class RepresentativeViewHolder extends RecyclerView.ViewHolder {
        //Se encarga de cargar los elementos graficos, tenemos las referencias graficas

        TextView vName, vInformation;
        ImageView vGraphic;

        public RepresentativeViewHolder(@NonNull View itemView) {
            super(itemView);
            vName = (TextView) itemView.findViewById(R.id.vNameRepresentative);
            vInformation = (TextView) itemView.findViewById(R.id.vDescriptionRepresentative);
            vGraphic = (ImageView) itemView.findViewById(R.id.vGraphicRepresentativePres);
        }
    }
}
