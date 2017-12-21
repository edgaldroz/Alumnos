package com.ar.alumnos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ar.alumnos.adaptadores.adapterAlumnos;
import com.ar.alumnos.controlador.controllerAlumno;
import com.ar.alumnos.modelo.Alumno;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {

    //region ..:: DECLARACION DE VARIABLES ::..

    private Button btnNuevo;
    private Button btnBuscar;
    private ListView Listado_ALumnos;
    private List<Alumno> Alumnos;
    private controllerAlumno Controlador;
    private adapterAlumnos adaptadorAlumno;

    //endregion

    public void onResume(){
        super.onResume();
        this.Consultar_Alumnos();
        this.adaptadorAlumno = new adapterAlumnos(this,R.layout.plantillaalumno, this.Alumnos);
        this.Listado_ALumnos.setAdapter(this.adaptadorAlumno);
    }

    private void Consultar_Alumnos()
    {
        try {
            //Abrimos la conexion
            this.Controlador.Abrir();
            Cursor tabla = this.Controlador.Consultar_Todos();

            //Validar que no este vacio
            if (tabla != null && tabla.getCount() > 0){
                //Recorremos los elementos de la tabla
                tabla.moveToFirst();
                //this.Productos.clear();
                this.Alumnos = new ArrayList<>();

                do
                {
                    Alumno Fila = new Alumno();
                    Fila.setCodigo(tabla.getString(0));
                    Fila.setNombre(tabla.getString(1));
                    Fila.setEdad(tabla.getInt(2));

                    this.Alumnos.add(Fila);
                }while (tabla.moveToNext());
            }

            //Cerramos la conexion
            this.Controlador.Cerrar();

        }catch (SQLiteException ex)
        {
            Toast.makeText(this, "Error al obtener los Alumnos de la base de datos", Toast.LENGTH_LONG).show();
        }
    }

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // Inicializamos los objetos
        this.Alumnos = new ArrayList<>();
        this.btnNuevo = (Button) findViewById(R.id.btnAgregar);
        this.btnBuscar = (Button) findViewById(R.id.btnBuscar);
        this.Listado_ALumnos = (ListView) findViewById(R.id.Listado_Alumnos);
        this.Controlador = new controllerAlumno(this);
        this.Consultar_Alumnos();
        this.adaptadorAlumno = new adapterAlumnos(this,R.layout.plantillaalumno, this.Alumnos);
        this.Listado_ALumnos.setAdapter(this.adaptadorAlumno);

        // Listener para BTNAGREGAR
        //Establecemos los listener
        this.btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamamos al formulario de Nuevo Producto
                Intent dara = new Intent(Principal.this, Insertar.class);
                startActivity(dara);
            }
        });

        // Listener para BTNBUSCAR
        this.btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent dara = new Intent(Principal.this, Buscar.class);
                startActivity(dara);
            }
        });
    }
}
