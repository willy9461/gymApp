package com.example.primerapruebagym

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ListarSociosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_socios)

        val btnRegistrarSocio = findViewById<Button>(R.id.buttonRegistrarSocio)
        val btnVolverAtras = findViewById<Button>(R.id.buttonVolverAtras)

        btnRegistrarSocio.setOnClickListener {
            val intent = Intent(this, RegistrarSocioActivity::class.java)
            startActivity(intent)
        }

        btnVolverAtras.setOnClickListener {
            finish()
        }
    }
}
