package com.example.stopwatch

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StopwatchViewModel {

    private val _stopwatchFlow = MutableStateFlow("0")
    val stopwatchFlow: StateFlow<String> = _stopwatchFlow.asStateFlow()
    private val scope = CoroutineScope(Dispatchers.Main.immediate)

    fun start() {
        scope.launch {
            var counter = 0
            while (true) {
                _stopwatchFlow.emit(counter.toString())
                println("Counter: $counter")

                counter++
                delay(100)
            }
        }
    }

    fun dispose() {
        scope.cancel()
    }

}
