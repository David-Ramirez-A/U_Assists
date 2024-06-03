package com.example.uassists

import Controler.ResenaControler
import Controler.UsarioControler
import Modelo.Resena
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class teacher_selected : AppCompatActivity() {
    lateinit var btnVolverLista: ImageButton
    lateinit var  btnCalificar: Button
    lateinit var txtPerfil: TextView
    lateinit var lblCalificacionPerfil: TextView

    //Esta parte se declara la plantilla para la lista de usuarios
    lateinit var recyclerView: RecyclerView

    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var resenaControler: ResenaControler
    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var usuarioControler: UsarioControler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Lo siguiente permite que la app se extienda por completo en la pantalla
        enableEdgeToEdge()
        setContentView(R.layout.activity_teacher_selected)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnVolverLista = findViewById(R.id.btnVolverListaT)
        btnCalificar = findViewById(R.id.btnCalificar)
        txtPerfil = findViewById(R.id.lblPerfil)
        lblCalificacionPerfil = findViewById(R.id.lblCalificaciónPerfil)

        //Inicialización del objeto del controler de reseña
        resenaControler = ResenaControler()
        //Inicialización del objeto del controler de usuario
        usuarioControler = UsarioControler()

        //Conexion que el activity de la plantilla
        recyclerView = findViewById(R.id.recycleViewResena)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        //Recibimiento de la informacion que estaba en la pantalla anterior
        val usuario = intent.getStringExtra("Usuario").toString()
        val tipoUsuario = intent.getStringExtra("tipoUsuario").toString()
        val perfilSeleccionado = intent.getStringExtra("perfil").toString()

        fun cargarPerfil()
        {
            val usuarioPerfil = usuarioControler.getUsuario(perfilSeleccionado)
            val textPerfil = "Nombre: ${usuarioPerfil?.nombre} ${usuarioPerfil?.apellido} \n" +
                    "Teléfono: ${usuarioPerfil?.telefono} \n" +
                    "Email: ${usuarioPerfil?.email} \n" +
                    "Carrera: ${usuarioPerfil?.carrera} \n" +
                    "Género: ${usuarioPerfil?.genero} \n" +
                    "Dirección: ${usuarioPerfil?.provincia} ${usuarioPerfil?.canton} ${usuarioPerfil?.distrito}\n"
            txtPerfil.text = textPerfil
            lblCalificacionPerfil.text = resenaControler.getPromedio(usuarioPerfil?.email.toString())

            //Se cargan las reseñas mediante la plantilla del recyclerView
            recyclerView.adapter = AdapterResena(perfilSeleccionado)
        }

        //Metodos de funcionamiento de los botones
        btnVolverLista.setOnClickListener {
            val intent = Intent(this, tutorList::class.java)
            intent.putExtra("Usuario", usuario)
            intent.putExtra("tipoUsuario", tipoUsuario)
            startActivity(intent)
            finish()
        }

        btnCalificar.setOnClickListener {
            val usuarioPerfil = usuarioControler.getUsuario(usuario)
            val intent = Intent(this, review::class.java)
            intent.putExtra("Usuario", usuario)
            intent.putExtra("tipoUsuario", tipoUsuario)
            intent.putExtra("perfil", perfilSeleccionado)
            intent.putExtra("nombre", usuarioPerfil?.nombre.toString())
            startActivity(intent)
            finish()
        }

        //Se llama el método para cargar el perfil
        cargarPerfil()
    }
}