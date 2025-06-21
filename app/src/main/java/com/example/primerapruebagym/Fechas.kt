package com.example.primerapruebagym

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import java.util.Locale

class Fechas {
    public fun sumarMesesATimestamp(timestamp: Long, meses: Int): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = timestamp // Establece el timestamp original
            add(Calendar.MONTH, meses) // Suma los meses
        }
        return calendar.timeInMillis // Devuelve el nuevo timestamp
    }
}