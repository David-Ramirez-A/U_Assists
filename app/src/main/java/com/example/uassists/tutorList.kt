package com.example.uassists

import Controler.UsarioControler
import Modelo.Usuario
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class tutorList : AppCompatActivity() {
    lateinit var btnBuscar: ImageButton
    lateinit var btnPerfil: ImageButton
    lateinit var btnLogOut: ImageButton
    lateinit var lblTitulo: TextView
    lateinit var llTutor1: LinearLayout

    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var usuarioControler: UsarioControler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Lo siguiente permite que la app se extienda por completo en la pantalla
        enableEdgeToEdge()
        setContentView(R.layout.activity_tutor_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnBuscar = findViewById(R.id.btnBuscar)
        btnPerfil = findViewById(R.id.btnPerfil)
        btnLogOut = findViewById(R.id.btnLogOut)
        lblTitulo = findViewById(R.id.lblTitulo)
        llTutor1 = findViewById(R.id.llTutor1)

        //Recibimiento de la informacion que estaba en la pantalla anterior
        val usuario = intent.getStringExtra("Usuario").toString()
        val tipoUsuario = intent.getStringExtra("tipoUsuario").toString()

        //Inicialización del objeto del controler
        usuarioControler = UsarioControler()

        @SuppressLint("SetTextI18n")
        fun cargarLista()
        {
            if(tipoUsuario=="1")//Estudiante
            {
                lblTitulo.text = "Tutores disponibles"
                val listaTutores =  usuarioControler.listaTutores()
                val tamanoLista = listaTutores?.size
                for(i in 0 until tamanoLista!!)
                {
                    val usuarioTutor = listaTutores[i]

                }

            }
            if(tipoUsuario=="2")//Tutor
            {
                lblTitulo.text = "Estudiantes disponibles"
                val listaEstudiantes = usuarioControler.listaEstudiantes()
                val tamanoLista = listaEstudiantes?.size
                for(i in 0 until tamanoLista!!)
                {
                    val usurioEstudiante = listaEstudiantes[i]

                }
            }
        }

        btnBuscar.setOnClickListener {
            val intent = Intent(this, tutorList::class.java)
            intent.putExtra("Usuario", usuario)
            intent.putExtra("tipoUsuario", tipoUsuario)
            startActivity(intent)
            finish()
        }

        btnPerfil.setOnClickListener {
            val intent = Intent(this, ProfileView::class.java)
            startActivity(intent)
            finish()
        }

        btnLogOut.setOnClickListener {
            val intent = Intent(this, inicio::class.java)
            startActivity(intent)
            finish()
        }

        llTutor1.setOnClickListener {
            val intent = Intent(this, teacher_selected::class.java)
            startActivity(intent)
            finish()
        }

        cargarLista()
    }
}