package com.platformscience.challenge.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.platformscience.challenge.ShipmentListViewModel

class ShipmentsViewModelFactory constructor(private val repository: ShipmentsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ShipmentListViewModel::class.java)) {
            ShipmentListViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("UnknownViewModel")
        }
    }
}