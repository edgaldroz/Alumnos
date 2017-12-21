package com.ar.alumnos.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ar.alumnos.controlador.controllerAlumno;

/**
 * Created by Adalberto on 08/12/2017.
 */

public class clsOpenHelper extends SQLiteOpenHelper
{

    public clsOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(controllerAlumno.CREATE_TABLE_PRODUCTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + controllerAlumno.NOMBRE_TABLA);
        onCreate(sqLiteDatabase);
    }
}
