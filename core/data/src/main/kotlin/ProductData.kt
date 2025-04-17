package com.example.core.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.WriteWith
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Serializable
@Parcelize // so it can be saved in the Bundle, i.e. rememberSaveable can use the default Saver
data class ProductData(
    val code: String,
    @SerialName("product") val productJson: @WriteWith<JsonObjectParceler> JsonObject
) : Parcelable

object JsonObjectParceler : Parceler<JsonObject> {
    override fun create(parcel: Parcel) =
        Json.parseToJsonElement(parcel.readString().orEmpty()).jsonObject

    override fun JsonObject.write(parcel: Parcel, flags: Int) {
        parcel.writeString(toString())
    }
}
