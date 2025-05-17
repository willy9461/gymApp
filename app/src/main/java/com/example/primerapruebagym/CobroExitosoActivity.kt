package com.example.primerapruebagym

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent


class CobroExitosoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cobro_exitoso)

        val btnVolverMenu = findViewById<Button>(R.id.buttonVolverMenu)
        btnVolverMenu.setOnClickListener {
            // Ir a MenuActivity y cerrar la pantalla actual
            val intent = Intent(this, MenuActivity::class.java)
            // Opcional: eliminar el stack de actividades previas
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}