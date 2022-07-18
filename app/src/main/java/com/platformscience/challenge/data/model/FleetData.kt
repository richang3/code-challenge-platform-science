package com.platformscience.challenge.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FleetData(
    @SerialName("shipments")
    val shipments: List<String>?,

    @SerialName("drivers")
    val drivers: List<String>?
)