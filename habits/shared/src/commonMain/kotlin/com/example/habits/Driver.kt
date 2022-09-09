package com.example.habits

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

/*@Composable
expect fun rememberDriverFactory(): DriverFactory*/

/*@Composable
fun createDatabase(factory: DriverFactory = rememberDriverFactory()): Database {
    return Database(factory.createDriver())
}*/
