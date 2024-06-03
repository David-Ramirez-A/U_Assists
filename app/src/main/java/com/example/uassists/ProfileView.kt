package com.example.uassists

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileView : AppCompatActivity() {
    lateinit var btnVolverLista: ImageButton
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

        //Recibimiento de la informacion que estaba en la pantalla anterior
        val usuario = intent.getStringExtra("Usuario").toString()
        val tipoUsuario = intent.getStringExtra("tipoUsuario").toString()

        //Metodos de funcionamiento de los botones
        btnVolverLista.setOnClickListener {
            val intent = Intent(this, bienvenidos::class.java)
            intent.putExtra("Usuario", usuario)
            intent.putExtra("tipoUsuario", tipoUsuario)
            startActivity(intent)
            finish()
        }

    }
}