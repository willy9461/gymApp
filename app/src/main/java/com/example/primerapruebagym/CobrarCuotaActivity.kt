package com.example.primerapruebagym

import android.os.Bundle
import android.widget.Button
import android.content.Intent      // <- Este import es importante
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CobrarCuotaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cobrar_cuota)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonVolverAtras = findViewById<Button>(R.id.buttonVolverAtras)
        buttonVolverAtras.setOnClickListener {
            finish()
        }

        // Este es el código que faltaba
        val buttonCobrarCuota = findViewById<Button>(R.id.buttonCobrarCuota)
        buttonCobrarCuota.setOnClickListener {
            val intent = Intent(this, CobroExitosoActivity::class.java)
            startActivity(intent)
        }
    }
}
