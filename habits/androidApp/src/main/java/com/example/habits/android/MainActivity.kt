package com.example.habits.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.habits.Greeting

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
    Row(Modifier.fillMaxHeight()) {
        Text(
            text = greet(),
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
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
