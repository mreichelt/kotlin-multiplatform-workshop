package com.example.stopwatch

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class StopwatchViewModel {

    private val _stopwatchFlow = MutableStateFlow("0")
    val stopwatchFlow: StateFlow<String> = _stopwatchFlow.asStateFlow()
    private val scope = CoroutineScope(Dispatchers.Main)

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

fun interface Closeable {
    fun close()
}

fun <T> Flow<T>.watch(block: (T) -> Unit): Closeable {
    val coroutineScope = CoroutineScope(Dispatchers.Main)
    onEach { block(it) }.launchIn(coroutineScope)
    return Closeable { coroutineScope.cancel() }
}
