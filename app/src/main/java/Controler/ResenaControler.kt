package Controler

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import Modelo.Resena
import com.google.firebase.firestore.QuerySnapshot
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
        val numeroRegistro = totalRegistros()+1
        resena = Resena(evaluado,evaluador,calificacion,evaluacion)
        baseDatos.collection("reseñas").document(numeroRegistro.toString())
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
        val listaResenas = mutableListOf<Resena>()
        if(!solicitudResultante.isEmpty)
        {
            for (document in solicitudResultante.documents)
            {
                val nuevaResena = document.toObject<Resena>()
                if (nuevaResena != null)
                {
                    listaResenas.add(nuevaResena)
                }
            }
            return listaResenas
        }
        return listaResenas
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

    fun totalRegistros(): Int
    {
        val solicitud = coleccionResenas.get()
        val solicitudResultante = runBlocking { solicitud.await() }
        val listaResenas = mutableListOf<Resena>()
        if(!solicitudResultante.isEmpty)
        {
            for (document in solicitudResultante.documents)
            {
                val nuevaResena = document.toObject<Resena>()
                if (nuevaResena != null)
                {
                    listaResenas.add(nuevaResena)
                }
            }
        }
        var total = listaResenas.size
        return total
    }

    fun actualizarResena(evaluado:String, evaluador:String, calificacion:String, evaluacion:String)
    {
        val numeroRegistro = getDocumento(evaluador)
        resena = Resena(evaluado,evaluador,calificacion,evaluacion)
        baseDatos.collection("reseñas").document(numeroRegistro)
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

    fun existeResena(evaluador: String): Boolean
    {
        val solicitud = coleccionResenas.get()
        val solicitudResultante = runBlocking { solicitud.await() }
        if(!solicitudResultante.isEmpty)
        {
            for (document in solicitudResultante.documents)
            {
                val nuevaResena = document.toObject<Resena>()
                if (nuevaResena != null)
                {
                    if(nuevaResena?.evaluador == evaluador)
                    {
                        return true
                    }
                }
            }
            return false
        }
        return false
    }

    fun getDocumento(usuario: String): String
    {
        var documentId = ""
        val solicitud = coleccionResenas.whereEqualTo("evaluador", usuario).limit(1).get()
        val solicitudResultante = runBlocking { solicitud.await() }
        if(!solicitudResultante.isEmpty)
        {
            documentId = solicitudResultante.documents[0].id
        }
        return documentId
    }
}

