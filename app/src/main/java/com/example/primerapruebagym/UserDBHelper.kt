package com.example.primerapruebagym

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDBHelper(context: Context): SQLiteOpenHelper(context, "ClubDB", null, 4) {
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
                socio INTEGER,
                vencimiento INTEGER
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
            put("vencimiento", System.currentTimeMillis())
        }
        val insertar = db.insert("socios", null, valores)
        return insertar != -1L
    }


    fun obtenerSocios(): List<String> {
        val socios = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM socios", null)
        // Solo agregá el encabezado una vez:
        socios.add("Nombre\tApellido\tEdad\tDNI\tSocio")
        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido"))
                val edad = cursor.getInt(cursor.getColumnIndexOrThrow("edad"))
                val dni = cursor.getInt(cursor.getColumnIndexOrThrow("dni"))
                val socio = cursor.getInt(cursor.getColumnIndexOrThrow("socio"))
                val socioStr = if (socio == 1) "Sí" else "No"
                socios.add("$nombre\t$apellido\t$edad\t$dni\t$socioStr")
            } while (cursor.moveToNext())
        }
        cursor.close()
        return socios
    }

    fun obtenerSociosObjeto(): List<Socio> {
        val lista = mutableListOf<Socio>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM socios", null)
        if (cursor.moveToFirst()) {
            do {
                val columnVencimineto = cursor.getColumnIndexOrThrow("vencimiento")
                val vencimiento = if (cursor.isNull(columnVencimineto)) {
                    0
                } else {
                    cursor.getLong(columnVencimineto)
                }
                val socio = Socio(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                    genero = cursor.getString(cursor.getColumnIndexOrThrow("genero")),
                    edad = cursor.getInt(cursor.getColumnIndexOrThrow("edad")),
                    dni = cursor.getInt(cursor.getColumnIndexOrThrow("dni")),
                    socio = cursor.getInt(cursor.getColumnIndexOrThrow("socio")),
                    vencimiento = vencimiento
                )
                lista.add(socio)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    fun pagarCuota(dni: Int) :Long{
        val timestampActual = System.currentTimeMillis()
        val fecha = Fechas()
        var nuevaFecha = fecha.sumarMesesATimestamp(timestampActual, 1)
        val db = writableDatabase
        val valores = ContentValues().apply {
            put("vencimiento", nuevaFecha)
        }
        val filasActualizadas = db.update(
            "socios",
            valores,
            "dni = ?",
            arrayOf(dni.toString())
        )
        if (filasActualizadas != 1){
            nuevaFecha = 0
        }
        return nuevaFecha
    }

    fun vencenHoy(): List<Socio>{
        val fecha = Fechas()
        val (inicio, fin) = fecha.rangoTimestampHoy()
        val lista = mutableListOf<Socio>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM socios WHERE vencimiento BETWEEN ? AND ?", arrayOf(inicio.toString(), fin.toString()))
        if (cursor.moveToFirst()) {
            do {
                val columnVencimineto = cursor.getColumnIndexOrThrow("vencimiento")
                val vencimiento = if (cursor.isNull(columnVencimineto)) {
                    0
                } else {
                    cursor.getLong(columnVencimineto)
                }
                val socio = Socio(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                    genero = cursor.getString(cursor.getColumnIndexOrThrow("genero")),
                    edad = cursor.getInt(cursor.getColumnIndexOrThrow("edad")),
                    dni = cursor.getInt(cursor.getColumnIndexOrThrow("dni")),
                    socio = cursor.getInt(cursor.getColumnIndexOrThrow("socio")),
                    vencimiento = vencimiento
                )
                lista.add(socio)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }
}