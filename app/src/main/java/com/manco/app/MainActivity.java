package com.manco.app;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.manco.app.Model.Equipos;
import com.manco.app.Utils.DatabaseHandler;

import java.io.File;


public class MainActivity extends ActionBarActivity {

    private static final String LOGTAG  =  "EXPLOREDB";
    SQLiteOpenHelper    dbhelper;
    SQLiteDatabase      database;
    Boolean             existsdb;

    Button              instalaciones;
    Button              equipos;
    Button              termografica;
    Button              usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instalaciones = (Button) findViewById(R.id.instalacion_butt);
        equipos       = (Button) findViewById(R.id.equipos_butt);
        termografica  = (Button) findViewById(R.id.termo_butt);
        usuarios      = (Button) findViewById(R.id.usuarios_butt);

        existsdb =  this.dbExists();
        if(existsdb == false){
            dbhelper = new DatabaseHandler(this);
            database = dbhelper.getWritableDatabase();

            Log.d("Creating DB: ", "Db created... check it!");
        }else{
            //noting to-do
        }

        equipos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iraEquipos = new Intent(MainActivity.this, EquiposActivity.class);
                startActivity(iraEquipos);
            }
        });
    }

    public Boolean dbExists(){
        File db = new File("/data/data/com.manco.app/databases/mancodb");
        if(db.exists()){
            Log.i(LOGTAG, "--> Existe DB");
            return true;
        }else {
            Log.i(LOGTAG, "--> No Existe DB");
            return false;
        }
    }
}
