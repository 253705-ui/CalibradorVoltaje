package com.example.calibradorvoltaje

data class EstadoCalculadora(
    val voltajeEntrada: String = "12.0",
    val resistencia1: String = "1000.0",
    val resistencia2: String = "510.0",
    val voltajeSalida: Float = 0.0f
)