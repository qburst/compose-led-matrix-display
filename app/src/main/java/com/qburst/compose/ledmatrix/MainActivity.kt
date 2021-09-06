package com.qburst.compose.ledmatrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.qburst.compose.ledmatrix.ui.theme.LEDMatrixDisplayTheme

val number00 = listOf(
    listOf(0, 1, 1, 1, 0),
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 1, 1),
    listOf(1, 0, 1, 0, 1),
    listOf(1, 1, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),
)

val number01 = listOf(
    listOf(0, 0, 1, 0, 0),
    listOf(0, 1, 1, 0, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 1, 1, 1, 0),
)

val number02 = listOf(
    listOf(0, 1, 1, 1, 0),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 0, 0, 0, 1),
    listOf(0, 0, 0, 1, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 1, 0, 0, 0),
    listOf(1, 1, 1, 1, 1),
)

val number03 = listOf(
    listOf(0, 1, 1, 1, 0),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 0, 0, 0, 1),
    listOf(0, 0, 1, 1, 0),
    listOf(0, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),
)

val number04 = listOf(
    listOf(0, 0, 0, 1, 0),
    listOf(0, 0, 1, 1, 0),
    listOf(0, 1, 0, 1, 0),
    listOf(1, 0, 0, 1, 0),
    listOf(1, 1, 1, 1, 1),
    listOf(0, 0, 0, 1, 0),
    listOf(0, 0, 0, 1, 0),
)

val number05 = listOf(
    listOf(1, 1, 1, 1, 1),
    listOf(1, 0, 0, 0, 0),
    listOf(1, 1, 1, 1, 0),
    listOf(0, 0, 0, 0, 1),
    listOf(0, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),
)

val number06 = listOf(
    listOf(0, 1, 1, 1, 0),
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 0),
    listOf(1, 1, 1, 1, 0),
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),
)

val number07 = listOf(
    listOf(1, 1, 1, 1, 1),
    listOf(0, 0, 0, 0, 1),
    listOf(0, 0, 0, 1, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 1, 0, 0, 0),
    listOf(0, 1, 0, 0, 0),
    listOf(0, 1, 0, 0, 0),
)

val number08 = listOf(
    listOf(0, 1, 1, 1, 0),
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),
)

val number09 = listOf(
    listOf(0, 1, 1, 1, 0),
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

    val character = when(number){
        0 -> number00
        1 -> number01
        2 -> number02
        3 -> number03
        4 -> number04
        5 -> number05
        6 -> number06
        7 -> number07
        8 -> number08
        9 -> number09
        else -> number00
    }

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
                                color = if( character[row][column] == 1 ) Color.Red else Color.LightGray
                            )

                    )
                }

            }

        }
    }

}