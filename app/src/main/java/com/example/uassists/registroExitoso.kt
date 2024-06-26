package com.example.uassists

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class registroExitoso : AppCompatActivity() {
    lateinit var btnVolverExito: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Lo siguiente permite que la app se extienda por completo en la pantalla
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_exitoso)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnVolverExito = findViewById(R.id.btnVolverExito)

        //Metodos de funcionamiento de los botones
        btnVolverExito.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }
    }
}