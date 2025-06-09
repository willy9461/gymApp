package com.example.primerapruebagym

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class RegistrarSocioActivity : AppCompatActivity() {
    private lateinit var dbHelper: UserDBHelper
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
        dbHelper = UserDBHelper(this)
        val buttonAgregarSocio = findViewById<Button>(R.id.buttonAgregarSocio)
        buttonAgregarSocio.setOnClickListener {
            if(validarDatos()){
                if(cargarSocio()){
                    val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
                    val editTextApellido = findViewById<EditText>(R.id.editTextApellido)
                    val editTextEdad = findViewById<EditText>(R.id.editTextEdad)
                    val editTextDni = findViewById<EditText>(R.id.editTextDni)

                    editTextNombre.text.clear()
                    editTextApellido.text.clear()
                    editTextEdad.text.clear()
                    editTextDni.text.clear()
                    val intentar = Intent(this, registroExitosoActivity::class.java)
                    startActivity(intentar)
                }
                else {
                    Toast.makeText(this,"No se pudo guardar", Toast.LENGTH_SHORT).show()
                }
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

    private fun cargarSocio(): Boolean{
        val nombre = findViewById<EditText>(R.id.editTextNombre).text.toString()
        val apellido = findViewById<EditText>(R.id.editTextApellido).text.toString()
        val radioGroupGenero = findViewById<RadioGroup>(R.id.radioGroupGenero).checkedRadioButtonId
        val edad = findViewById<EditText>(R.id.editTextEdad).text.toString().toInt()
        val dni = findViewById<EditText>(R.id.editTextDni).text.toString().toInt()
        val radioGroupEsSocio = findViewById<RadioGroup>(R.id.radioGroupEsSocio).checkedRadioButtonId
        val genero = findViewById<RadioButton>(radioGroupGenero).text.toString()
        var socio:Int
        if(findViewById<RadioButton>(radioGroupEsSocio).text.toString() == "Sí"){
            socio = 1
        }
        else{
            socio = 0
        }
        return dbHelper.cargarSocio(nombre, apellido, genero, edad, dni, socio)
    }
}