package com.example.uassists

import Controler.UsarioControler
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var btnContraseña: Button
    lateinit var btnCrearCuenta: Button
    lateinit var btnVolver: ImageButton
    lateinit var txtEmail: TextView
    lateinit var txtContrasena: TextView

    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var usuarioControler: UsarioControler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnIngresarLogin)
        btnContraseña = findViewById(R.id.btnOlvideContrasena)
        btnCrearCuenta = findViewById(R.id.btnCrearcuenta)
        btnVolver = findViewById(R.id.btnVolverAIncio)
        txtEmail = findViewById(R.id.txtEmail)
        txtContrasena = findViewById(R.id.txtContraseña)

        //Inicialización del objeto del controler
        usuarioControler = UsarioControler()

        btnLogin.setOnClickListener {
            val email = txtEmail.text.toString()
            val contrasena = txtContrasena.text.toString()

            if(email.isNotEmpty() && contrasena.isNotEmpty())
            {
                usuarioControler.existeUsuario(email) { exists ->
                    if (exists)
                    //if(email=="a")
                    {
                        //if (email == "a" && contrasena == "1")
                        if(usuarioControler.usuarioCorrecto(email,contrasena))
                        {
                            val intent = Intent(this, tutorList::class.java)
                            intent.putExtra("Usuario", email)
                            startActivity(intent)
                            finish()
                        }
                        else
                        {
                            Toast.makeText(this, "Contraseña invalida", Toast.LENGTH_SHORT).show()
                            txtContrasena.error = "La contraseña no es correcta"
                        }
                    }
                    else
                    {
                        txtEmail.error = "El email no se encuentra registrado"
                        Toast.makeText(this, "Error de usuario", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else
            {
                Toast.makeText(this, "Debe introducir el usuario y contraseña para iniciar secion", Toast.LENGTH_SHORT).show()
                txtEmail.error = "Debe escribir el email"
                txtContrasena.error = "Debe escribir la contraseña"
            }
        }

        btnContraseña.setOnClickListener {
            val intent = Intent(this, cambioContrasena::class.java)
            startActivity(intent)
            finish()
        }

        btnCrearCuenta.setOnClickListener {
            val intent = Intent(this, registro::class.java)
            startActivity(intent)
            finish()
        }

        btnVolver.setOnClickListener {
            val intent = Intent(this, inicio::class.java)
            startActivity(intent)
            finish()
        }
    }
}