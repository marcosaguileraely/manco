package com.manco.app.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.manco.app.Model.Equipos;
import com.manco.app.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcosantonioaguilerely on 6/6/15.
 */
public class EquiposAdapter extends ArrayAdapter<Equipos>{

    private final Context context;
    private final ArrayList<Equipos> equiposArrayList;

    public EquiposAdapter(Context context, ArrayList<Equipos> equiposArrayList){
        super(context, R.layout.equipos_list_items, equiposArrayList);
        this.context = context;
        this.equiposArrayList = equiposArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.equipos_list_items, parent, false);

        // 3. Get the two text view from the rowView
        TextView id = (TextView) rowView.findViewById(R.id.id);
        TextView name = (TextView) rowView.findViewById(R.id.nombre);
        TextView descrip = (TextView) rowView.findViewById(R.id.descripcion);

        Equipos equipos = (Equipos) equiposArrayList.get(position);

        // 4. Set the text for textView
        id.setText(String.valueOf(equipos.getId_equipo()));
        name.setText(equipos.getNombre());
        descrip.setText(equipos.getDescripcion());

        // 5. return rowView
        return rowView;
        //return super.getView(position, convertView, parent);
    }

    public long getItemId(int position) {
        if (equiposArrayList != null) {
            return equiposArrayList.get(position).getId_equipo();
        }
        return 0;
    }

    public String getName(int position){
        if (equiposArrayList != null) {
            return equiposArrayList.get(position).getNombre();
        }
        return "ok name";
    }

    public String getDescription(int position){
        if (equiposArrayList != null) {
            return equiposArrayList.get(position).getDescripcion();
        }
        return "ok description";
    }


}
