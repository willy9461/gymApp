package com.example.primerapruebagym

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HoyVencenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoy_vencen)

        val btnVolverAtras = findViewById<Button>(R.id.buttonVolverAtras)
        btnVolverAtras.setOnClickListener {
            finish()
        }
    }
}
