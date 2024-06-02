package com.example.uassists

import Controler.ResenaControler
import Controler.UsarioControler
import Modelo.Usuario
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.core.view.View

class AdapterClass(val listaUsuarios: List<Usuario>?, val usuarioActivo: String, val tipoUsuario: String, private val context: Context, private val activity: Activity): RecyclerView.Adapter<AdapterClass.ViewHolderClass>()
{
    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var recenaControler: ResenaControler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return  ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return listaUsuarios!!.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val usurioActual = listaUsuarios?.get(position)
        //Inicialización del objeto del controler
        recenaControler = ResenaControler()

        val calificacion = recenaControler.getPromedio(usurioActual?.email.toString())
        holder.lblNombre.text = usurioActual!!.nombre +" "+usurioActual.apellido
        holder.lblCurso.text = usurioActual.carrera
        holder.lblCalificacion.text = calificacion
        holder.llUsuario.contentDescription = usurioActual.email

        holder.llUsuario.setOnClickListener{
            val perfilUsuario = holder.llUsuario.contentDescription.toString()
            val intent = Intent(context, teacher_selected::class.java)
            intent.putExtra("Usuario", usuarioActivo)
            intent.putExtra("tipoUsuario", tipoUsuario)
            intent.putExtra("perfil",perfilUsuario)
            context.startActivity(intent)
            activity.finish()
        }
    }

    class ViewHolderClass(itemView: android.view.View): RecyclerView.ViewHolder(itemView) {
        val llUsuario: LinearLayout = itemView.findViewById(R.id.llUsuario)
        val lblNombre: TextView = itemView.findViewById(R.id.nombre1)
        val lblCurso: TextView = itemView.findViewById(R.id.curso1)
        val lblCalificacion: TextView = itemView.findViewById(R.id.calificacion1)
    }
}