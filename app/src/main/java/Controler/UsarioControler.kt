package Controler

import Modelo.Usario
import com.google.firebase.firestore.FirebaseFirestore
class UsarioControler
{
    private var usuario: Usario
    constructor()
    {
        usuario = Usario()
    }

    fun insertarUsuario(nombre : String, apellido : String, email : String, contrasena : String, telefono : String, carrera : String,
                        genero : String, provincia : String, canton : String, distrito : String, tipousuario : Int)
    {
        usuario = Usario(nombre, apellido, email, contrasena, telefono, carrera, genero, provincia, canton, distrito, tipousuario)
        val baseDatos = FirebaseFirestore.getInstance()

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

    fun existeUsuario(email:String)
    {

    }

    fun usuarioCorrecto(email: String,contrasena: String)
    {

    }

    fun tutores(): MutableList<Usario> {
        val listTutores : MutableList<Usario> = mutableListOf()

        return listTutores
    }
}