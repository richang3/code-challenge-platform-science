package com.platformscience.challenge.data

class ShipmentsRepository constructor(private val shipmentApi: ShipmentApi) {
    suspend fun getShipments() = shipmentApi.getShipments()
}