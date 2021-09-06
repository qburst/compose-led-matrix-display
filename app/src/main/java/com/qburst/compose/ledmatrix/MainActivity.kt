package com.qburst.compose.ledmatrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qburst.compose.ledmatrix.composables.LedMatrixDisplay
import com.qburst.compose.ledmatrix.composables.LedMatrixStyle
import com.qburst.compose.ledmatrix.composables.LedShape
import com.qburst.compose.ledmatrix.data.Time
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

                    var ledStyle by remember { mutableStateOf(LedMatrixStyle()) } // initialize with the default style

                    LedCounterDisplay(
                        style = ledStyle
                    )

                    ThemePicker(
                        onOnColorPicked = { ledStyle = ledStyle.copy(onColor = it) },
                        onOffColorPicked = { ledStyle = ledStyle.copy(offColor = it) },
                        onShapePicked = { ledStyle = ledStyle.copy(ledShape = it) },
                    )

                    Divider(modifier = Modifier.padding(vertical = 32.dp))

                    Text(
                        "Example usage",
                        modifier = Modifier.padding(bottom = 16.dp),
                        style = TextStyle(
                            color = Color.DarkGray,
                            fontSize = 18.sp
                        )
                    )
                    ClockDisplay()

                }

            }
        }
    }
}

@Composable
private fun ThemePicker(
    onOnColorPicked: (Color) -> Unit,
    onOffColorPicked: (Color) -> Unit,
    onShapePicked: (LedShape) -> Unit
) {

    val colors = listOf(
        Color.Black,
        Color.DarkGray,
        Color.Gray,
        Color.LightGray,
        Color.White,
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Cyan,
        Color.Magenta,
    )

    // TODO: Place the color pickers in a better layout like `LazyVerticalGrid` later
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 16.dp)
    ) {

        Text(
            "Pick the LED color in ON state",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                fontSize = 18.sp
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(colors.size) {
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(40.dp)
                        .border(width = 1.dp, color = Color.Black)
                        .background(color = colors[it])
                        .clickable {
                            onOnColorPicked.invoke(colors[it])
                        }
                )
            }
        }

        Text(
            "Pick the LED color in OFF state",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                fontSize = 18.sp
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(colors.size) {
                Box(
                    modifier = Modifier
                        .width(30.dp)
                        .height(40.dp)
                        .border(width = 1.dp, color = Color.Black)
                        .background(color = colors[it])
                        .clickable {
                            onOffColorPicked.invoke(colors[it])
                        }
                )
            }
        }

        Text(
            "Pick an LED shape",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                fontSize = 18.sp
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {

            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(40.dp)
                    .height(40.dp)
                    .border(
                        shape = RoundedCornerShape(size = 40.dp),
                        width = 2.dp,
                        color = Color.Black
                    )
                    .clickable {
                        onShapePicked.invoke(LedShape.Round)
                    }
            )

            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Black
                    )
                    .clickable {
                        onShapePicked.invoke(LedShape.Rectangle)
                    }
            )

        }

    }


}

@Composable
private fun LedCounterDisplay(
    style: LedMatrixStyle = LedMatrixStyle()
) {

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

        LedMatrixDisplay(number = number.div(100), style = style)

        Spacer(modifier = Modifier.width(8.dp))

        LedMatrixDisplay(number = number.mod(100).div(10), style = style)

        Spacer(modifier = Modifier.width(8.dp))

        LedMatrixDisplay(number = number % 10, style = style)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = { started = !started },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                if (started) "Stop Counter" else "Start Counter"
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
}

@Composable
private fun ClockDisplay() {

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
        offColor = Color(android.graphics.Color.parseColor("#EDEDED"))
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
