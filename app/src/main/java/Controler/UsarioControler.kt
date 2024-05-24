package Controler

import Modelo.Usario
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
    }
}