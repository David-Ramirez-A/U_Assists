package com.example.uassists

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ProfileView : AppCompatActivity() {
    lateinit var btnVolverLista: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_view)

        btnVolverLista = findViewById(R.id.btnVolverLista)

        btnVolverLista.setOnClickListener {
            val intent = Intent(this, tutorList::class.java)
            startActivity(intent)
            finish()
        }

    }
}