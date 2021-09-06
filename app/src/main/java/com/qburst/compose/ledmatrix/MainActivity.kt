package com.qburst.compose.ledmatrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qburst.compose.ledmatrix.composables.LedMatrixDisplay
import com.qburst.compose.ledmatrix.ui.theme.LEDMatrixDisplayTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LEDMatrixDisplayTheme {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    LedCounterDisplay()

                }

            }
        }
    }
}

@Composable
private fun LedCounterDisplay() {

    var number by remember { mutableStateOf(0) }

    var started by remember { mutableStateOf(false) }

    LaunchedEffect(started) {
        while (started) {

            number += 1

            if (number >= 1000) {
                number = 0
            }

            delay(1000)
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        LedMatrixDisplay(number = number.div(100))

        Spacer(modifier = Modifier.width(8.dp))

        LedMatrixDisplay(number = number.mod(100).div(10))

        Spacer(modifier = Modifier.width(8.dp))

        LedMatrixDisplay(number = number % 10)
    }

    Button(
        onClick = { started = !started },
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            if (started) "Stop" else "Start"
        )
    }

    Button(
        onClick = { number = (0..999).random() },
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            "Randomize"
        )
    }
}
