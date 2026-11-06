package com.example.calibradorvoltaje

data class EstadoCalculadora(
    val voltajeEntrada: String = "",
    val resistencia1: String = "",
    val resistencia2: String = "",
    val voltajeSalida: Float? = null,
    val mensajeAlerta: String? = null
)