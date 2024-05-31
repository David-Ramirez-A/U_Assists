package Controler

import Modelo.Usuario
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import java.util.concurrent.ExecutionException

class UsarioControler
{
    private var usuario: Usuario
    private val baseDatos: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val coleccionUsuarios: CollectionReference = baseDatos.collection("usuarios")
    constructor()
    {
        usuario = Usuario()
    }

    fun insertarUsuario(nombre : String, apellido : String, email : String, contrasena : String, telefono : String, carrera : String,
                        genero : String, provincia : String, canton : String, distrito : String, tipousuario : Int)
    {
        usuario = Usuario(nombre, apellido, email, contrasena, telefono, carrera, genero, provincia, canton, distrito, tipousuario)
        baseDatos.collection("usuarios").document(email)
            .set(usuario)
            //Indicar que se logro registrar exitosamente
            .addOnSuccessListener {
                println("Usuario agregado con éxito")
            }
            .addOnFailureListener { e ->
                // Error al agregar el usuario
                println("Error al agregar el usuario: $e")
            }
    }

    fun existeUsuario(email:String, callback: (Boolean)->Unit)
    {
        val userDocRef: DocumentReference = coleccionUsuarios.document(email)
        userDocRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful)
            {
                val document: DocumentSnapshot? = task.result
                if (document != null && document.exists())
                {
                    callback(true)
                }
                else
                {
                    callback(false)
                }
            } else {
                // Handle error
                callback(false)
            }
        }
    }

    fun usuarioCorrecto(email: String, contrasena: String) : Boolean
    {
        val solicitud = coleccionUsuarios.whereEqualTo("email", email).limit(1).get()
        val solicitudResultante = runBlocking { solicitud.await() }
        if(!solicitudResultante.isEmpty)
        {
            val usuarioSolicitud = solicitudResultante.documents[0]
            usuario = usuarioSolicitud.toObject(Usuario::class.java)!!
            return usuario.contrasena.equals(contrasena)
        }
        return false
    }

    fun getUsuariosTipo(tipoUsuario: Int): List<Usuario>?
    {
        val solicitud = coleccionUsuarios.whereEqualTo("tipousuario", tipoUsuario).get()
        val solicitudResultante = runBlocking { solicitud.await() }
        if(!solicitudResultante.isEmpty)
        {
            val listaUsuarios = mutableListOf<Usuario>()
            for (document in solicitudResultante.documents) {
                val nuevoUsuario = document.toObject<Usuario>()
                if (nuevoUsuario != null)
                {
                    listaUsuarios.add(nuevoUsuario)
                }
            }
            return listaUsuarios
        }
        return null
    }
    fun listaEstudiantes(): List<Usuario>?
    {
        return getUsuariosTipo(1)
    }

    fun listaTutores(): List<Usuario>?
    {
        return getUsuariosTipo(2)
    }

    fun getUsuario(email: String): Usuario?
    {
        val solicitud = coleccionUsuarios.whereEqualTo("email", email).limit(1).get()
        val solicitudResultante = runBlocking { solicitud.await() }
        if(!solicitudResultante.isEmpty)
        {
            val usuarioSolicitud = solicitudResultante.documents[0]
            usuario = usuarioSolicitud.toObject(Usuario::class.java)!!
            return usuario
        }
        return null
    }

    fun cambioContraseña(email: String, nuevaContrasena: String)
    {
        coleccionUsuarios.whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                if (!querySnapshot.isEmpty)
                {
                    val documentSnapshot = querySnapshot.documents[0]
                    val documentId = documentSnapshot.id
                    coleccionUsuarios.document(documentId)
                        .update("contrasena", nuevaContrasena)
                        .addOnSuccessListener { println("Contraseña actualizada exitosamente.") }
                        .addOnFailureListener { e -> println("Error actualizando la contraseña: ${e.message}") }
                }
                else
                {
                    println("No se encontró ningún usuario con el email proporcionado.")
                }
            }
            .addOnFailureListener { e ->
                println("Error obteniendo el usuario: ${e.message}")
            }
    }
}