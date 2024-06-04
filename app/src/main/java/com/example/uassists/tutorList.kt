package com.example.uassists

import Controler.UsarioControler
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class tutorList : AppCompatActivity() {
    lateinit var btnBuscar: ImageButton
    lateinit var btnPerfil: ImageButton
    lateinit var btnLogOut: ImageButton
    lateinit var btnBuscarBarra: ImageButton
    lateinit var lblTitulo: TextView
    lateinit var txtCarreraBuscar: EditText

    //Esta parte se declara la plantilla para la lista de usuarios
    lateinit var recyclerView: RecyclerView

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
        btnBuscarBarra = findViewById(R.id.btnBuscarBarra)
        lblTitulo = findViewById(R.id.lblTitulo)
        txtCarreraBuscar = findViewById(R.id.txtCarreraBuscar)

        //Conexion que el activity de la plantilla
        recyclerView = findViewById(R.id.llUsuario)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        //Recibimiento de la informacion que estaba en la pantalla anterior
        val usuario = intent.getStringExtra("Usuario").toString()
        val tipoUsuario = intent.getStringExtra("tipoUsuario").toString()

        //Inicialización del objeto del controler
        usuarioControler = UsarioControler()

        fun cargarLista()
        {
            if(tipoUsuario=="1")//Estudiante
            {
                lblTitulo.text = "Tutores disponibles"
                val listaTutores =  usuarioControler.listaTutores()
                recyclerView.adapter = AdapterClass(listaTutores, usuario, tipoUsuario,this,this)
            }
            if(tipoUsuario=="2")//Tutor
            {
                lblTitulo.text = "Estudiantes disponibles"
                val listaEstudiantes = usuarioControler.listaEstudiantes()
                recyclerView.adapter = AdapterClass(listaEstudiantes, usuario, tipoUsuario,this,this)
            }
        }

        //Metodos de funcionamiento de los botones
        btnBuscar.setOnClickListener {
            val intent = Intent(this, tutorList::class.java)
            intent.putExtra("Usuario", usuario)
            intent.putExtra("tipoUsuario", tipoUsuario)
            startActivity(intent)
            finish()
        }

        btnPerfil.setOnClickListener {
            val intent = Intent(this, ProfileView::class.java)
            intent.putExtra("Usuario", usuario)
            intent.putExtra("tipoUsuario", tipoUsuario)
            startActivity(intent)
            finish()
        }

        btnLogOut.setOnClickListener {
            val intent = Intent(this, inicio::class.java)
            startActivity(intent)
            finish()
        }

        btnBuscarBarra.setOnClickListener {
            val carrera = txtCarreraBuscar.text.toString()
            if (carrera.isNotEmpty()) {
                if (tipoUsuario == "1")//Estudiante
                {
                    lblTitulo.text = "Tutores disponibles"
                    val listaTutores = usuarioControler.listarPorCarrera(tipoUsuario, carrera)
                    recyclerView.adapter =
                        AdapterClass(listaTutores, usuario, tipoUsuario, this, this)
                }
                if (tipoUsuario == "2")//Tutor
                {
                    lblTitulo.text = "Estudiantes disponibles"
                    val listaEstudiantes = usuarioControler.listarPorCarrera(tipoUsuario, carrera)
                    recyclerView.adapter =
                        AdapterClass(listaEstudiantes, usuario, tipoUsuario, this, this)
                }
            }
            else
            {
                if(tipoUsuario=="1")//Estudiante
                {
                    lblTitulo.text = "Tutores disponibles"
                    val listaTutores =  usuarioControler.listaTutores()
                    recyclerView.adapter = AdapterClass(listaTutores, usuario, tipoUsuario,this,this)
                }
                if(tipoUsuario=="2")//Tutor
                {
                    lblTitulo.text = "Estudiantes disponibles"
                    val listaEstudiantes = usuarioControler.listaEstudiantes()
                    recyclerView.adapter = AdapterClass(listaEstudiantes, usuario, tipoUsuario,this,this)
                }
            }
        }
        //Se carla la lista ya sea de tutores o estudiantes
        cargarLista()
    }
}