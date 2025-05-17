package com.example.primerapruebagym

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent

class RegistrarSocioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registar_socio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonVolverAtras = findViewById<Button>(R.id.buttonVolverAtras)
        buttonVolverAtras.setOnClickListener {
            finish()
        }
        val buttonAgregarSocio = findViewById<Button>(R.id.buttonAgregarSocio)
        buttonAgregarSocio.setOnClickListener {
            val intentar = Intent(this, registroExitosoActivity::class.java)
            startActivity(intentar)
        }
    }
}