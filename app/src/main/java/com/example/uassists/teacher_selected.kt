package com.example.uassists

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class teacher_selected : AppCompatActivity() {
    lateinit var btnVolverLista: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_selected)

        btnVolverLista = findViewById(R.id.btnVolverListaT)

        btnVolverLista.setOnClickListener {
            val intent = Intent(this, tutorList::class.java)
            startActivity(intent)
            finish()
        }
    }
}