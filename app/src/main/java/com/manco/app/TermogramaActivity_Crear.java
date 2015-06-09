package com.manco.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.manco.app.Model.Termograma;
import com.manco.app.Utils.DatabaseHandler;
import com.manco.app.Utils.MyFileContentProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TermogramaActivity_Crear extends ActionBarActivity {

    TextView    ubicacion, foto_camara, foto_termica, condicion_termica, tipo_equipo;
    Button      guardar, camara;
    ImageView   img;

    String      ubicacion_str, foto_camara_str, foto_termica_str, condicion_termica_str;
    int         tipo_equipo_str, usuario, instalacion;

    String      prefix_term            = "IR_";
    String      ext_term               = ".jpg";

    private static final String LOGTAG  = "EXPLOREDB";
    Context context                     = this;
    DatabaseHandler databaseHandler     = new DatabaseHandler(this);

    private final int CAMERA_RESULT     = 1;
    private final String Tag = getClass().getName();

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
        img                 = (ImageView) findViewById(R.id.pic_result);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubicacion_str           = ubicacion.getText().toString();
                foto_camara_str         = foto_camara.getText().toString();
                foto_termica_str        = foto_termica.getText().toString();
                String term_name        = prefix_term + foto_termica_str + ext_term;
                condicion_termica_str   = condicion_termica.getText().toString();
                tipo_equipo_str         = Integer.parseInt(tipo_equipo.getText().toString());
                usuario                 = 1;
                instalacion             = 1;

                Log.i(LOGTAG, "Crear Termograma");
                databaseHandler.agregarTermogramas(new Termograma(1,ubicacion_str,foto_camara_str,term_name,condicion_termica_str,tipo_equipo_str,instalacion,usuario));
                Log.i(LOGTAG, "---> Termograma creado");
            }
        });

        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = getPackageManager();
                Log.i(Tag, "Camera init");
                if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Log.i(Tag, "Camera action");
                    Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    i.putExtra(MediaStore.EXTRA_OUTPUT, MyFileContentProvider.CONTENT_URI);

                    Log.i(Tag, " Content URI  " + MyFileContentProvider.CONTENT_URI);
                    Log.i(Tag, " Content NAME " + MyFileContentProvider.name);

                    startActivityForResult(i, CAMERA_RESULT);

                } else {
                    Toast.makeText(getBaseContext(), "Camera no disponible", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(Tag, "--> "+MyFileContentProvider.name);
        if (resultCode == RESULT_OK && requestCode == CAMERA_RESULT) {
            File out = new File(getFilesDir(), MyFileContentProvider.name);
            Log.i(Tag, "--> "+out);
            if(!out.exists()) {
                Toast.makeText(getBaseContext(),
                        "Error while capturing image", Toast.LENGTH_LONG)
                        .show();
                return;
            }
            Bitmap mBitmap = BitmapFactory.decodeFile(out.getAbsolutePath());
            img.setImageBitmap(mBitmap);
            foto_camara.setText(MyFileContentProvider.name);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        img = null;
    }
}
