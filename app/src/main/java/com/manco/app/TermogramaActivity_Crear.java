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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.manco.app.Model.Equipos;
import com.manco.app.Model.Termograma;
import com.manco.app.Utils.DatabaseHandler;
import com.manco.app.Utils.EquiposAdapter;
import com.manco.app.Utils.MyFileContentProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TermogramaActivity_Crear extends ActionBarActivity {

    TextView    ubicacion, foto_camara, foto_termica, tipo_equipo;
    Button      guardar, camara;
    ImageView   img;
    Spinner     condicion_termica_spinner;
    Spinner     listado_equipos;

    String      ubicacion_str, foto_camara_str, foto_termica_str, condicion_termica_str, tipo_equipo_str;
    int         usuario, instalacion;

    String      prefix_term            = "IR_";
    String      ext_term               = ".jpg";
    String      condicion_txt;
    String      equipo_txt;

    private static final String LOGTAG  = "EXPLOREDB";
    Context context                     = this;
    DatabaseHandler databaseHandler     = new DatabaseHandler(this);

    private final int CAMERA_RESULT     = 1;
    private final String Tag = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termograma_crear);

        ubicacion                   = (TextView) findViewById(R.id.ubicacion);
        foto_camara                 = (TextView) findViewById(R.id.foto_camara);
        foto_termica                = (TextView) findViewById(R.id.foto_termica);
        guardar                     = (Button)  findViewById(R.id.guardar_term_butt);
        camara                      = (Button)  findViewById(R.id.init_camara_butt);
        img                         = (ImageView) findViewById(R.id.pic_result);
        condicion_termica_spinner   = (Spinner) findViewById(R.id.condicion_spinner);
        listado_equipos             = (Spinner) findViewById(R.id.equipos_spinner);

        /*populating Listado de Equipos Spinner*/
        List<String> list = databaseHandler.listarEquiposSpinner();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listado_equipos.setAdapter(arrayAdapter);

        /*populating Condición Termica Spinner*/
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.condiciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        condicion_termica_spinner.setAdapter(adapter);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubicacion_str           = ubicacion.getText().toString();
                foto_camara_str         = foto_camara.getText().toString();
                foto_termica_str        = foto_termica.getText().toString();
                String term_name        = prefix_term + foto_termica_str + ext_term;
                condicion_termica_str   = condicion_txt; // condicion_txt es capturada desde el spinner y asignador a la variable condicion_termica_str
                tipo_equipo_str         = equipo_txt;
                usuario                 = 1;
                instalacion             = 1;

                Log.i(LOGTAG, "---> Crear Termograma");
                databaseHandler.agregarTermogramas(new Termograma(1, ubicacion_str, foto_camara_str, term_name, condicion_termica_str, tipo_equipo_str, instalacion, usuario));
                Log.i(LOGTAG, "---> Termograma creado");

                Intent gobackListaTermo = new Intent(TermogramaActivity_Crear.this, TermogramaActivity.class);
                startActivity(gobackListaTermo);
                Toast.makeText(getBaseContext(), "Termograma creado exitosamente", Toast.LENGTH_SHORT).show();
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

        condicion_termica_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                condicion_txt = condicion_termica_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listado_equipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                equipo_txt = listado_equipos.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
