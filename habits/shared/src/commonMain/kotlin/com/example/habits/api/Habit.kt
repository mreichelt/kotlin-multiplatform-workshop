@file:UseSerializers(UuidStringSerializer::class)

package com.example.habits.api

import com.benasher44.uuid.Uuid
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class Habit(
    val id: Uuid,
    val name: String,
    val description: String = "",
)

@Serializable
class HabitsResponse(
    val habits: List<Habit>,
)
