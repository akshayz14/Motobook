package com.apc.motobook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apc.motobook.repository.AddVehicleRepository

/**
 * Created by Akshay on 27/08/21
 */
class AddVehicleViewModelFactory(private val repository: AddVehicleRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddVehicleFragmentsViewModel::class.java)) {
            return AddVehicleFragmentsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}