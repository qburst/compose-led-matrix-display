package com.qburst.compose.ledmatrix.data

import java.util.*

data class Time(
    val hour: Int,
    val minutes: Int,
    val seconds: Int
) {
    companion object {

        fun getLatest(): Time {
            val calendar = Calendar.getInstance()
            return Time(
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)
            )
        }
    }
}