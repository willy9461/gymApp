package com.example.primerapruebagym

data class Socio(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val genero: String,
    val edad: Int,
    val dni: Int,
    val socio: Int // 1 = sí, 0 = no
)
