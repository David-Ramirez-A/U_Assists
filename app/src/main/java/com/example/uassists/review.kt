package com.example.uassists

import Controler.ResenaControler
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class review : AppCompatActivity()
{
    lateinit var btnVolver: ImageButton
    lateinit var btnEnviar: Button
    lateinit var txtResena: EditText

    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var resenaControler: ResenaControler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Lo siguiente permite que la app se extienda por completo en la pantalla
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recibimiento de la informacion que estaba en la pantalla anterior
        val usuario = intent.getStringExtra("Usuario").toString()
        val tipoUsuario = intent.getStringExtra("tipoUsuario").toString()
        val perfilSeleccionado = intent.getStringExtra("perfil").toString()
        val nombre = intent.getStringExtra("nombre").toString()

        //Inicialización del objeto del controler de reseña
        resenaControler = ResenaControler()

        //Conexion de botones con el activity
        btnVolver = findViewById(R.id.btnVolverTutor)
        btnEnviar = findViewById(R.id.btnEnviarResena)
        //Conexion del cuadro de texto con el activity
        txtResena = findViewById((R.id.txtFeedBack))
        //Spinner
        val spinnerNumeros: Spinner = findViewById(R.id.spinner_numero)
        val numeros = resources.getStringArray(R.array.numeros)
        val adapter_numeros = ArrayAdapter(this,android.R.layout.simple_spinner_item,numeros)
        adapter_numeros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerNumeros.adapter = adapter_numeros

        //Metodos de funcionamiento de los botones
        btnVolver.setOnClickListener {
            val intent = Intent(this, teacher_selected::class.java)
            intent.putExtra("Usuario", usuario)
            intent.putExtra("tipoUsuario", tipoUsuario)
            intent.putExtra("perfil", perfilSeleccionado)
            startActivity(intent)
            finish()
        }

        btnEnviar.setOnClickListener {
            val resenaText = txtResena.text.toString()
            if (resenaText.isNotEmpty())
            {
                val calificacion = spinnerNumeros.selectedItem.toString()
                if (resenaControler.existeResena(nombre))
                {
                    resenaControler.actualizarResena(perfilSeleccionado,nombre,calificacion,resenaText)
                }
                else
                {
                    resenaControler.insertarResena(perfilSeleccionado,nombre,calificacion,resenaText)
                }
                val intent = Intent(this, teacher_selected::class.java)
                intent.putExtra("Usuario", usuario)
                intent.putExtra("tipoUsuario", tipoUsuario)
                intent.putExtra("perfil", perfilSeleccionado)
                startActivity(intent)
                finish()
            }
            else
            {
                txtResena.error = "Debe escribir un mensaje para dejar su reseña"
                Toast.makeText(this,"Reseña incompleta",Toast.LENGTH_SHORT)
            }
        }
    }
}