package com.ar.alumnos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ar.alumnos.R;
import com.ar.alumnos.modelo.Alumno;

import java.util.List;

/**
 * Created by Adalberto on 08/12/2017.
 */
public class adapterAlumnos extends BaseAdapter
{
    //region ..:: DECLARACION DE VARIABLES ::..
    private int IDPlantilla;
    private Context contexto;
    private List<Alumno> Listado_Alumnos;

    public static int Contador = 0;
    //endregion

    public adapterAlumnos(Context context, int idplantilla, List<Alumno> Alumnos)
    {
        //Inicializamos los valotes
        this.contexto = context;
        this.IDPlantilla = idplantilla;
        this.Listado_Alumnos = Alumnos;
        this.Contador = 0;
    }

    @Override
    public int getCount() {
        return this.Listado_Alumnos.size();
    }

    @Override
    public Object getItem(int i) {
        return this.Listado_Alumnos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view == null)
        {
            LayoutInflater inflate = (LayoutInflater) this.contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(this.IDPlantilla, null);

            TextView txtNum = (TextView) view.findViewById(R.id.txtNum);
            TextView txtCodigo = (TextView) view.findViewById(R.id.lblCodigo);
            TextView txtNombre = (TextView) view.findViewById(R.id.txtNombre);
            TextView txtEdad = (TextView) view.findViewById(R.id.txtEdad);

            Alumno Objeto = this.Listado_Alumnos.get(i);

            //VAlidamos que exista el producto
            if (Objeto != null)
            {
                Contador++;

               // txtNum.setText(String.valueOf(Contador));
                txtNum.setText(String.valueOf(Contador));
                txtCodigo.setText(Objeto.getCodigo());
                txtNombre.setText(Objeto.getNombre());
                txtEdad.setText(String.valueOf(Objeto.getEdad()));
            }
        }

        return view;
    }
}
