package com.usher.usher.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

import com.github.mikephil.charting.charts.PieChart;
import com.usher.usher.R;
import com.usher.usher.models.RepresentativeVO;

import java.util.ArrayList;

public class RepresentativeAdapter extends RecyclerView.Adapter<RepresentativeAdapter.RepresentativeViewHolder>{

    ArrayList<RepresentativeVO> listRepresentative;
    Float presentism;
    private Context context;

    public RepresentativeAdapter(ArrayList<RepresentativeVO> listRepresentative, Context context) {
        this.listRepresentative = listRepresentative;
        this.context = context;
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

        holder.aNameAndSurnameRepresentative.setText(listRepresentative.get(position).getNameAndSurname());
        holder.aBlockRepresentative.setText(listRepresentative.get(position).getBlock());
        if (listRepresentative.get(position).getPresentism() > 0.5) {
            holder.aPresenceSituationRepresentative.setText( "PRESENTE en un " + listRepresentative.get(position).getPresentism()*100 + "%");
            holder.aPresenceSituationRepresentative.setTextColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            holder.aPresenceSituationRepresentative.setText( "AUSENTE EN UN " + listRepresentative.get(position).getPresentism()*100 + "%");
            holder.aPresenceSituationRepresentative.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }

        holder.aBlockRepresentative.setText(listRepresentative.get(position).getBlock());

        /**Glide.with(holder.aPhotoRepresentative)
                .load(listRepresentative.get(position).getPresentism())
                .circleCrop()
                .into(holder.aPhotoRepresentative);
         */

    }

    @Override
    public int getItemCount() {
        return listRepresentative.size();
    }

    public class RepresentativeViewHolder extends RecyclerView.ViewHolder {
        //Se encarga de cargar los elementos graficos, tenemos las referencias graficas
        ImageView aPhotoRepresentative;
        TextView aNameAndSurnameRepresentative, aBlockRepresentative, aPresenceSituationRepresentative;
        PieChart aPresentismGraph;

        public RepresentativeViewHolder(@NonNull View itemView) {
            super(itemView);
            aNameAndSurnameRepresentative = itemView.findViewById(R.id.vNameAndSurnameRepresentative);
            aBlockRepresentative = itemView.findViewById(R.id.vBlockRepresentative);
            aPhotoRepresentative = itemView.findViewById(R.id.vPhotoRepresentative);
            aPresenceSituationRepresentative = itemView.findViewById(R.id.vPresenceSituationRepresentative);
            aPresentismGraph = itemView.findViewById(R.id.vPresentismGraph);
        }
    }
}
