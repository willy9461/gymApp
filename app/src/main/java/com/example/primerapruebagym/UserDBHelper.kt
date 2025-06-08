package com.example.primerapruebagym

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDBHelper(context: Context): SQLiteOpenHelper(context, "UsuariosDB", null, 2) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE usuarios(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario TEXT UNIQUE,
                contrasenia TEXT
            )
        """.trimIndent())
        db.execSQL("INSERT INTO usuarios (usuario, contrasenia) VALUES ('admin', '1234')")
        db.execSQL("""
            CREATE TABLE socios(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                apellido TEXT,
                genero TEXT,
                edad INTEGER,
                dni INTEGER,
                socio INTEGER
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate()
    }

    fun login(usuario: String, contrasenia: String): Boolean{
        var db = readableDatabase
        var cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE usuario=? AND contrasenia=?",
            arrayOf(usuario, contrasenia)
            )
        var existe = cursor.count > 0
        return existe
    }

    fun cargarSocio(nombre:String, apellido:String, genero:String, edad:Int, dni:Int, socio:Int): Boolean{
        val db = writableDatabase
        val valores = ContentValues().apply {
            put("nombre", nombre)
            put("apellido", apellido)
            put("genero", genero)
            put("edad", edad)
            put("dni", dni)
            put("socio", socio)
        }
        val insertar = db.insert("socios", null, valores)
        return insertar != -1L
    }
}