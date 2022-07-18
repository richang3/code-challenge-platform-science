package com.platformscience.challenge.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Shipment (
    @SerialName("driver")
    val driver: String?,

    @SerialName("address")
    val address: String?
)