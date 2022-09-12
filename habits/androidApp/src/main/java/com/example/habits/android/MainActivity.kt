package com.example.habits.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habits.Greeting
import com.example.stopwatch.StopwatchViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 128.dp)
    ) {
        Text(
            text = greet(),
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )

        val stopwatchViewModel = remember { StopwatchViewModel() }
        val stopwatchState by stopwatchViewModel.stopwatchFlow.collectAsState()
        Stopwatch(
            getStopwatchValue = { stopwatchState },
            startStopwatch = { stopwatchViewModel.start() }
        )
    }
}

@Composable
fun Stopwatch(getStopwatchValue: () -> String, startStopwatch: () -> Unit) {
    Text(
        text = "Counter: " + getStopwatchValue(),
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
    Button(onClick = startStopwatch) {
        Text("Start Stopwatch")
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainPreview() {
    Main()
}

fun greet(): String {
    return Greeting().greeting()
}
