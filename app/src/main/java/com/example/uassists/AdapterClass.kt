package com.example.uassists

import Controler.UsarioControler
import Modelo.Usuario
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.core.view.View

class AdapterClass(val listaUsuarios: List<Usuario>?): RecyclerView.Adapter<AdapterClass.ViewHolderClass>()
{
    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var usuarioControler: UsarioControler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return  ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return listaUsuarios!!.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val usurioActual = listaUsuarios?.get(position)
        val calificacion = ""

        holder.lblNombre.text = usurioActual!!.nombre +" "+usurioActual.apellido
        holder.lblCurso.text = usurioActual.carrera
        holder.lblCalificacion.text = calificacion
        holder.llUsuario.contentDescription = usurioActual.email
    }

    class ViewHolderClass(itemView: android.view.View): RecyclerView.ViewHolder(itemView) {
        val llUsuario: LinearLayout = itemView.findViewById(R.id.llUsuario)
        val lblNombre: TextView = itemView.findViewById(R.id.nombre1)
        val lblCurso: TextView = itemView.findViewById(R.id.curso1)
        val lblCalificacion: TextView = itemView.findViewById(R.id.calificacion1)
    }
}