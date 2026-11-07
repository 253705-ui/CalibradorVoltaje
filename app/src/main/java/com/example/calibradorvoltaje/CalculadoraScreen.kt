package com.example.calibradorvoltaje

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CalculadoraScreen(viewModel: CalculadoraViewModel) {
    val estado by viewModel.estado.collectAsState()

    val azulBebe = Color(0xFF89CFF0)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Calibrador de divisores de voltaje",
                style = MaterialTheme.typography.headlineMedium.copy(fontFamily = FontFamily.Serif),
                color = azulBebe,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = estado.resistencia1,
                onValueChange = { viewModel.onResistencia1Changed(it) },
                label = { Text("Resistencia R1 (Ω):", fontFamily = FontFamily.Serif) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = estado.resistencia2,
                onValueChange = { viewModel.onResistencia2Changed(it) },
                label = { Text("Resistencia R2 (Ω):", fontFamily = FontFamily.Serif) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = estado.voltajeEntrada,
                onValueChange = { viewModel.onVoltajeEntradaChanged(it) },
                label = { Text("Voltaje de Entrada Vin (V):", fontFamily = FontFamily.Serif) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            when {
                estado.mensajeAlerta != null -> {
                    Text(
                        text = "⚠️ ${estado.mensajeAlerta}",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily.Serif),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                estado.voltajeSalida != null -> {

                    Text(
                        text = String.format("Vout: %.2f V", estado.voltajeSalida),
                        style = MaterialTheme.typography.headlineMedium.copy(fontFamily = FontFamily.Serif),
                        color = azulBebe,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}