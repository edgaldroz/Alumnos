package com.ar.alumnos;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ar.alumnos.controlador.controllerAlumno;
import com.ar.alumnos.modelo.Alumno;


public class Insertar extends AppCompatActivity {

    //region ..:: DECLARACION DE VARIABLES ::..
    private EditText txtCodigo;
    private EditText txtNombre;
    private EditText txtEdad;
    private Button btnGuardar;
    private Alumno Modelo;
    private controllerAlumno Controlador;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        //region  ... Inicializacion de las Variables ...
        this.Controlador = new controllerAlumno(this);

        this.txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        this.txtNombre = (EditText) findViewById(R.id.txtNombre);
        this.txtEdad = (EditText)findViewById(R.id.txtEdad);
        this.btnGuardar = (Button) findViewById(R.id.btnGuardar);
        //endregion

        // Listener para el BTNGuardar
        this.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Insertar.this.Agregar();
            }
        });
    }

    private void Agregar()
    {
        if(ValidarDatos())
        {
            this.Modelo = new Alumno();
            this.Modelo.setCodigo(this.txtCodigo.getText().toString());
            this.Modelo.setNombre(this.txtNombre.getText().toString());
            this.Modelo.setEdad(Integer.parseInt(this.txtEdad.getText().toString()));

            try
            {
                //Realizamos la insercción en la base de datos
                this.Controlador.Abrir();

                //INsertamos y validamos
                if (this.Controlador.Insertar(this.Modelo))
                {
                    Toast.makeText(this, "Aluumno Insertado con Éxito", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(this, "Error al Insertar el Aluumno", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }catch (SQLiteException ex)
            {
                Toast.makeText(this, "Error en la base de datos!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Existen Campos sin Completar... ", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean ValidarDatos()
    {
        String pCodigo = this.txtCodigo.getText().toString();
        String pNombre = this.txtNombre.getText().toString();
        String pEdad = this.txtEdad.getText().toString();

        if(pCodigo.equals("") || pCodigo.length() == 0 || pCodigo == null)
        {
            Toast.makeText(this, "El Código no debe estar Vacío", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(pNombre.equals("") || pNombre.length() == 0 || pNombre == null)
        {
            Toast.makeText(this, "El Nombre no debe estar Vacío", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(pEdad.equals("") || pEdad.length() == 0 || pEdad == null)
        {
            return false;
        }
        else if(Integer.parseInt(pEdad) <= 0)
        {
            Toast.makeText(this, "Edad tiene que ser Número Natural", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private void Limpiar()
    {
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.txtEdad.setText("");
    }
}
