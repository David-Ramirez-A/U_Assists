package Controler

import Modelo.Usario
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class UsarioControler
{
    private var usuario: Usario
    private val baseDatos: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val coleccionUsuarios: CollectionReference = baseDatos.collection("usuarios")
    constructor()
    {
        usuario = Usario()
    }

    fun insertarUsuario(nombre : String, apellido : String, email : String, contrasena : String, telefono : String, carrera : String,
                        genero : String, provincia : String, canton : String, distrito : String, tipousuario : Int)
    {
        usuario = Usario(nombre, apellido, email, contrasena, telefono, carrera, genero, provincia, canton, distrito, tipousuario)
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
        if(!solicitudResultante.isEmpty())
        {
            val usuarioSolicitud = solicitudResultante.documents[0]
            usuario = usuarioSolicitud.toObject(Usario::class.java)!!
            return usuario.contrasena.equals(contrasena)
        }
        return false
    }

    fun tutores(): MutableList<Usario> {
        val listTutores : MutableList<Usario> = mutableListOf()

        return listTutores
    }
}