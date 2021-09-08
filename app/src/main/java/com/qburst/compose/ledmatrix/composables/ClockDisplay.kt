package com.qburst.compose.ledmatrix.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.qburst.compose.ledmatrix.data.Time
import kotlinx.coroutines.delay

@Composable
fun ClockDisplay() {

    var time by remember { mutableStateOf(Time.getLatest()) }

    LaunchedEffect(true) {
        while (true) {
            time = Time.getLatest()
            delay(1000)
        }
    }

    val style = LedMatrixStyle(
        ledShape = LedShape.Round,
        ledWidth = 5.dp,
        ledHeight = 5.dp,
        ledSpacing = 0.dp,
        onColor = Color.Black,
        offColor = Color(0xFFEDEDED)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        // Hour
        LedMatrixDisplay(number = time.hour / 10, style = style)
        Spacer(modifier = Modifier.width(4.dp))
        LedMatrixDisplay(number = time.hour % 10, style = style)
        Spacer(modifier = Modifier.width(16.dp))

        // Minutes
        LedMatrixDisplay(number = time.minutes / 10, style = style)
        Spacer(modifier = Modifier.width(4.dp))
        LedMatrixDisplay(number = time.minutes % 10, style = style)
        Spacer(modifier = Modifier.width(16.dp))

        // Seconds
        LedMatrixDisplay(number = time.seconds / 10, style = style)
        Spacer(modifier = Modifier.width(4.dp))
        LedMatrixDisplay(number = time.seconds % 10, style = style)

    }

}