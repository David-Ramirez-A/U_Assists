package Modelo

import com.google.firebase.firestore.PropertyName

class Resena
{
    @get:PropertyName("evaluado")
    @set:PropertyName("evaluado")
    var evaluado : String

    @get:PropertyName("evaluador")
    @set:PropertyName("evaluador")
    var evaluador : String

    @get:PropertyName("calificacion")
    @set:PropertyName("calificacion")
    var calificacion : String

    @get:PropertyName("evaluacion")
    @set:PropertyName("evaluacion")
    var evaluacion : String

    constructor(evaluado:String, evaluador:String, calificacion:String, evaluacion:String)
    {
        this.evaluado = evaluado
        this.evaluador = evaluador
        this.calificacion = calificacion
        this.evaluacion = evaluacion
    }

    constructor()
    {
        this.evaluado = ""
        this.evaluador = ""
        this.calificacion = ""
        this.evaluacion = ""
    }
}