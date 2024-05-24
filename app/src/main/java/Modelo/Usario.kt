package Modelo

class Usario
{
    private var nombre : String
        get() {
            return  nombre
        }
        set(value) {nombre = value}

    private var apellido : String
        get() {
            return  apellido
        }
        set(value) {apellido = value}

    private var email : String
        get() {
            return email
        }
        set(value) {email = value}

    private var contrasena : String
        get() {
            return contrasena
        }
        set(value) {contrasena = value}

    private var telefono : String
        get() {
            return telefono
        }
        set(value) {telefono = value}

    private var carrera : String
        get() {
            return carrera
        }
        set(value) {carrera = value}

    private var genero : String
        get() {
            return genero
        }
        set(value) {genero = value}

    private var provincia : String
        get() {
            return provincia
        }
        set(value) {provincia = value}

    private var canton : String
        get() {
            return canton
        }
        set(value) {canton = value}

    private var distrito : String
        get() {
            return distrito
        }
        set(value) {distrito = value}

    private var tipousuario : Int
        get() {
            return tipousuario
        }
        set(value) {tipousuario = value}
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
