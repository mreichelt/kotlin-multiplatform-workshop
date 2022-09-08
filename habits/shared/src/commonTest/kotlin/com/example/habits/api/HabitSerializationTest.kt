package com.example.habits.api

import com.benasher44.uuid.uuidFrom
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class HabitSerializationTest {

    @Test
    fun `encode a habit`() {
        val jogging = Habit(
            id = uuidFrom("24fdbe87-8f63-4e6a-a3c7-ede48031ebc1"),
            name = "Jogging",
            description = "Get some air and burn those calories",
        )

        val prettyPrint = Json { prettyPrint = true }
        val actualJson = prettyPrint.encodeToString(jogging)

        assertEquals(
            expected = """
                {
                    "id": "24fdbe87-8f63-4e6a-a3c7-ede48031ebc1",
                    "name": "Jogging",
                    "description": "Get some air and burn those calories"
                }
            """.trimIndent(),
            actual = actualJson
        )
    }

    @Test
    fun `decode a habit`() {
        val json = """
            {
                "id": "24fdbe87-8f63-4e6a-a3c7-ede48031ebc1",
                "name": "Jogging",
                "description": "Get some air and burn those calories"
            }
        """.trimIndent()

        assertEquals(
            expected = Habit(
                id = uuidFrom("24fdbe87-8f63-4e6a-a3c7-ede48031ebc1"),
                name = "Jogging",
                description = "Get some air and burn those calories",
            ),
            actual = Json.decodeFromString(json)
        )
    }

}
