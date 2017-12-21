package com.ar.alumnos.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.ar.alumnos.modelo.Alumno;
import com.ar.alumnos.sqlite.clsOpenHelper;

/**
 * Created by Adalberto on 08/12/2017.
 */

public class controllerAlumno
{
    //region ..:: DECLARACION DE CONSTANTES ::..
    public static final String DATABASE = "dbAlumnos.db";
    public static final String CREATE_TABLE_PRODUCTO =
            "CREATE TABLE Alumno(Codigo TEXT PRIMARY KEY, Nombre TEXT NOT NULL, Edad INTEGER NOT NULL);";
    public static final String NOMBRE_TABLA = "Alumno";
    public static final String COLUMN_ID = "Codigo";
    public static final String COLUMN_NOMBRE = "Nombre";
    public static final String COLUMN_EDAD= "Edad";
    public static final int VERSION_BASE = 1 ;
    //endregion

    //region ..:: DECLARACION DE VARIABLES ::..
    private SQLiteDatabase objDataBase;
    private clsOpenHelper objHelper;
    //endregion

    public controllerAlumno(Context contexto)
    {
        //Inicializamos nuestro openHelper
        this.objHelper = new clsOpenHelper(contexto, DATABASE, null, VERSION_BASE);
    }

    public boolean Abrir() throws SQLiteException
    {
        this.objDataBase = this.objHelper.getWritableDatabase();
        return true;
    }

    public String[] buscar_reg(String buscar){
        String[] datos = new String[3];
        // this.objDataBase = this.objHelper.getWritableDatabase();
        Abrir();
        String q = "SELECT Codigo, Edad FROM Alumno WHERE Codigo='"+buscar+"'";
        Cursor registos = objDataBase.rawQuery(q,null);

        if (registos.moveToFirst()){
            for (int i=0; i<2; i++){
                datos[i]= registos.getString(i);
            }
            datos[0] = "Codigo:  "+datos[0];
            datos[1] = "Edad: "+datos[1];
            datos[2]="Encontrado";
        }else {
            datos[0]=". . .";
            datos[1]=". . .";
            datos[2]="No Encontrado "+ buscar;
        }
        Cerrar();
        return datos;
    }

    public String[] buscar_Nom(String buscar){
        String[] datos = new String[3];
        //this.objDataBase = this.objHelper.getWritableDatabase();
        Abrir();
        String q = "SELECT Nombre, Edad FROM Alumno WHERE Nombre='"+buscar+"'";
        Cursor registos = objDataBase.rawQuery(q,null);
        if (registos.moveToNext()){
            for (int i=0; i<2; i++){
                datos[i]= registos.getString(i);
            }
            datos[0] = "Nombre:  "+datos[0];
            datos[1] = "Edad:  "+datos[1];
            datos[2]="Encontrado";
        }else {
            datos[0]=". . .";
            datos[1]=". . .";
            datos[2]="No Encontrado "+ buscar;
        }
        Cerrar();
        return datos;
    }


    public boolean Cerrar() throws SQLiteException
    {
        this.objDataBase.close();
        return true;
    }

    //Creacion de funciones para la manipulacion de la informacion
    public boolean Insertar(Alumno Obj)
    {
        ContentValues valorInsertar = new ContentValues();
        valorInsertar.put(COLUMN_ID, Obj.getCodigo());
        valorInsertar.put(COLUMN_NOMBRE, Obj.getNombre());
        valorInsertar.put(COLUMN_EDAD, Obj.getEdad());

        //Validamos la insercción de la informacion
        if (this.objDataBase.insert(NOMBRE_TABLA, null, valorInsertar) > 0)
        {
            return true;
        }else
        {
            return  false;
        }

    }


    /*public boolean Eliminar(String IDAlumno)
    {
        //VAlidamos la eliminacion
        if(this.objDataBase.delete(NOMBRE_TABLA, COLUMN_ID + "=" + IDAlumno, null) > 0)
        {
            return true;
        }else{
            return false;
        }
    }*/
    public String Eliminar(String IDAlumno)
    {
        //VAlidamos la eliminacion
        String mensaje = "";
        //objDataBase = this.objHelper.getWritableDatabase();
        Abrir();
        //incantidad para saber si hay algo que eliminar o no
        int cantidad = objDataBase.delete(NOMBRE_TABLA,"Codigo='"+IDAlumno+"'",null);

        if (cantidad != 0){
            mensaje = "Eliminado Correctamente";

        }else {
            mensaje = "No existe";
        }
        Cerrar();
        return mensaje;
    }

    public String EliminarNombre(String NomAlumno)
    {
        //VAlidamos la eliminacion
        String mensaje = "";
        //objDataBase = this.objHelper.getWritableDatabase();
        Abrir();
        //incantidad para saber si hay algo que eliminar o no
        int cantidad = objDataBase.delete(NOMBRE_TABLA,"Nombre='"+NomAlumno+"'",null);

        if (cantidad != 0){
            mensaje = "Eliminado Correctamente";

        }else {
            mensaje = "No existe";
        }
        Cerrar();
        return mensaje;
    }



    public Cursor Consultar_Todos()
    {
        return this.objDataBase.query(NOMBRE_TABLA, new String[]{COLUMN_ID, COLUMN_NOMBRE, COLUMN_EDAD}, null, null, null, null, null);
    }
}

