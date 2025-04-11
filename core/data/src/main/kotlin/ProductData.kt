package com.example.core.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ProductData(
    val code: String,
    @SerialName("product") val productJson: JsonObject
)
