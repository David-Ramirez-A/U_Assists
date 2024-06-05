package com.example.uassists

import Controler.UsarioControler
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class registro : AppCompatActivity()
{
    lateinit var btnRegistrarUsuario: Button
    lateinit var btnVolver: ImageButton
    lateinit var txtNombre: EditText
    lateinit var txtApellido: EditText
    lateinit var txtEmailRegistro: EditText
    lateinit var txtContrasenaRegistro: EditText
    lateinit var txtConfirmarContrasenaRegistro: EditText
    lateinit var rgTipoUsuario: RadioGroup
    lateinit var rbTutor: RadioButton
    lateinit var rbEstudiante: RadioButton

    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var usuarioControler: UsarioControler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Lo siguiente permite que la app se extienda por completo en la pantalla
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Botnoes
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario)
        btnVolver = findViewById(R.id.btnVolverALogin)

        //Cuadros de texto
        txtNombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtEmailRegistro = findViewById(R.id.txtEmailRegistro)
        txtContrasenaRegistro = findViewById(R.id.txtContrasenaRegistro)
        txtConfirmarContrasenaRegistro = findViewById(R.id.txtConfirmarContrasena)
        rgTipoUsuario = findViewById(R.id.rgTipoUsuario)

        //Radio buttons
        rbTutor = findViewById(R.id.rbTutor)
        rbEstudiante = findViewById(R.id.rbEstudiante)

        //Inicialización del objeto del controler
        usuarioControler = UsarioControler()

        fun validarCorreo(email: String): Boolean
        {
            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
            return email.matches(emailRegex)
        }

        fun validarPassword(password: String): Boolean
        {
            // Definimos los criterios mínimos de seguridad para la contraseña
            val tamanoMinimo = 8
            val mayuscula = "[A-Z]".toRegex()
            val minuscula = "[a-z]".toRegex()
            val digito = "[0-9]".toRegex()
            val caracterEspecial = "[^A-Za-z0-9]".toRegex()

            // Verificamos que la contraseña cumpla con todos los criterios
            return password.length >= tamanoMinimo &&
                    mayuscula.containsMatchIn(password) &&
                    minuscula.containsMatchIn(password) &&
                    digito.containsMatchIn(password) &&
                    caracterEspecial.containsMatchIn(password)
        }

        //Metodos de funcionamiento de los botones
        btnRegistrarUsuario.setOnClickListener {
            val nombre = txtNombre.text.toString()
            val apellido = txtApellido.text.toString()
            val emailRegistro = txtEmailRegistro.text.toString()
            val contrasenaRegistro = txtContrasenaRegistro.text.toString()
            val confirmarContrasenaRegistro = txtConfirmarContrasenaRegistro.text.toString()
            var tipoUsuario = ""
            if(rbTutor?.isChecked == true)
            {
                tipoUsuario = "2"
            }
            if(rbEstudiante?.isChecked == true)
            {
                tipoUsuario = "1"
            }

            if(nombre.isNotEmpty() || apellido.isNotEmpty())
            {
                if(nombre.isNotEmpty())
                {
                    if(apellido.isNotEmpty())
                    {
                        if(emailRegistro.isNotEmpty())
                        {
                            if(validarCorreo(emailRegistro))
                            {
                                if (contrasenaRegistro.isNotEmpty())
                                {
                                    if(validarPassword(contrasenaRegistro))
                                    {
                                        if (confirmarContrasenaRegistro.isNotEmpty())
                                        {
                                            if (tipoUsuario.isNotEmpty())
                                            {
                                                if (contrasenaRegistro == confirmarContrasenaRegistro)
                                                {
                                                    usuarioControler.existeUsuario(emailRegistro) { exists ->
                                                        if (exists) {
                                                            Toast.makeText(this, "Ya hay una cuenta con ese Email", Toast.LENGTH_SHORT).show()
                                                            txtEmailRegistro.error = "Ya hay una cuenta con ese Email"
                                                        }
                                                        else
                                                        {
                                                            val intent = Intent(this, FinishProfile::class.java)
                                                            intent.putExtra("nombre", nombre)
                                                            intent.putExtra("apellido", apellido)
                                                            intent.putExtra("email", emailRegistro)
                                                            intent.putExtra("contraseña", contrasenaRegistro)
                                                            intent.putExtra("tipoUsuario", tipoUsuario)
                                                            startActivity(intent)
                                                            finish()
                                                        }
                                                    }
                                                }
                                                else
                                                {
                                                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT)
                                                    txtContrasenaRegistro.error = "La contraseña no coincide"
                                                    txtConfirmarContrasenaRegistro.error = "La contraseña no coincide"
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(this, "Debe seleccionar un tipo de usuario", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                        else
                                        {
                                            Toast.makeText(this, "Debe llenar el campo de confirmar contraseña", Toast.LENGTH_SHORT).show()
                                            txtConfirmarContrasenaRegistro.error = "Debe de confirmar su contraseña"
                                        }
                                    }
                                    else
                                    {
                                        Toast.makeText(this, "Contraseña invalida", Toast.LENGTH_SHORT).show()
                                        txtContrasenaRegistro.error = "La contraseña no es segura"
                                    }
                                }
                                else
                                {
                                    Toast.makeText(this, "Debe llenar el campo de contraseña", Toast.LENGTH_SHORT).show()
                                    txtContrasenaRegistro.error = "Debe digitar su contraseña"
                                }
                            }
                            else
                            {
                                Toast.makeText(this, "Email invalido", Toast.LENGTH_SHORT).show()
                                txtEmailRegistro.error = "Email invalido"
                            }
                        }
                        else
                        {
                            Toast.makeText(this, "Debe llenar el campo de Email", Toast.LENGTH_SHORT).show()
                            txtEmailRegistro.error = "Debe de digitar su Email"
                        }
                    }
                    else
                    {
                        Toast.makeText(this, "Debe llenar el campo de apellido", Toast.LENGTH_SHORT).show()
                        txtApellido.error = "Debe de digitar su apeliido"
                    }
                }
                else
                {
                    Toast.makeText(this, "Debe llenar el campo de nombre", Toast.LENGTH_SHORT).show()
                    txtNombre.error = "Debe de digitar su nombre"
                }
            }
            else
            {
                Toast.makeText(this, "Debe llenar el todos los campos", Toast.LENGTH_SHORT).show()
                txtNombre.error = "Debe de digitar su nombre"
                txtApellido.error = "Debe de digitar su apeliido"
                txtEmailRegistro.error = "Debe de digitar su Email"
                txtContrasenaRegistro.error = "Debe digitar su contraseña"
                txtConfirmarContrasenaRegistro.error = "Debe de confirmar su contraseña"
            }
        }

        btnVolver.setOnClickListener {
            val intent = Intent(this, inicio::class.java)
            startActivity(intent)
            finish()
        }
    }
}