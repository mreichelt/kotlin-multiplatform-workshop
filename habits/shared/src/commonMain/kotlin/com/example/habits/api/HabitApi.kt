package com.example.habits.api

import com.example.habits.network.client
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

interface HabitApi {
    suspend fun getHabits(): List<Habit>
}

class KtorHabitApi(private val baseUrl: Url) : HabitApi {

    override suspend fun getHabits(): List<Habit> {
        val response = client.get(baseUrl) {
            url { appendPathSegments("habits") }
        }
        return Json.decodeFromString<HabitsResponse>(response.body()).habits
    }

}

@Serializable
private class HabitsResponse(
    val habits: List<Habit>,
)
