package com.manco.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.manco.app.Utils.DatabaseHandler;
import com.manco.app.Utils.EquiposAdapter;


public class EquiposActivity extends ActionBarActivity {
    private static final String LOGTAG  =  "EXPLOREDB";
    ListView    listViewEquipos;
    Button      nuevo_equipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);

        nuevo_equipo = (Button) findViewById(R.id.equipo_nuevo_butt);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);

        final EquiposAdapter equiposAdapter = new EquiposAdapter(this, databaseHandler.listarEquipos());
        listViewEquipos = (ListView) findViewById(R.id.ListViewEquipos);
        listViewEquipos.setAdapter(equiposAdapter);

        nuevo_equipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoNuevoEqupo = new Intent(EquiposActivity.this, EquiposActivity_Crear.class);
                gotoNuevoEqupo.putExtra("accion", "crear");
                startActivity(gotoNuevoEqupo);
            }
        });

        listViewEquipos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long id_equipo = equiposAdapter.getItemId(position);
                String name    = equiposAdapter.getName(position);
                String desc    = equiposAdapter.getDescription(position);

                Log.d("id equipo", "---->" + id + " --->" + name + " --->" + desc );
                Toast.makeText(EquiposActivity.this, "Equipo "+id_equipo+" seleccionado", Toast.LENGTH_SHORT).show();

                Intent goToBorrar = new Intent(EquiposActivity.this, EquiposActivity_Crear.class);
                goToBorrar.putExtra("accion", "borrar");
                goToBorrar.putExtra("id", id_equipo);
                goToBorrar.putExtra("name", name);
                goToBorrar.putExtra("descrip", desc);

                startActivity(goToBorrar);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent goToHome = new Intent(EquiposActivity.this, MainActivity.class);
        startActivity(goToHome);
    }
}
