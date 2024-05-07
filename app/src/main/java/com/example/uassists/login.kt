package com.example.uassists

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var btnVolver: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnIngresarLogin)
        btnVolver = findViewById(R.id.btnVolverAIncio)

        btnLogin.setOnClickListener {
            val intent = Intent(this, tutorList::class.java)
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