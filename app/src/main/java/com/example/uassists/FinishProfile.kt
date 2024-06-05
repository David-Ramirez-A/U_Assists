package com.example.uassists

import Controler.UsarioControler
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Spinner
import android.widget.Toast


class FinishProfile : AppCompatActivity() {
    lateinit var btnBack: ImageButton
    lateinit var btnFinish: Button
    lateinit var txtTelefono :EditText
    lateinit var spinnerCarreras: Spinner
    lateinit var spinnerGeneros: Spinner
    lateinit var spinnerProvincias: Spinner
    lateinit var spinnerCantones: Spinner
    lateinit var spinnerDistritos: Spinner

    //Declaracion del controler donde se comunica con la base de datos y con el modelo
    private lateinit var usuarioControler: UsarioControler

    private lateinit var adapter_cantones: ArrayAdapter<String>
    private lateinit var adapter_distritos: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Lo siguiente permite que la app se extienda por completo en la pantalla
        enableEdgeToEdge()
        setContentView(R.layout.activity_finish_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnBack = findViewById(R.id.btnBack)
        btnFinish = findViewById(R.id.btnFinish)
        txtTelefono = findViewById(R.id.txtTelefono)

        //Inicialización del objeto del controler
        usuarioControler = UsarioControler()

        //Recibimiento de la informacion que estaba en la pantalla anterior
        val nombre = intent.getStringExtra("nombre").toString()
        val apellido = intent.getStringExtra("apellido").toString()
        val email = intent.getStringExtra("email").toString()
        val contraseña = intent.getStringExtra("contraseña").toString()
        val tipoUsuario = intent.getStringExtra("tipoUsuario").toString().toInt()

        //Apartado de los spinners
        spinnerCarreras = findViewById(R.id.spinner_carrera)
        spinnerGeneros = findViewById(R.id.spinner_genero)
        spinnerProvincias = findViewById(R.id.spinner_provincia)
        spinnerCantones = findViewById(R.id.spinner_canton)
        spinnerDistritos = findViewById(R.id.spinner_distrito)

        val carreras = resources.getStringArray(R.array.carreras)
        val generos = resources.getStringArray(R.array.generos)
        val provincias = resources.getStringArray(R.array.provincias)
        val cantones = resources.getStringArray(R.array.cantones_San_Jose).toMutableList()
        val distritos = resources.getStringArray(R.array.distritos_San_Jose).toMutableList()

        val adapter_carreras = ArrayAdapter(this,android.R.layout.simple_spinner_item,carreras)
        adapter_carreras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val adapter_generos = ArrayAdapter(this,android.R.layout.simple_spinner_item,generos)
        adapter_generos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val adapter_provincias = ArrayAdapter(this,android.R.layout.simple_spinner_item,provincias)
        adapter_provincias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter_cantones = ArrayAdapter(this,android.R.layout.simple_spinner_item,cantones)
        adapter_cantones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter_distritos = ArrayAdapter(this,android.R.layout.simple_spinner_item,distritos)
        adapter_distritos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCarreras.adapter = adapter_carreras
        spinnerGeneros.adapter = adapter_generos
        spinnerProvincias.adapter = adapter_provincias
        spinnerCantones.adapter = adapter_cantones
        spinnerDistritos.adapter = adapter_distritos

        //Metodos de funcionamiento de los botones
        spinnerCarreras.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = carreras[position]
                //Toast.makeText(this@FinishProfile,"$selectedItem", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerGeneros.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long
            ) {
                val selectedItem = generos[position]
                //Toast.makeText(this@FinishProfile,"$selectedItem", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerProvincias.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long
            ) {
                val selectedItem = provincias[position]
                if (selectedItem == "San José")
                {
                    actualizarCantones(R.array.cantones_San_Jose)
                    actualizarDistritos(R.array.distritos_San_Jose)
                }
                if (selectedItem == "Alajuela")
                {
                    actualizarCantones(R.array.cantones_Alajuela)
                    actualizarDistritos(R.array.distritos_Alajuelita)
                }
                if (selectedItem == "Cartago")
                {
                    actualizarCantones(R.array.cantones_Cartago)
                    actualizarDistritos(R.array.distritos_Cartago)
                }
                if (selectedItem == "Heredia")
                {
                    actualizarCantones(R.array.cantones_Heredia)
                    actualizarDistritos(R.array.distritos_Heredia)
                }
                if (selectedItem == "Guanacaste")
                {
                    actualizarCantones(R.array.cantones_Guanacaste)
                    actualizarDistritos(R.array.distritos_Liberia)
                }
                if (selectedItem == "Puntarenas")
                {
                    actualizarCantones(R.array.cantones_Puntarenas)
                    actualizarDistritos(R.array.distritos_Puntarenas)
                }
                if (selectedItem == "Limón")
                {
                    actualizarCantones(R.array.cantones_Limón)
                    actualizarDistritos(R.array.distritos_Limón)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerCantones.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long
            ) {
                val selectedItem = cantones[position]
                val provincia = spinnerProvincias.selectedItem
                if (provincia == "San José")
                {
                    if (selectedItem == "San José")
                    {
                        actualizarDistritos(R.array.distritos_San_Jose)
                    }
                    if (selectedItem == "Escazú")
                    {
                        actualizarDistritos(R.array.distritos_Escazú)
                    }
                    if (selectedItem == "Desamparados")
                    {
                        actualizarDistritos(R.array.distritos_Desamparados)
                    }
                    if (selectedItem == "Puriscal")
                    {
                        actualizarDistritos(R.array.distritos_Puriscal)
                    }
                    if (selectedItem == "Tarrazú")
                    {
                        actualizarDistritos(R.array.distritos_Tarrazú)
                    }
                    if (selectedItem == "Aserrí")
                    {
                        actualizarDistritos(R.array.distritos_Aserrí)
                    }
                    if (selectedItem == "Mora")
                    {
                        actualizarDistritos(R.array.distritos_Mora)
                    }
                    if (selectedItem == "Goicoechea")
                    {
                        actualizarDistritos(R.array.distritos_Goicoechea)
                    }
                    if (selectedItem == "Santa Ana")
                    {
                        actualizarDistritos(R.array.distritos_Santa_Ana)
                    }
                    if (selectedItem == "Alajuelita")
                    {
                        actualizarDistritos(R.array.distritos_Alajuelita)
                    }
                    if (selectedItem == "Vázquez de Coronado")
                    {
                        actualizarDistritos(R.array.distritos_Vazquez_de_Coronado)
                    }
                    if (selectedItem == "Acosta")
                    {
                        actualizarDistritos(R.array.distritos_Acosta)
                    }
                    if (selectedItem == "Tibás")
                    {
                        actualizarDistritos(R.array.distritos_Tibás)
                    }
                    if (selectedItem == "Moravia")
                    {
                        actualizarDistritos(R.array.distritos_Moravia)
                    }
                    if (selectedItem == "Montes de Oca")
                    {
                        actualizarDistritos(R.array.distritos_Montes_de_Oca)
                    }
                    if (selectedItem == "Turrubares")
                    {
                        actualizarDistritos(R.array.distritos_Turrubares)
                    }
                    if (selectedItem == "Dota")
                    {
                        actualizarDistritos(R.array.distritos_Dota)
                    }
                    if (selectedItem == "Curridabat")
                    {
                        actualizarDistritos(R.array.distritos_Curridabat)
                    }
                    if (selectedItem == "Pérez Zeledón")
                    {
                        actualizarDistritos(R.array.distritos_Pérez_Zeledón)
                    }
                    if (selectedItem == "León Cortés Castro")
                    {
                        actualizarDistritos(R.array.distritos_León_Cortés_Castro)
                    }
                }
                if (provincia == "Alajuela")
                {
                    if (selectedItem == "Alajuela")
                    {
                        actualizarDistritos(R.array.distritos_Alajuelita)
                    }
                    if (selectedItem == "San Ramón")
                    {
                        actualizarDistritos(R.array.distritos_San_Ramón)
                    }
                    if (selectedItem == "Grecia")
                    {
                        actualizarDistritos(R.array.distritos_Grecia)
                    }
                    if (selectedItem == "San Mateo")
                    {
                        actualizarDistritos(R.array.distritos_San_Mateo)
                    }
                    if (selectedItem == "Atenas")
                    {
                        actualizarDistritos(R.array.distritos_Atenas)
                    }
                    if (selectedItem == "Naranjo")
                    {
                        actualizarDistritos(R.array.distritos_Naranjo)
                    }
                    if (selectedItem == "Palmares")
                    {
                        actualizarDistritos(R.array.distritos_Palmares)
                    }
                    if (selectedItem == "Poás")
                    {
                        actualizarDistritos(R.array.distritos_Poás)
                    }
                    if (selectedItem == "Orotina")
                    {
                        actualizarDistritos(R.array.distritos_Orotina)
                    }
                    if (selectedItem == "San Carlos")
                    {
                        actualizarDistritos(R.array.distritos_San_Carlos)
                    }
                    if (selectedItem == "Zarcero")
                    {
                        actualizarDistritos(R.array.distritos_Zarcero)
                    }
                    if (selectedItem == "Sarchí")
                    {
                        actualizarDistritos(R.array.distritos_Sarchí)
                    }
                    if (selectedItem == "Upala")
                    {
                        actualizarDistritos(R.array.distritos_Upala)
                    }
                    if (selectedItem == "Los Chiles")
                    {
                        actualizarDistritos(R.array.distritos_Los_Chiles)
                    }
                    if (selectedItem == "Guatuso")
                    {
                        actualizarDistritos(R.array.distritos_Guatuso)
                    }
                    if (selectedItem == "Río Cuarto")
                    {
                        actualizarDistritos(R.array.distritos_Río_Cuarto)
                    }
                }
                if (provincia == "Cartago")
                {
                    if (selectedItem == "Cartago")
                    {
                        actualizarDistritos(R.array.distritos_Cartago)
                    }
                    if (selectedItem == "Paraíso")
                    {
                        actualizarDistritos(R.array.distritos_Paraíso)
                    }
                    if (selectedItem == "La Unión")
                    {
                        actualizarDistritos(R.array.distritos_La_Unión)
                    }
                    if (selectedItem == "Jiménez")
                    {
                        actualizarDistritos(R.array.distritos_Jiménez)
                    }
                    if (selectedItem == "Turrialba")
                    {
                        actualizarDistritos(R.array.distritos_Turrialba)
                    }
                    if (selectedItem == "Alvarado")
                    {
                        actualizarDistritos(R.array.distritos_Alvarado)
                    }
                    if (selectedItem == "Oreamuno")
                    {
                        actualizarDistritos(R.array.distritos_Oreamuno)
                    }
                    if (selectedItem == "El Guarco")
                    {
                        actualizarDistritos(R.array.distritos_El_Guarco)
                    }                }
                if (provincia == "Heredia")
                {
                    if (selectedItem == "Heredia")
                    {
                        actualizarDistritos(R.array.distritos_Heredia)
                    }
                    if (selectedItem == "Barva")
                    {
                        actualizarDistritos(R.array.distritos_Barva)
                    }
                    if (selectedItem == "Santo Domingo")
                    {
                        actualizarDistritos(R.array.distritos_Santo_Domingo)
                    }
                    if (selectedItem == "Santa Bárbara")
                    {
                        actualizarDistritos(R.array.distritos_Santa_Bárbara)
                    }
                    if (selectedItem == "San Rafael")
                    {
                        actualizarDistritos(R.array.distritos_San_Rafael)
                    }
                    if (selectedItem == "San Isidro")
                    {
                        actualizarDistritos(R.array.distritos_San_Isidro)
                    }
                    if (selectedItem == "Belén")
                    {
                        actualizarDistritos(R.array.distritos_Belén)
                    }
                    if (selectedItem == "Flores")
                    {
                        actualizarDistritos(R.array.distritos_Flores)
                    }
                    if (selectedItem == "San Pablo")
                    {
                        actualizarDistritos(R.array.distritos_San_Pablo)
                    }
                    if (selectedItem == "Sarapiquí")
                    {
                        actualizarDistritos(R.array.distritos_Sarapiquí)
                    }
                }
                if (provincia == "Guanacaste")
                {
                    if (selectedItem == "Liberia")
                    {
                        actualizarDistritos(R.array.distritos_Liberia)
                    }
                    if (selectedItem == "Nicoya")
                    {
                        actualizarDistritos(R.array.distritos_Nicoya)
                    }
                    if (selectedItem == "Santa Cruz")
                    {
                        actualizarDistritos(R.array.distritos_Santa_Cruz)
                    }
                    if (selectedItem == "Bagaces")
                    {
                        actualizarDistritos(R.array.distritos_Bagaces)
                    }
                    if (selectedItem == "Carrillo")
                        {
                        actualizarDistritos(R.array.distritos_Carrillo)
                    }
                    if (selectedItem == "Cañas")
                        {
                        actualizarDistritos(R.array.distritos_Cañas)
                    }
                    if (selectedItem == "Abangares")
                    {
                        actualizarDistritos(R.array.distritos_Abangares)
                    }
                    if (selectedItem == "Tilarán")
                    {
                        actualizarDistritos(R.array.distritos_Tilarán)
                    }
                    if (selectedItem == "Nandayure")
                    {
                        actualizarDistritos(R.array.distritos_Nandayure)
                    }
                    if (selectedItem == "La Cruz")
                    {
                        actualizarDistritos(R.array.distritos_La_Cruz)
                    }
                    if (selectedItem == "Hojancha")
                    {
                        actualizarDistritos(R.array.distritos_Hojancha)
                    }
                }
                if (provincia == "Puntarenas")
                {
                    if (selectedItem == "Puntarenas")
                    {
                        actualizarDistritos(R.array.distritos_Puntarenas)
                    }
                    if (selectedItem == "Esparza")
                    {
                        actualizarDistritos(R.array.distritos_Esparza)
                    }
                    if (selectedItem == "Buenos Aires")
                    {
                        actualizarDistritos(R.array.distritos_Buenos_Aires)
                    }
                    if (selectedItem == "Montes de Oro")
                    {
                        actualizarDistritos(R.array.distritos_Montes_de_Oro)
                    }
                    if (selectedItem == "Osa")
                    {
                        actualizarDistritos(R.array.distritos_Osa)
                    }
                    if (selectedItem == "Quepos")
                    {
                        actualizarDistritos(R.array.distritos_Quepos)
                    }
                    if (selectedItem == "Golfito")
                    {
                        actualizarDistritos(R.array.distritos_Golfito)
                    }
                    if (selectedItem == "Coto Brus")
                    {
                        actualizarDistritos(R.array.distritos_Coto_Brus)
                    }
                    if (selectedItem == "Parrita")
                    {
                        actualizarDistritos(R.array.distritos_Parrita)
                    }
                    if (selectedItem == "Corredores")
                    {
                        actualizarDistritos(R.array.distritos_Corredores)
                    }
                    if (selectedItem == "Garabito")
                    {
                        actualizarDistritos(R.array.distritos_Garabito)
                    }
                    if (selectedItem == "Monteverde")
                    {
                        actualizarDistritos(R.array.distritos_Monteverde)
                    }
                }
                if (provincia == "Limón")
                {
                    if (selectedItem == "Limón")
                    {
                        actualizarDistritos(R.array.distritos_Limón)
                    }
                    if (selectedItem == "Pococí")
                    {
                        actualizarDistritos(R.array.distritos_Pococí)
                    }
                    if (selectedItem == "Siquirres")
                    {
                        actualizarDistritos(R.array.distritos_Siquirres)
                    }
                    if (selectedItem == "Talamanca")
                    {
                        actualizarDistritos(R.array.distritos_Talamanca)
                    }
                    if (selectedItem == "Matina")
                    {
                        actualizarDistritos(R.array.distritos_Matina)
                    }
                    if (selectedItem == "Guácimo")
                    {
                        actualizarDistritos(R.array.distritos_Guácimo)
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerDistritos.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long
            ) {
                val selectedItem = distritos[position]
                //Toast.makeText(this@FinishProfile,"$selectedItem", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //Metodos de funcionamiento de los botones
        btnBack.setOnClickListener {
            val intent = Intent(this, registro::class.java)
            startActivity(intent)
            finish()
        }

        btnFinish.setOnClickListener {
            val telefonoSeleccionado = txtTelefono.text.toString()
            val carreraSeleccionada = spinnerCarreras.selectedItem.toString()
            val generoSeleccionado = spinnerGeneros.selectedItem.toString()
            val provinciaSeleccionada = spinnerProvincias.selectedItem.toString()
            val cantonSeleccionada = spinnerCantones.selectedItem.toString()
            val distritoSeleccionado = spinnerDistritos.selectedItem.toString()
            if (telefonoSeleccionado.isNotEmpty())
            {
                usuarioControler.insertarUsuario(nombre,apellido,email,contraseña,telefonoSeleccionado,carreraSeleccionada,
                    generoSeleccionado,provinciaSeleccionada,cantonSeleccionada,distritoSeleccionado,tipoUsuario)
            }
            else
            {
                Toast.makeText(this, "Se debe digitar el numero de telefono", Toast.LENGTH_SHORT).show()
                txtTelefono.error = "Falta el número de telefono"
            }
            val intent = Intent(this, registroExitoso::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun actualizarCantones(ruta: Int)
    {
        val cantones = resources.getStringArray(ruta).toMutableList()
        spinnerCantones.setSelection(0)
        adapter_cantones.clear()
        adapter_cantones.addAll(cantones)
        adapter_cantones.notifyDataSetChanged()
    }

    private fun actualizarDistritos(ruta: Int)
    {
        val distritos = resources.getStringArray(ruta).toMutableList()
        adapter_distritos.clear()
        adapter_distritos.addAll(distritos)
        adapter_distritos.notifyDataSetChanged()
    }
}