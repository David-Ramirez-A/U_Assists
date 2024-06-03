package com.example.uassists

import Controler.UsarioControler
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileView : AppCompatActivity() {
    lateinit var btnVolverLista: ImageButton
    lateinit var lblDatosPerfil: TextView

    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var usuarioControler: UsarioControler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Lo siguiente permite que la app se extienda por completo en la pantalla
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnVolverLista = findViewById(R.id.btnVolverLista)
        lblDatosPerfil = findViewById(R.id.lblPerfil)

        //Recibimiento de la informacion que estaba en la pantalla anterior
        val usuario = intent.getStringExtra("Usuario").toString()
        val tipoUsuario = intent.getStringExtra("tipoUsuario").toString()

        //Inicialización del objeto del controler de usuario
        usuarioControler = UsarioControler()

        fun cargarPerfilPropio()
        {
            val usuarioPerfil = usuarioControler.getUsuario(usuario)
            val textPerfil = "Nombre: ${usuarioPerfil?.nombre} ${usuarioPerfil?.apellido} \n" +
                    "Teléfono: ${usuarioPerfil?.telefono} \n" +
                    "Email: ${usuarioPerfil?.email} \n" +
                    "Carrera: ${usuarioPerfil?.carrera} \n" +
                    "Género: ${usuarioPerfil?.genero} \n" +
                    "Dirección: ${usuarioPerfil?.provincia} ${usuarioPerfil?.canton} ${usuarioPerfil?.distrito}\n"
            lblDatosPerfil.text = textPerfil
        }

        //Metodos de funcionamiento de los botones
        btnVolverLista.setOnClickListener {
            val intent = Intent(this, bienvenidos::class.java)
            intent.putExtra("Usuario", usuario)
            intent.putExtra("tipoUsuario", tipoUsuario)
            startActivity(intent)
            finish()
        }

        //Se llama el método para cargar el perfil
        cargarPerfilPropio()
    }
}