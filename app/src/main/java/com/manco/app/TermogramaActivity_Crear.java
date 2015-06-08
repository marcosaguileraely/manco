package com.manco.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.manco.app.Model.Termograma;
import com.manco.app.Utils.DatabaseHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TermogramaActivity_Crear extends ActionBarActivity {

    TextView    ubicacion, foto_camara, foto_termica, condicion_termica, tipo_equipo;
    Button      guardar, camara;

    String      ubicacion_str, foto_camara_str, foto_termica_str, condicion_termica_str;
    int         tipo_equipo_str, usuario, instalacion;

    private static final String LOGTAG  = "EXPLOREDB";
    Context context                     = this;
    DatabaseHandler databaseHandler     = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termograma_crear);

        ubicacion           = (TextView) findViewById(R.id.ubicacion);
        foto_camara         = (TextView) findViewById(R.id.foto_camara);
        foto_termica        = (TextView) findViewById(R.id.foto_termica);
        condicion_termica   = (TextView) findViewById(R.id.condicion_termica);
        tipo_equipo         = (TextView) findViewById(R.id.tipo_equipo);
        guardar             = (Button)  findViewById(R.id.guardar_term_butt);
        camara              = (Button)  findViewById(R.id.init_camara_butt);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubicacion_str           = ubicacion.getText().toString();
                foto_camara_str         = foto_camara.getText().toString();
                foto_termica_str        = foto_termica.getText().toString();
                condicion_termica_str   = condicion_termica.getText().toString();
                tipo_equipo_str         = Integer.parseInt(tipo_equipo.getText().toString());
                usuario                 = 1;
                instalacion             = 1;

                Log.i(LOGTAG, "Crear Termograma");
                databaseHandler.agregarTermogramas(new Termograma(1,ubicacion_str,foto_camara_str,foto_termica_str,condicion_termica_str,tipo_equipo_str,instalacion,usuario));
                Log.i(LOGTAG, "---> Termograma creado");
            }
        });

        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(intent, 0);
                //camera stuff
                Intent imageIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                //folder stuff
                File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
                imagesFolder.mkdirs();

                File image = new File(imagesFolder, "QR_" + timeStamp + ".png");
                Uri uriSavedImage = Uri.fromFile(image);

                imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                //startActivityForResult(imageIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });






    }
}
