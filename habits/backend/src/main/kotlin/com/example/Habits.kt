package com.example

import com.benasher44.uuid.uuidFrom
import com.example.habits.api.Habit

val sampleHabits = listOf(
    Habit(
        id = uuidFrom("00000000-0000-0000-0000-000000000001"),
        name = "Jogging"
    ),
    Habit(
        id = uuidFrom("00000000-0000-0000-0000-000000000002"),
        name = "Meditate"
    ),
    Habit(
        id = uuidFrom("00000000-0000-0000-0000-000000000003"),
        name = "Drink no more than 2 coffees"
    ),
)
