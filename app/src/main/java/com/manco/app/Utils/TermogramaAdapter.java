package com.manco.app.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.manco.app.Model.Equipos;
import com.manco.app.Model.Termograma;
import com.manco.app.R;

import java.util.ArrayList;

/**
 * Created by marcosantonioaguilerely on 6/7/15.
 */
public class TermogramaAdapter extends ArrayAdapter<Termograma>{

    private final Context context;
    private final ArrayList<Termograma> termogramaArrayList;

    public TermogramaAdapter(Context context, ArrayList<Termograma> termogramaArrayList) {
        super(context, R.layout.termogramas_list_items, termogramaArrayList);
        this.context = context;
        this.termogramaArrayList = termogramaArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.termogramas_list_items, parent, false);

        // 3. Get the two text view from the rowView
        TextView id         = (TextView) rowView.findViewById(R.id.id_termo);
        TextView ubicacion  = (TextView) rowView.findViewById(R.id.ubicacion);
        TextView equipo     = (TextView) rowView.findViewById(R.id.equipo);
        TextView condicion  = (TextView) rowView.findViewById(R.id.condicion_txt);

        Termograma termograma = (Termograma) termogramaArrayList.get(position);

        id.setText(String.valueOf(termograma.getId_termograma()));
        ubicacion.setText(termograma.getUbicación());
        equipo.setText(termograma.getEquipo());
        condicion.setText(termograma.getCondicion_termica());

        return rowView;
    }

    @Override
    public int getPosition(Termograma item) {
        return super.getPosition(item);
    }

    public long getItemId(int position) {
        if (termogramaArrayList != null) {
            return termogramaArrayList.get(position).getId_termograma();
        }
        return 0;
    }

    public String getName(int position){
        if (termogramaArrayList != null) {
            return termogramaArrayList.get(position).getUbicación();
        }
        return "ok ubicacion";
    }

    public String getEquipo(int position){
        if(termogramaArrayList != null){
            return termogramaArrayList.get(position).getEquipo();
        }
        return "ok equipo";
    }

    public String getCondicion(int position){
        if(termogramaArrayList != null){
            return termogramaArrayList.get(position).getCondicion_termica();
        }
        return "ok condicion termica";
    }

    public String getImgName(int position){
        if(termogramaArrayList != null){
            return termogramaArrayList.get(position).getFoto_camara();
        }
        return "ok nombre camara";
    }

    public String getTermoName(int position){
        if(termogramaArrayList != null){
            return termogramaArrayList.get(position).getFoto_termograma();
        }
        return "ok nombre termograma";
    }
}
