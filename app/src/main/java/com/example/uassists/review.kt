package com.example.uassists

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class review : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //Spinner
        val spinnerNumeros: Spinner = findViewById(R.id.spinner_numero)
        val numeros = resources.getStringArray(R.array.numeros)
        val adapter_numeros = ArrayAdapter(this,android.R.layout.simple_spinner_item,numeros)
        adapter_numeros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNumeros.adapter = adapter_numeros
    }
}