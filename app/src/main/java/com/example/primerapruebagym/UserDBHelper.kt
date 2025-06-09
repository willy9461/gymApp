package com.example.primerapruebagym

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDBHelper(context: Context): SQLiteOpenHelper(context, "ClubDB", null, 3) {
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
                dni INTEGER UNIQUE,     
                socio INTEGER
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        db.execSQL("DROP TABLE IF EXISTS socios")
        onCreate(db)
    }

    fun login(usuario: String, contrasenia: String): Boolean{
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE usuario=? AND contrasenia=?",
            arrayOf(usuario, contrasenia)
            )
        val existe = cursor.count > 0
        cursor.close()
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