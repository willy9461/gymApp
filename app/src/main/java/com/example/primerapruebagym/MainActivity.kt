package com.example.primerapruebagym

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var dbHelper = UserDBHelper(this)
        val usuario = findViewById<EditText>(R.id.editTextUsername)
        val contrasenia = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val usuarioStr = usuario.text.toString().trim()
            val contraseniaStr = contrasenia.text.toString().trim()
            if(dbHelper.login(usuarioStr, contraseniaStr)){
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Usuario y/o contaseña invalidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}