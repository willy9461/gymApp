package com.example.primerapruebagym

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonRegistrarSocio = findViewById<Button>(R.id.registrarSocio)
        buttonRegistrarSocio.setOnClickListener {
            val intent = Intent(this, RegistrarSocioActivity::class.java)
            startActivity(intent)
        }
        val buttonCobrarCuota = findViewById<Button>(R.id.cobrarCuota)
        buttonCobrarCuota.setOnClickListener {
            val intent = Intent(this, CobrarCuotaActivity::class.java)
            startActivity(intent)
        }
        val buttonEmitirCarnet = findViewById<Button>(R.id.emitirCarnet)
        buttonEmitirCarnet.setOnClickListener {
            val intent = Intent(this, EmitirCarnetActivity::class.java)
            startActivity(intent)
        }
        val btnListarSocios = findViewById<Button>(R.id.listarSocios)
        btnListarSocios.setOnClickListener {
            val intent = Intent(this, ListarSociosActivity::class.java)
            startActivity(intent)
        }

        val btnHoyVencen = findViewById<Button>(R.id.hoyVencen)
        btnHoyVencen.setOnClickListener {
            val intent = Intent(this, HoyVencenActivity::class.java)
            startActivity(intent)
        }
        val cerrarSesion = findViewById<Button>(R.id.cerrarSesion)
        cerrarSesion.setOnClickListener {
            finish()
        }
    }
}