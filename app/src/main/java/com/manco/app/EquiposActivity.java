package com.manco.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.manco.app.Model.Equipos;
import com.manco.app.Utils.DatabaseHandler;
import com.manco.app.Utils.EquiposAdapter;


public class EquiposActivity extends ActionBarActivity {
    private static final String LOGTAG  =  "EXPLOREDB";
    ListView    listViewEquipos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);

        Log.i(LOGTAG, "Equipos activity");
        databaseHandler.agregarEquipos(new Equipos(1,"yoyoyoyoyoyoyoyoyoyoyoyoy","alarmas"));
        databaseHandler.agregarEquipos(new Equipos(1,"Transformadores","Transformadores"));
        databaseHandler.agregarEquipos(new Equipos(1,"Rack comunicaciones", "Rack comunicaciones"));
        databaseHandler.agregarEquipos(new Equipos(1,"Maquinas vapor", "Maquinas vapor"));

        EquiposAdapter equiposAdapter = new EquiposAdapter(this, databaseHandler.listarEquipos());
        listViewEquipos = (ListView) findViewById(R.id.ListViewEquipos);
        listViewEquipos.setAdapter(equiposAdapter);
    }
}
