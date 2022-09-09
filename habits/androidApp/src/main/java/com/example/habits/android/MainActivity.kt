package com.example.habits.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.habits.Database
import com.example.habits.Greeting
import com.example.habits.Habit
import com.example.habits.rememberDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

abstract class ComposeActivity(private val content: @Composable () -> Unit) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(content = content)
    }
}

class MainActivity : ComposeActivity({
    MaterialTheme {
        Main()
    }
})

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

    HabitColumn()
}

@Composable
fun HabitColumn(database: Database = rememberDatabase()) {
    val habitsListFlow: Flow<List<Habit>> by produceState(emptyFlow()) {
        value = database
            .habitQueries
            .selectAll()
            .asFlow()
            .mapToList()
    }

    val habits by habitsListFlow.collectAsState(emptyList())
    LazyColumn { items(habits) { Text(it.name) } }
}

@Composable
@Preview(showSystemUi = true)
fun MainPreview() {
    Main()
}

fun greet(): String {
    return Greeting().greeting()
}
