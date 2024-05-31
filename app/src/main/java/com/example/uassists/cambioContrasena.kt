package com.example.uassists

import Controler.UsarioControler
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class cambioContrasena : AppCompatActivity() {
    lateinit var btnCambiar: Button
    lateinit var btnVolver: ImageButton
    lateinit var txtEmailCambio: EditText
    lateinit var txtContraseñaNueva: EditText
    lateinit var txtConfirmacionContrasenaNueva: EditText

    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var usuarioControler: UsarioControler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Lo siguiente permite que la app se extienda por completo en la pantalla
        enableEdgeToEdge()
        setContentView(R.layout.activity_cambio_contrasena)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Botones
        btnCambiar = findViewById(R.id.btnCambiarContraseña)
        btnVolver = findViewById(R.id.btnVolverALogin2)

        //Cuadros de texto
        txtEmailCambio = findViewById(R.id.txtEmailCambio)
        txtContraseñaNueva = findViewById(R.id.txtNuevaContrasena)
        txtConfirmacionContrasenaNueva = findViewById(R.id.txtConfirmarNueva)

        //Inicialización del objeto del controler
        usuarioControler = UsarioControler()

        btnCambiar.setOnClickListener {
            val emailUsuario = txtEmailCambio.text.toString()
            val password = txtContraseñaNueva.text.toString()
            val confirmationPassword = txtConfirmacionContrasenaNueva.text.toString()

            usuarioControler.existeUsuario(emailUsuario){ exists ->
                if (exists)
                {
                    if (password == confirmationPassword)
                    {
                        usuarioControler.cambioContraseña(emailUsuario,password)
                        val intent = Intent(this, cambioExitoso::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else
                    {
                        txtContraseñaNueva.error = "La contraseña no coincide con la confirmación"
                        txtConfirmacionContrasenaNueva.error = "La contraseña no coincide con la confirmación"
                        Toast.makeText(this, "No coinciden las contraseñas", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    txtEmailCambio.error = "El email no se encuentra registrado"
                    Toast.makeText(this, "Error de usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnVolver.setOnClickListener {
            val intent = Intent(this, inicio::class.java)
            startActivity(intent)
            finish()
        }
    }
}