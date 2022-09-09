package com.example.habits

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, "habit.db")
    }
}

/*@Composable
actual fun rememberDriverFactory(): DriverFactory {
    val context = LocalContext.current
    return remember(context) {
        DriverFactory(context)
    }
}*/
