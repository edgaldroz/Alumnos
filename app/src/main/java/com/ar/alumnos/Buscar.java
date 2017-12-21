package com.ar.alumnos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ar.alumnos.controlador.controllerAlumno;

public class Buscar extends AppCompatActivity
{
    private RadioButton radioCodigo, radioNombre;
    private TextView txtBuscador,txtEncontrado;
    private Button btnBuscar,btnEliminar;
    private controllerAlumno objBaseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        this.objBaseDatos = new controllerAlumno(this);
        radioCodigo = (RadioButton) findViewById(R.id.radioCodigo);
        radioNombre = (RadioButton) findViewById(R.id.radioNombre);
        txtBuscador = (TextView) findViewById(R.id.txtBuscador);
        txtEncontrado = (TextView) findViewById(R.id.txtEncontrado);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(radioCodigo.isChecked()){
                    String buscarCodigo= txtBuscador.getText().toString();
                    String[] datos1 = objBaseDatos.buscar_reg(buscarCodigo);

                    txtEncontrado.setText(datos1[0]+"   "+datos1[1]);

                    Toast.makeText(getApplicationContext(),datos1[2],Toast.LENGTH_SHORT).show();

                }else if (radioNombre.isChecked()){
                    String buscarNombre= txtBuscador.getText().toString();
                    String[] datos2 = objBaseDatos.buscar_Nom(buscarNombre);

                    txtEncontrado.setText(datos2[0]+"   "+datos2[1]);

                    Toast.makeText(getApplicationContext(),datos2[2],Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar.this.Eliminar();
            }
        });

    }
    private void Eliminar(){
        if(radioCodigo.isChecked()){

            String EliminarCodigo= txtBuscador.getText().toString();
            String mensaje1 = objBaseDatos.Eliminar(EliminarCodigo);
            Toast.makeText(getApplicationContext(),mensaje1,Toast.LENGTH_SHORT).show();
            finish();
        }else if (radioNombre.isChecked()){
            String EliminarNombre= txtBuscador.getText().toString();
            String mensaje1 = objBaseDatos.EliminarNombre(EliminarNombre);
            Toast.makeText(getApplicationContext(),mensaje1,Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
