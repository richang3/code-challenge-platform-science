package com.platformscience.challenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platformscience.challenge.data.ShipmentAlgorithm
import com.platformscience.challenge.data.ShipmentsRepository
import com.platformscience.challenge.data.model.Shipment
import kotlinx.coroutines.launch

class ShipmentListViewModel constructor(private val repository: ShipmentsRepository) : ViewModel() {
    val shipments = MutableLiveData<List<Shipment>>()

    fun getShipments() {
        viewModelScope.launch {
            val fleetData = repository.getShipments()
            val formula = ShipmentAlgorithm(fleetData)

            shipments.postValue(formula.getShipments())
        }
    }
}