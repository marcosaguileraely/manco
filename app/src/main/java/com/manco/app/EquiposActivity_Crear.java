package com.manco.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.manco.app.Model.Equipos;
import com.manco.app.Utils.DatabaseHandler;


public class EquiposActivity_Crear extends ActionBarActivity {

    TextView        nombre;
    TextView        descripcion;
    Button          guardar, cancelar, eliminar, cancelar2;
    String          name, desc;
    LinearLayout    linearLayoutGuardar, linearLayoutEliminar;

    /*INTENT EXTRA VARS*/
    long            id_equipo;
    String          name_extra, desc_extra;

    private static final String LOGTAG  =  "EXPLOREDB";
    Context context                     = this;
    DatabaseHandler databaseHandler     = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos_crear);

        nombre       = (TextView) findViewById(R.id.nombre_equipo);
        descripcion  = (TextView) findViewById(R.id.descripcion_equipo);
        guardar      = (Button) findViewById(R.id.guardar_equipo_butt);
        cancelar     = (Button) findViewById(R.id.cancelar_equipo_butt);
        cancelar2    = (Button) findViewById(R.id.cancelar_equipo_butt2);
        eliminar     = (Button) findViewById(R.id.eliminar_equipo_butt);
        linearLayoutGuardar  = (LinearLayout) findViewById(R.id.acciones_gurardar_cancelar);
        linearLayoutEliminar = (LinearLayout) findViewById(R.id.acciones_eliminar_cancelar);

        Intent intent  = getIntent();
        String action  = intent.getStringExtra("accion");
        id_equipo      = intent.getLongExtra("id", 1L);
        name_extra     = intent.getStringExtra("name");
        desc_extra     = intent.getStringExtra("descrip");

        if(action.equals("crear")){
            Log.d("ACTION", "---> crear");
            linearLayoutEliminar.setVisibility(View.INVISIBLE);
            linearLayoutGuardar.setVisibility(View.VISIBLE);
        }

        if (action.equals("borrar")){
            Log.d("ACTION", "---> borrar");
            linearLayoutGuardar.setVisibility(View.INVISIBLE);
            linearLayoutEliminar.setVisibility(View.VISIBLE);
            nombre.setText(name_extra);
            descripcion.setText(desc_extra);
        }

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nombre.getText().toString();
                desc = descripcion.getText().toString();
                Log.i(LOGTAG, "Crear equipos");
                databaseHandler.agregarEquipos(new Equipos(1, name, desc));
                Log.i(LOGTAG, "Equipo creado");
                Intent gobackListaEquipos =  new Intent(EquiposActivity_Crear.this, EquiposActivity.class);
                startActivity(gobackListaEquipos);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.setText("");
                descripcion.setText("");
                Intent gobackListaEquipos = new Intent(EquiposActivity_Crear.this, EquiposActivity.class);
                startActivity(gobackListaEquipos);
            }
        });

        cancelar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.setText("");
                descripcion.setText("");
                Intent gobackListaEquipos = new Intent(EquiposActivity_Crear.this, EquiposActivity.class);
                startActivity(gobackListaEquipos);
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog();
            }
        });
    }

    //Confirmar eliminacion
    private void showDeleteDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("¿Está seguro de eliminar este equipo?")
                .setCancelable(false)
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                databaseHandler.borrarEquipos(id_equipo);
                                Intent gobackListaEquipos = new Intent(EquiposActivity_Crear.this, EquiposActivity.class);
                                startActivity(gobackListaEquipos);
                                Toast.makeText(context, "Acción exitosa", Toast.LENGTH_SHORT).show();
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                        Toast.makeText(context, "Acción cancelada", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
        Intent goToEquipos = new Intent(EquiposActivity_Crear.this, EquiposActivity.class);
        startActivity(goToEquipos);
    }
}
