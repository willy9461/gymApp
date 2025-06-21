package com.example.primerapruebagym

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import java.util.Locale
import java.util.Date

class Fechas {
    public fun sumarMesesATimestamp(timestamp: Long, meses: Int): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = timestamp // Establece el timestamp original
            add(Calendar.MONTH, meses) // Suma los meses
        }
        return calendar.timeInMillis // Devuelve el nuevo timestamp
    }

    public fun timestampAFecha(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date(timestamp)) // Date(timestamp) convierte milisegundos a Date
    }
}