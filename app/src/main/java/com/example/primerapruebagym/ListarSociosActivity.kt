package com.example.primerapruebagym
import android.widget.LinearLayout
import android.widget.TextView
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ListarSociosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_socios)

        val btnRegistrarSocio = findViewById<Button>(R.id.buttonRegistrarSocio)
        val btnVolverAtras = findViewById<Button>(R.id.buttonVolverAtras)
        val sociosContainer = findViewById<LinearLayout>(R.id.layoutSociosContainer)

        btnRegistrarSocio.setOnClickListener {
            val intent = Intent(this, RegistrarSocioActivity::class.java)
            startActivity(intent)
        }
        btnVolverAtras.setOnClickListener { finish() }

        // --- Mostrar todos los socios dinámicamente ---
        mostrarSocios(sociosContainer)
    }

    override fun onResume() {
        super.onResume()
        val sociosContainer = findViewById<LinearLayout>(R.id.layoutSociosContainer)
        mostrarSocios(sociosContainer)
    }

    private fun mostrarSocios(container: LinearLayout) {
        container.removeAllViews()
        val dbHelper = UserDBHelper(this)
        val socios = dbHelper.obtenerSociosObjeto()
        val fecha = Fechas()
        for (socio in socios) {
            val cuadro = layoutInflater.inflate(R.layout.item_socio, container, false)
            cuadro.findViewById<TextView>(R.id.textNroSocio).text = "NRO SOCIO: ${socio.id}"
            cuadro.findViewById<TextView>(R.id.textDni).text = "DNI: ${socio.dni}"
            cuadro.findViewById<TextView>(R.id.textNombre).text = "NOMBRE: ${socio.nombre}"
            cuadro.findViewById<TextView>(R.id.textApellido).text = "APELLIDO: ${socio.apellido}"
            if (socio.vencimiento != 0.toLong()){
                cuadro.findViewById<TextView>(R.id.textVencimiento).text = "VENCIMIENTO: ${fecha.timestampAFecha(socio.vencimiento)}"
            }
            else {
                cuadro.findViewById<TextView>(R.id.textVencimiento).text = "VENCIMIENTO: -"
            }
            container.addView(cuadro)
        }
    }
}
