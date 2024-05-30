package Modelo

import com.google.firebase.firestore.PropertyName

class Resena
{
    @get:PropertyName("tutor")
    @set:PropertyName("tutor")
    var tutor : String

    @get:PropertyName("estudiante")
    @set:PropertyName("estudiante")
    var estudiante : String

    @get:PropertyName("calificacion")
    @set:PropertyName("calificacion")
    var calificacion : String

    constructor(tutor:String, estudiante:String, calificacion:String)
    {
        this.tutor = tutor
        this.estudiante = estudiante
        this.calificacion = calificacion
    }

    constructor()
    {
        this.tutor = ""
        this.estudiante = ""
        this.calificacion = ""
    }
}