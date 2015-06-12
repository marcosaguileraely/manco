package com.manco.app.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.manco.app.Model.Equipos;
import com.manco.app.Model.Termograma;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marcosantonioaguilerely on 6/5/15.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String LOGTAG              = "EXPLOREDB";
    private static final int DATABASE_VERSION       = 1;
    private static final String DATABASE_NAME       = "mancodb";
    private static final String TABLE_EQUIPOS       = "equipos";
    private static final String TABLE_INSTALACION   = "instalacion";
    private static final String TABLE_TERMOGRAMA    = "termograma";
    private static final String TABLE_USUARIO       = "usuario";

    // Equipos Table Columns names
    private static final String KEY_ID              = "id";
    private static final String KEY_NAME            = "nombre";
    private static final String KEY_DESCRIP         = "descripcion";

    // Instalacion Table Columns names
    private static final String KEY_ID_INST         = "id";
    private static final String KEY_NAME_INST       = "nombre";
    private static final String KEY_DATE_INST       = "fecha";
    private static final String KEY_LAT_INST        = "lat";
    private static final String KEY_LONG_INST       = "long";

    // Termogramas Table Columns names
    private static final String KEY_ID_TERM         = "id";
    private static final String KEY_UBICACION_TERM  = "ubicacion";
    private static final String KEY_FOTO_CAMARA     = "foto_camara";
    private static final String KEY_FOTO_TERM       = "foto_termica";
    private static final String KEY_CONDICION_TERM  = "condicion_termica";
    private static final String KEY_IDEQUIPO_TERM   = "id_equipo";
    private static final String KEY_IDINSTAL_TERM   = "id_instalacion";
    private static final String KEY_IDUSR_TERM      = "id_usuario";
    private static final String KEY_CREADO          = "creado";
    private static final String KEY_ACTUALIZADO     = "actualizado";

    // Usuarios Table Columns names
    private static final String KEY_ID_USR          = "id";
    private static final String KEY_NOMBRES_USR     = "nombres";
    private static final String KEY_APELLIDOS_USR   = "apellidos";
    private static final String KEY_DNI_USR         = "dni";
    private static final String KEY_TIPODNI_USR     = "tipodni";
    private static final String KEY_USUARIO_USR     = "usuario";
    private static final String KEY_CLAVE_USR       = "clave";


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(LOGTAG, "Proceso db");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            /*Equipos table structure*/
            String CREATE_EQUIPOS_TABLE = "CREATE TABLE "+TABLE_EQUIPOS+"("
                    +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +KEY_NAME+" TEXT,"
                    +KEY_DESCRIP+" TEXT"+")";

            /*Instalacion table structure*/
            String CREATE_INSTALACION_TABLE = "CREATE TABLE "+TABLE_INSTALACION+"("
                    +KEY_ID_INST+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +KEY_NAME_INST+" TEXT,"
                    +KEY_DATE_INST+" DATETIME,"
                    +KEY_LAT_INST+" INT,"
                    +KEY_LONG_INST+" INT"+")";

            /*Termograma table structure*/
            String CREATE_TERMOGRAMA_TABLE = "CREATE TABLE "+TABLE_TERMOGRAMA+"("
                    +KEY_ID_TERM+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +KEY_UBICACION_TERM+" TEXT,"
                    +KEY_FOTO_CAMARA+" TEXT,"
                    +KEY_FOTO_TERM+" TEXT,"
                    +KEY_CONDICION_TERM+" TEXT,"
                    +KEY_IDEQUIPO_TERM+" TEXT,"
                    +KEY_IDINSTAL_TERM+" INT,"
                    +KEY_IDUSR_TERM+" INT,"
                    +KEY_CREADO+" DATETIME,"
                    +KEY_ACTUALIZADO+" DATETIME"+")";

            /*Usuario table structure*/
            String CREATE_USUARIO_TABLE = "CREATE TABLE "+TABLE_USUARIO+"("
                    +KEY_ID_USR+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +KEY_NOMBRES_USR+" TEXT,"
                    +KEY_APELLIDOS_USR+" TEXT,"
                    +KEY_DNI_USR+" INT,"
                    +KEY_TIPODNI_USR+" TEXT,"
                    +KEY_USUARIO_USR+" INT,"
                    +KEY_CLAVE_USR+" INT"+")";

            /*Creation trigger*/
            db.execSQL(CREATE_EQUIPOS_TABLE);
            db.execSQL(CREATE_INSTALACION_TABLE);
            db.execSQL(CREATE_TERMOGRAMA_TABLE);
            db.execSQL(CREATE_USUARIO_TABLE);

            Log.i(LOGTAG, "Tabla equipos creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EQUIPOS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTALACION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TERMOGRAMA);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);

            // Create tables again
            onCreate(db);
    }

    /*Equipos CRUD*/

    // metodo agregar
    public void agregarEquipos(Equipos equipos){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, equipos.getNombre());
        values.put(KEY_DESCRIP, equipos.getDescripcion());

        db.insert(TABLE_EQUIPOS, null, values);
        db.close();
    }

    // metodo borrar equipo
    public void borrarEquipos(long id_equipo){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i(LOGTAG, "eliminar equipo #" + id_equipo);
        db.delete(TABLE_EQUIPOS, KEY_ID + " = ?",
                new String[]{
                        String.valueOf(id_equipo)
                });
        Log.i(LOGTAG, "equipo #" + id_equipo + " eliminado");
        db.close();
    }

    // metodo actualizar equipo
    public void actualizarEquipos(){

    }

    // metodo listar equipo
    public ArrayList<Equipos> listarEquipos(){
        int id_equipo;
        String nombre, descripcion;

        ArrayList<Equipos> items = new ArrayList<Equipos>();

        String selectQuery = "SELECT * FROM " + TABLE_EQUIPOS + " ORDER BY id desc";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()) {
            do {
                id_equipo = cursor.getInt(0);
                nombre = cursor.getString(1);
                descripcion = cursor.getString(2);

                items.add(new Equipos(id_equipo, nombre, descripcion));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return items;
    }

    // metodo listar equipo para Spinner
    public List<String> listarEquiposSpinner(){
        int id_equipo;
        String nombre, descripcion;

        List<String> items = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + TABLE_EQUIPOS + " ORDER BY id asc";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()) {
            do {
               // id_equipo = cursor.getInt(0);
               // nombre = cursor.getString(1);
               // descripcion = cursor.getString(2);

                items.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        Log.d(">>> ", ">>>>" + items);
        return items;
    }

    /* ---- END Equipos --- */

    /********************/
    /* Termogramas CRUD */

    // metodo agregar termogramas
    public void agregarTermogramas(Termograma termograma){
        String timeStamp  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UBICACION_TERM, termograma.getUbicación());
        values.put(KEY_FOTO_CAMARA, termograma.getFoto_camara());
        values.put(KEY_FOTO_TERM, termograma.getFoto_termograma());
        values.put(KEY_CONDICION_TERM, termograma.getCondicion_termica());
        values.put(KEY_IDEQUIPO_TERM, termograma.getEquipo());
        values.put(KEY_IDINSTAL_TERM, termograma.getId_instalacion());
        values.put(KEY_IDUSR_TERM, termograma.getId_usuario());
        values.put(KEY_CREADO, timeStamp);
        values.put(KEY_ACTUALIZADO, timeStamp);

        db.insert(TABLE_TERMOGRAMA, null, values);
        db.close();
    }

    /* ---- END Equipos --- */



}
