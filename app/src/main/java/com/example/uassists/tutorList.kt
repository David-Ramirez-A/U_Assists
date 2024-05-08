package com.example.uassists

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class tutorList : AppCompatActivity() {
    lateinit var btnBuscar: ImageButton
    lateinit var btnPerfil: ImageButton
    lateinit var btnLogOut: ImageButton
    lateinit var llTutor1: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_list)

        btnBuscar = findViewById(R.id.btnBuscar)
        btnPerfil = findViewById(R.id.btnPerfil)
        btnLogOut = findViewById(R.id.btnLogOut)
        llTutor1 = findViewById(R.id.llTutor1)

        btnBuscar.setOnClickListener {
            val intent = Intent(this, tutorList::class.java)
            startActivity(intent)
            finish()
        }

        btnPerfil.setOnClickListener {
            val intent = Intent(this, ProfileView::class.java)
            startActivity(intent)
            finish()
        }

        btnLogOut.setOnClickListener {
            val intent = Intent(this, inicio::class.java)
            startActivity(intent)
            finish()
        }

        llTutor1.setOnClickListener {
            val intent = Intent(this, teacher_selected::class.java)
            startActivity(intent)
            finish()
        }
    }
}