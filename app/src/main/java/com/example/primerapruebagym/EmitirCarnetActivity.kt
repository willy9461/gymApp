package com.example.primerapruebagym

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EmitirCarnetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emitir_carnet)

        val buttonEmitirCarnet = findViewById<Button>(R.id.buttonEmitirCarnet)
        val buttonVolverAtras = findViewById<Button>(R.id.buttonVolverAtras)
        val editTextNroSocio = findViewById<EditText>(R.id.editTextNroSocio)


        buttonEmitirCarnet.setOnClickListener {
            val intent = Intent(this, EmisionCarnetExitosaActivity::class.java)
            startActivity(intent)
        }

        buttonVolverAtras.setOnClickListener {
            finish()
        }
    }
}
