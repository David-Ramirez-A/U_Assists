package com.example.uassists

import Controler.UsarioControler
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class bienvenidos : AppCompatActivity()
{
    lateinit var btnBuscar: ImageButton
    lateinit var btnPerfil: ImageButton
    lateinit var btnLogOut: ImageButton

    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var usuarioControler: UsarioControler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bienvenidos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnBuscar = findViewById(R.id.btnBuscar)
        btnPerfil = findViewById(R.id.btnPerfil)
        btnLogOut = findViewById(R.id.btnLogOut)

        //Recibimiento de la informacion que estaba en la pantalla anterior
        val usuario = intent.getStringExtra("Usuario").toString()

        //Inicialización del objeto del controler
        usuarioControler = UsarioControler()

        btnBuscar.setOnClickListener {
            val usuarioDB = usuarioControler.getUsuario(usuario)
            val tipoDeUsuario = usuarioDB?.tipousuario.toString()
            val intent = Intent(this, tutorList::class.java)
            intent.putExtra("Usuario", usuario)
            intent.putExtra("tipoUsuario", tipoDeUsuario)
            startActivity(intent)
            finish()
        }

        btnPerfil.setOnClickListener {
            val intent = Intent(this, ProfileView::class.java)
            intent.putExtra("Usuario", usuario)
            startActivity(intent)
            finish()
        }

        btnLogOut.setOnClickListener {
            val intent = Intent(this, inicio::class.java)
            startActivity(intent)
            finish()
        }
    }
}