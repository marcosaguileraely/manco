package com.manco.app;

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
import com.manco.app.Utils.TermogramaAdapter;

public class TermogramaActivity extends ActionBarActivity {
    private static final String LOGTAG  =  "EXPLOREDB";
    ListView    listViewTermo;
    Button      nuevo_termograma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termograma);

        listViewTermo       = (ListView) findViewById(R.id.ListViewTermogramas);
        nuevo_termograma    = (Button) findViewById(R.id.nuevo_termo_butt);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);

        final TermogramaAdapter termogramaAdapter = new TermogramaAdapter(this, databaseHandler.listarTermogramas());
        listViewTermo.setAdapter(termogramaAdapter);

        nuevo_termograma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoNuevoTermo = new Intent(TermogramaActivity.this, TermogramaActivity_Crear.class);
                gotoNuevoTermo.putExtra("accion", "crear");
                startActivity(gotoNuevoTermo);
            }
        });

        listViewTermo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long id_termo       = termogramaAdapter.getItemId(position);
                String ubicacion    = termogramaAdapter.getName(position);
                String equipo       = termogramaAdapter.getEquipo(position);
                String condicion    = termogramaAdapter.getCondicion(position);
                String imgname      = termogramaAdapter.getImgName(position);
                String termoname    = termogramaAdapter.getTermoName(position);

                Log.d("id equipo", "---->" + id_termo + " --->" + ubicacion + " --->" + equipo);
                Toast.makeText(TermogramaActivity.this, "Termograma " + id_termo + " seleccionado", Toast.LENGTH_SHORT).show();

                Intent goToBorrar = new Intent(TermogramaActivity.this, TermogramaActivity_Crear.class);
                goToBorrar.putExtra("accion", "general");
                goToBorrar.putExtra("id_termo", id_termo);
                goToBorrar.putExtra("ubicacion", ubicacion);
                goToBorrar.putExtra("equipo", equipo);
                goToBorrar.putExtra("condicion", condicion);
                goToBorrar.putExtra("imgname", imgname);
                goToBorrar.putExtra("termoname", termoname);

                startActivity(goToBorrar);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent goBackHome = new Intent(TermogramaActivity.this, MainActivity.class);
        startActivity(goBackHome);
    }
}
