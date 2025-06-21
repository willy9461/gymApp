package com.example.primerapruebagym

import android.os.Bundle
import android.widget.Button
import android.content.Intent      // <- Este import es importante
import android.widget.EditText
import android.widget.Toast
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
        val dbHelper = UserDBHelper(this)

        val buttonVolverAtras = findViewById<Button>(R.id.buttonVolverAtras)
        buttonVolverAtras.setOnClickListener {
            finish()
        }

        // Este es el código que faltaba
        val buttonCobrarCuota = findViewById<Button>(R.id.buttonCobrarCuota)
        buttonCobrarCuota.setOnClickListener {
            val editTextNroSocio = findViewById<EditText>(R.id.editTextDocumento)
            val editTextMonto = findViewById<EditText>(R.id.editTextMonto)
            val dni = editTextNroSocio.text.trim().toString().toInt()
            val vencimiento = dbHelper.pagarCuota(dni)
            if (vencimiento != 0.toLong() && editTextMonto.text.isNotEmpty()){
                val intent = Intent(this, CobroExitosoActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"No se encontró socio o falta monto", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
