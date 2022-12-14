package com.example.habits.api

import com.benasher44.uuid.uuidFrom
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertEquals

class HabitsApiTest {

    private val server = MockWebServer()
    private val api = KtorHabitApi(Url(server.url("").toString()))

    @Test
    fun loadEmptyHabits() = runTest {
        server.enqueue(MockResponse().setBody("""{"habits": []}"""))

        val habits: List<Habit> = api.getHabits().habits
        assertTrue(habits.isEmpty())
    }

    @Test
    fun loadHabits() = runTest {
        server.enqueue(
            MockResponse().setBody(
                """
            {
                "habits": [
                    {"id": "00000000-0000-0000-0000-000000000001", "name": "one"},
                    {"id": "00000000-0000-0000-0000-000000000002", "name": "two"}
                ]
            }
            """.trimIndent()
            )
        )

        val habits: List<Habit> = api.getHabits().habits
        assertEquals(
            expected = listOf(
                Habit(id = uuidFrom("00000000-0000-0000-0000-000000000001"), name = "one"),
                Habit(id = uuidFrom("00000000-0000-0000-0000-000000000002"), name = "two"),
            ), actual = habits
        )
    }

}
