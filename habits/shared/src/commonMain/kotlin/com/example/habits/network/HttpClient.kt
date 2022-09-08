package com.example.habits.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*

val client by lazy { HttpClient(CIO) }
