package Controler

import Modelo.Usuario
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import Modelo.Resena

class ResenaControler
{
    private var resena: Resena
    private val baseDatos: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val coleccionUsuarios: CollectionReference = baseDatos.collection("usuarios")
    constructor()
    {
        resena = Resena()
    }
}