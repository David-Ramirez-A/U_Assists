package com.example.uassists

import Controler.ResenaControler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//class AdapterResena(private val listaResenas: List<Resena>?): RecyclerView.Adapter<AdapterResena.ViewHolderClassResena>()
class AdapterResena(private val perfil: String): RecyclerView.Adapter<AdapterResena.ViewHolderClassResena>() {
    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var resenaControler: ResenaControler

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClassResena {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.resena_layout,parent,false)
        return ViewHolderClassResena(itemView)
    }
    override fun getItemCount(): Int {
        //Inicialización del objeto del controler de reseña
        resenaControler = ResenaControler()
        val listaResenas = resenaControler.getResenasUsuario(perfil)
        if (listaResenas.isNullOrEmpty())
        {
            return 1 //retorna 1 solomente para que si genere un layout diciendo que no hay reseñas
        }
        else
        {
            return listaResenas!!.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolderClassResena, position: Int) {
        //Inicialización del objeto del controler de reseña
        resenaControler = ResenaControler()
        val listaResenas = resenaControler.getResenasUsuario(perfil)
        if (listaResenas.isNullOrEmpty())
        {
            holder.llResena.contentDescription = "Sin reseñas"
            holder.lblNombreReviewer.text = "No hay reseñas aun"
            holder.lblFeedback.text = " "
            holder.lblGrade.text = " "
        }
        else
        {
            val resenaActual = listaResenas?.get(position)
            holder.llResena.contentDescription = "Reseña de ${resenaActual?.evaluador}"
            holder.lblNombreReviewer.text = resenaActual?.evaluador
            holder.lblFeedback.text = resenaActual?.evaluacion
            holder.lblGrade.text = resenaActual?.calificacion
        }
    }

    class ViewHolderClassResena(itemView: android.view.View): RecyclerView.ViewHolder(itemView) {
        val llResena: LinearLayout = itemView.findViewById(R.id.llResena)
        val lblNombreReviewer: TextView = itemView.findViewById(R.id.nombreReviewer)
        val lblFeedback: TextView = itemView.findViewById(R.id.feedback)
        val lblGrade: TextView = itemView.findViewById(R.id.grade)
    }
}