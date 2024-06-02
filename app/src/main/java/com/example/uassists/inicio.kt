package com.example.uassists

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.ai.client.generativeai.type.content

class inicio : AppCompatActivity()
{
    lateinit var btnRegistrar: Button
    lateinit var btnIngresar: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //Lo siguiente permite que la app se extienda por completo en la pantalla
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnIngresar = findViewById(R.id.btnIngresar)

        //Metodos de funcionamiento de los botones
        btnRegistrar.setOnClickListener {
            val intent = Intent(this, registro::class.java)
            startActivity(intent)
            finish()
        }

        btnIngresar.setOnClickListener {
            val intent = Intent(this, login::class.java)
            //intent.putExtra("jugador", nombre)
            startActivity(intent)
            finish()
        }
    }
}