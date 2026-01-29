package com.example.ui_interface.otp

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform

class OtpTImerCountDown {
    companion object {
        fun timer(setDelayTime: Int): Flow<Int> =
            (setDelayTime - 1 downTo 0).asFlow()
                .onEach { delay(1000L) }
                .onStart { emit(setDelayTime) }
                .conflate()
                .transform { remainingSecond: Int ->
                    emit(remainingSecond)
                }
    }
}