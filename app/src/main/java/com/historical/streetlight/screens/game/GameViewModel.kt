package com.historical.streetlight.screens.game

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.historical.streetlight.utils.NonRepeatingRandom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {

    private var _randomLightNum = mutableStateOf(0)
    val randomLightNum get() = _randomLightNum

    private var _score = mutableStateOf(0)
    val score get() = _score

    private var job: Job? = null
    private var second = 0

    private var _onTimeOut = mutableStateOf(false)
    val onTimeOut get() = _onTimeOut

    fun increaseScore() {
        _score.value += 2
    }

    init {
        viewModelScope.launch {
            randomlyTurnLightOff()
            start()
        }
    }

    fun randomlyTurnLightOff() {
        viewModelScope.launch {
            _randomLightNum.value = NonRepeatingRandom.retrieve()
            restart()
        }
    }

    private fun start() {
        job = viewModelScope.launch {
            while (isActive) {
                delay(1000L)
                second += 1
                if (second != 0) {
                    if (second % 2 == 0) {
                        cancel()
                        _onTimeOut.value = true
                    }
                }
            }
        }
    }

     fun cancel() {
        job?.cancel()
    }

    private fun restart() {
        job?.cancel()
        start()
        _onTimeOut.value = false
    }

    fun resetScore() {
        _score.value = 0
    }
}