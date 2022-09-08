package com.example.habits.api

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuidFrom
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object UuidStringSerializer : KSerializer<Uuid> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("UuidStringSerializer", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Uuid) = encoder.encodeString(value.toString())
    override fun deserialize(decoder: Decoder): Uuid = uuidFrom(decoder.decodeString())
}
