package com.manco.app;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import com.manco.app.Utils.DatabaseHandlerEquipos;

import java.io.File;


public class MainActivity extends ActionBarActivity {

    private static final String LOGTAG  =  "EXPLOREDB";
    SQLiteOpenHelper    dbhelper;
    SQLiteDatabase      database;
    Boolean             existsdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        existsdb =  this.dbExists();
        if(existsdb == false){
            dbhelper = new DatabaseHandlerEquipos(this);
            database = dbhelper.getWritableDatabase();

            Log.d("Creating DB: ", "Db created... check it!");
        }else{
            //noting to-do
        }
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
