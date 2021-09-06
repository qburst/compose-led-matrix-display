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

val ribbon = listOf(

    // 0
    listOf(0, 1, 1, 1, 0), // index = 0
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 1, 1),
    listOf(1, 0, 1, 0, 1),
    listOf(1, 1, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),

    // 1
    listOf(0, 0, 1, 0, 0), // index = 7
    listOf(0, 1, 1, 0, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 1, 1, 1, 0),

    // 2
    listOf(0, 1, 1, 1, 0),  // index = 14
    listOf(1, 0, 0, 0, 1),
    listOf(0, 0, 0, 0, 1),
    listOf(0, 0, 0, 1, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 1, 0, 0, 0),
    listOf(1, 1, 1, 1, 1),

    // 3
    listOf(0, 1, 1, 1, 0), // index = 21
    listOf(1, 0, 0, 0, 1),
    listOf(0, 0, 0, 0, 1),
    listOf(0, 0, 1, 1, 0),
    listOf(0, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),

    // 4
    listOf(0, 0, 0, 1, 0),  // index = 28
    listOf(0, 0, 1, 1, 0),
    listOf(0, 1, 0, 1, 0),
    listOf(1, 0, 0, 1, 0),
    listOf(1, 1, 1, 1, 1),
    listOf(0, 0, 0, 1, 0),
    listOf(0, 0, 0, 1, 0),

    // 5
    listOf(1, 1, 1, 1, 1), // index = 35
    listOf(1, 0, 0, 0, 0),
    listOf(1, 1, 1, 1, 0),
    listOf(0, 0, 0, 0, 1),
    listOf(0, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),

    // 6
    listOf(0, 1, 1, 1, 0), // index = 42
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 0),
    listOf(1, 1, 1, 1, 0),
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),

    // 7
    listOf(1, 1, 1, 1, 1), // index = 49
    listOf(0, 0, 0, 0, 1),
    listOf(0, 0, 0, 1, 0),
    listOf(0, 0, 1, 0, 0),
    listOf(0, 1, 0, 0, 0),
    listOf(0, 1, 0, 0, 0),
    listOf(0, 1, 0, 0, 0),

    // 8
    listOf(0, 1, 1, 1, 0), // index = 56
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),
    listOf(1, 0, 0, 0, 1),
    listOf(1, 0, 0, 0, 1),
    listOf(0, 1, 1, 1, 0),

    // 9
    listOf(0, 1, 1, 1, 0), // index = 63
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

    val characterRow = when(number){
        in 0..9 -> number * 7
        else -> 0
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
                                color = if( ribbon[characterRow + row][column] == 1 ) Color.Red else Color.LightGray
                            )

                    )
                }

            }

        }
    }

}