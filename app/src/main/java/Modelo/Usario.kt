package Modelo

import com.google.firebase.firestore.PropertyName
import org.jetbrains.annotations.PropertyKey

class Usario
{
    @get:PropertyName("nombre")
    @set:PropertyName("nombre")
    var nombre : String

    @get:PropertyName("apellido")
    @set:PropertyName("apellido")
    var apellido : String

    @get:PropertyName("email")
    @set:PropertyName("email")
    var email : String

    @get:PropertyName("contrasena")
    @set:PropertyName("contrasena")
    var contrasena : String

    @get:PropertyName("telefono")
    @set:PropertyName("telefono")
    var telefono : String

    @get:PropertyName("carrera")
    @set:PropertyName("carrera")
    var carrera : String

    @get:PropertyName("genero")
    @set:PropertyName("genero")
    var genero : String

    @get:PropertyName("provincia")
    @set:PropertyName("provincia")
    var provincia : String

    @get:PropertyName("canton")
    @set:PropertyName("canton")
    var canton : String

    @get:PropertyName("distrito")
    @set:PropertyName("distrito")
    var distrito : String

    @get:PropertyName("tipousuario")
    @set:PropertyName("tipousuario")
    var tipousuario : Int
    //El tipo de usuario se define de la siguiente forma
    // 1 = Estudiante particular
    // 2 = Tutor
    // a futurio se pueden seguir agregando tipos como administrados, etc.

    constructor(nombre : String, apellido : String, email : String, contrasena : String, telefono : String, carrera : String,
                genero : String, provincia : String, canton : String, distrito : String, tipousuario : Int) {
        this.nombre = nombre
        this.apellido = apellido
        this.email = email
        this.contrasena = contrasena
        this.telefono = telefono
        this.carrera = carrera
        this.genero = genero
        this.provincia = provincia
        this.canton = canton
        this.distrito = distrito
        this.tipousuario = tipousuario
    }
    constructor() {
        this.nombre = ""
        this.apellido = ""
        this.email = ""
        this.contrasena = ""
        this.telefono = ""
        this.carrera = ""
        this.genero = ""
        this.provincia = ""
        this.canton = ""
        this.distrito = ""
        this.tipousuario = 0
    }
}
