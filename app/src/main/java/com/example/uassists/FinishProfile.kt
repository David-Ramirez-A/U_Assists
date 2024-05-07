package com.example.uassists

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Spinner
import android.widget.Toast


class FinishProfile : AppCompatActivity() {
    lateinit var btnBack: Button
    lateinit var btnFinish: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_profile)

        btnBack = findViewById(R.id.btnBack)
        btnFinish = findViewById(R.id.btnFinish)


        btnBack.setOnClickListener {
            val intent = Intent(this, registro::class.java)
            startActivity(intent)
            finish()
        }

        btnFinish.setOnClickListener {
            val intent = Intent(this, tutorList::class.java)
            startActivity(intent)
            finish()
        }

        val spinnerCarreras: Spinner = findViewById(R.id.spinner_carrera)
        val spinnerGeneros: Spinner = findViewById(R.id.spinner_genero)
        val spinnerProvincias: Spinner = findViewById(R.id.spinner_provincia)
        val spinnerCantones: Spinner = findViewById(R.id.spinner_canton)
        val spinnerDistritos: Spinner = findViewById(R.id.spinner_distrito)

        val carreras = resources.getStringArray(R.array.carreras)
        val generos = resources.getStringArray(R.array.generos)
        val provincias = resources.getStringArray(R.array.provincias)
        val cantones = resources.getStringArray(R.array.cantones)
        val distritos = resources.getStringArray(R.array.distritos)

        val adapter_carreras = ArrayAdapter(this,android.R.layout.simple_spinner_item,carreras)
        adapter_carreras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val adapter_generos = ArrayAdapter(this,android.R.layout.simple_spinner_item,generos)
        adapter_generos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val adapter_provincias = ArrayAdapter(this,android.R.layout.simple_spinner_item,provincias)
        adapter_provincias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val adapter_cantones = ArrayAdapter(this,android.R.layout.simple_spinner_item,cantones)
        adapter_cantones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val adapter_distritos = ArrayAdapter(this,android.R.layout.simple_spinner_item,distritos)
        adapter_distritos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCarreras.adapter = adapter_carreras
        spinnerGeneros.adapter = adapter_generos
        spinnerProvincias.adapter = adapter_provincias
        spinnerCantones.adapter = adapter_cantones
        spinnerDistritos.adapter = adapter_distritos

        spinnerCarreras.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = carreras[position]
                Toast.makeText(this@FinishProfile,"$selectedItem", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@FinishProfile,"$selectedItem", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@FinishProfile,"$selectedItem", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@FinishProfile,"$selectedItem", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@FinishProfile,"$selectedItem", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}