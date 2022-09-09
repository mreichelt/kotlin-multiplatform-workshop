package com.example.habits

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember
import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, "habit.db")
    }
}

@Composable
fun rememberDatabase(context: Context = LocalContext.current): Database {
    return remember(context) { Database(DriverFactory(context).createDriver()) }
}