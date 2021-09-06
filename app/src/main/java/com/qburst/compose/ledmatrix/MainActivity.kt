package com.qburst.compose.ledmatrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.qburst.compose.ledmatrix.ui.theme.LEDMatrixDisplayTheme

val ribbon = listOf(

    // 0
    listOf(0, 1, 1, 1, 0), // index = 0
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 1, 1),
    listOf(1, 0, 1, 0, 1),
    listOf(1, 1, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),

    listOf(0, 0, 0, 0, 0), // Spacer

    // 1
    listOf(0, 0, 1, 0, 0), // index = 8
    listOf(0, 1, 1, 0, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 1, 1, 1, 0),

    listOf(0, 0, 0, 0, 0), // Spacer

    // 2
    listOf(0, 1, 1, 1, 0),  // index = 16
    listOf(1, 0, 0, 0, 1),
    listOf(0, 0, 0, 0, 1),
    listOf(0, 0, 0, 1, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 1, 0, 0, 0),
    listOf(1, 1, 1, 1, 1),

    listOf(0, 0, 0, 0, 0), // Spacer

    // 3
    listOf(0, 1, 1, 1, 0), // index = 24
    listOf(1, 0, 0, 0, 1),
    listOf(0, 0, 0, 0, 1),
    listOf(0, 0, 1, 1, 0),
    listOf(0, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),

    listOf(0, 0, 0, 0, 0), // Spacer

    // 4
    listOf(0, 0, 0, 1, 0),  // index = 32
    listOf(0, 0, 1, 1, 0),
    listOf(0, 1, 0, 1, 0),
    listOf(1, 0, 0, 1, 0),
    listOf(1, 1, 1, 1, 1),
    listOf(0, 0, 0, 1, 0),
    listOf(0, 0, 0, 1, 0),

    listOf(0, 0, 0, 0, 0), // Spacer

    // 5
    listOf(1, 1, 1, 1, 1), // index = 40
    listOf(1, 0, 0, 0, 0),
    listOf(1, 1, 1, 1, 0),
    listOf(0, 0, 0, 0, 1),
    listOf(0, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),

    listOf(0, 0, 0, 0, 0), // Spacer

    // 6
    listOf(0, 1, 1, 1, 0), // index = 48
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 0),
    listOf(1, 1, 1, 1, 0),
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),

    listOf(0, 0, 0, 0, 0), // Spacer

    // 7
    listOf(1, 1, 1, 1, 1), // index = 56
    listOf(0, 0, 0, 0, 1),
    listOf(0, 0, 0, 1, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 1, 0, 0, 0),
    listOf(0, 1, 0, 0, 0),
    listOf(0, 1, 0, 0, 0),

    listOf(0, 0, 0, 0, 0), // Spacer

    // 8
    listOf(0, 1, 1, 1, 0), // index = 64
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),

    listOf(0, 0, 0, 0, 0), // Spacer

    // 9
    listOf(0, 1, 1, 1, 0), // index = 72
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 1),
    listOf(0, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LEDMatrixDisplayTheme {

                Column {

                    var number by remember { mutableStateOf(0) }

                    LedMatrixDisplay(
                        number = number
                    )

                    repeat(10) {

                        Button(
                            onClick = { number = it },
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text("$it")
                        }


                    }
                }

            }
        }
    }
}

@Composable
fun LedMatrixDisplay(
    number: Int = 0
) {

    val characterRow = when (number) {
        in 0..9 -> number * 8
        else -> 0
    }

    // This will "animate" an Integer to the new value, with the given `animationSpec`.
    //
    // In our case, imagine that the previous value was 0.
    //
    // If the new `targetValue` is 8, then it will update the value from 0 to 8, within the given
    // `durationMillis`. On each update, the value will change from 0, 1, 2, 3... up to 8.
    //
    // On each update, the `LedMatrixDisplay` Composable will recompose (because of the "state"
    // change), and the value of this state at that moment is used in the `background(..)`
    // modifier of the `Box`.
    val characterRowAnimated by animateIntAsState(
        targetValue = characterRow,
        animationSpec = tween(durationMillis = 500, easing = LinearEasing)
    )

    Column {

        repeat(7) { row ->

            Row {

                repeat(5) { column ->

                    Box(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(10.dp)
                            .height(10.dp)
                            .background(
                                color = if (ribbon[characterRowAnimated + row][column] == 1) Color.Red else Color.LightGray
                            )

                    )
                }

            }

        }
    }

}