package com.example.uassists

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class cambioContrasena : AppCompatActivity() {
    lateinit var btnCambiar: Button
    lateinit var btnVolver: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_cambio_contrasena)

        btnCambiar = findViewById(R.id.btnCambiarContraseña)
        btnVolver = findViewById(R.id.btnVolverALogin2)

        btnCambiar.setOnClickListener {
            val intent = Intent(this, cambioExitoso::class.java)
            startActivity(intent)
            finish()
        }

        btnVolver.setOnClickListener {
            val intent = Intent(this, inicio::class.java)
            startActivity(intent)
            finish()
        }
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}