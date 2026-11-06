package com.example.calibradorvoltaje

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalculadoraViewModel : ViewModel() {

    private val _estado = MutableStateFlow(EstadoCalculadora())

    val estado: StateFlow<EstadoCalculadora> = _estado.asStateFlow()

    fun onVoltajeEntradaChanged(nuevoVoltaje: String) {
        _estado.update { it.copy(voltajeEntrada = nuevoVoltaje) }
        calcularVoltajeSalida()
    }

    fun onResistencia1Changed(nuevaR1: String) {
        _estado.update { it.copy(resistencia1 = nuevaR1) }
        calcularVoltajeSalida()
    }

    fun onResistencia2Changed(nuevaR2: String) {
        _estado.update { it.copy(resistencia2 = nuevaR2) }
        calcularVoltajeSalida()
    }

    fun calcularVoltajeSalida() {
        val currentState = _estado.value

        if (currentState.voltajeEntrada.isEmpty() &&
            currentState.resistencia1.isEmpty() &&
            currentState.resistencia2.isEmpty()
        ) {

            _estado.update {
                it.copy(
                    voltajeSalida = null,
                    mensajeAlerta = null
                )
            }
            return
        }

        val vin = currentState.voltajeEntrada.toFloatOrNull() ?: 0f
        val r1 = currentState.resistencia1.toFloatOrNull() ?: 0f
        val r2 = currentState.resistencia2.toFloatOrNull() ?: 0f

        if (r1 < 0f || r2 < 0f) {
            _estado.update {
                it.copy(
                    voltajeSalida = null,
                    mensajeAlerta = "Las resistencias no pueden tener valores negativos."
                )
            }
            return
        }

        if ((r1 + r2) == 0f) {
            _estado.update {
                it.copy(
                    voltajeSalida = null,
                    mensajeAlerta = "¡Peligro de Cortocircuito! Ambas resistencias son cero."
                )
            }
            return
        }

        val resultado = vin * (r2 / (r1 + r2))

        _estado.update {
            it.copy(
                voltajeSalida = resultado,
                mensajeAlerta = null
            )
        }
    }
}