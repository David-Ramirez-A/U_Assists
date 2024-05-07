package com.example.uassists

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class registro : AppCompatActivity()
{
    lateinit var btnRegistrarUsuario: Button
    lateinit var btnVolver: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario)
        btnVolver = findViewById(R.id.btnVolverALogin)

        btnRegistrarUsuario.setOnClickListener {
            val intent = Intent(this, registroExitoso::class.java)
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
            insets*/
    }
}