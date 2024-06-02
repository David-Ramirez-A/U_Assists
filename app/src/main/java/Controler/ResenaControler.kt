package Controler

import Modelo.Usuario
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import Modelo.Resena
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class ResenaControler
{
    private var resena: Resena
    private val baseDatos: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val coleccionResenas: CollectionReference = baseDatos.collection("reseñas")
    constructor()
    {
        resena = Resena()
    }

    fun insertarResena(evaluado:String, evaluador:String, calificacion:String, evaluacion:String)
    {
        resena = Resena()
        baseDatos.collection("reseñas").document(evaluado)
            .set(resena)
            //Indicar que se logro registrar exitosamente
            .addOnSuccessListener {
                println("La reseña fue agregado con éxito")
            }
            .addOnFailureListener { e ->
                // Error al agregar el usuario
                println("Error al agregar el reseña: $e")
            }
    }

    fun getResenasUsuario(email: String): List<Resena>?
    {
        val solicitud = coleccionResenas.whereEqualTo("evaluado", email).get()
        val solicitudResultante = runBlocking { solicitud.await() }
        if(!solicitudResultante.isEmpty)
        {
            val listaResenas = mutableListOf<Resena>()
            for (document in solicitudResultante.documents) {
                val nuevaResena = document.toObject<Resena>()
                if (nuevaResena != null)
                {
                    listaResenas.add(nuevaResena)
                }
            }
            return listaResenas
        }
        return null
    }

    fun getPromedio(evaluado: String) :String
    {
        var promedio = ""
        val resenas = getResenasUsuario(evaluado)
        if (resenas.isNullOrEmpty())
        {
            return "5"
        }
        else
        {
            val tamano = resenas?.size
            var suma = 0
            for (resena in resenas!!)
            {
                suma += resena.calificacion.toInt()
            }
            promedio = (suma / tamano!!).toString()
            return promedio
        }
    }
}

