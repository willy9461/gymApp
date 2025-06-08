package com.example.primerapruebagym

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

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
            if(validarDatos()){
                val intentar = Intent(this, registroExitosoActivity::class.java)
                startActivity(intentar)
            }
            else {
                Toast.makeText(this,"Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validarDatos(): Boolean{
        var valido:Boolean
        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val editTextApellido = findViewById<EditText>(R.id.editTextApellido)
        val radioGroupGenero = findViewById<RadioGroup>(R.id.radioGroupGenero)
        val editTextEdad = findViewById<EditText>(R.id.editTextEdad)
        val editTextDni = findViewById<EditText>(R.id.editTextDni)
        val radioGroupEsSocio = findViewById<RadioGroup>(R.id.radioGroupEsSocio)
        if(
            editTextNombre.text.isNotEmpty()
            && editTextApellido.text.isNotEmpty()
            && radioGroupGenero.checkedRadioButtonId != -1
            && editTextEdad.text.isNotEmpty()
            && editTextDni.text.isNotEmpty()
            && radioGroupEsSocio.checkedRadioButtonId != -1
        ){
            valido = true
        }
        else{
            valido = false
        }
        return valido
    }
}