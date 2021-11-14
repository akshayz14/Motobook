package com.apc.motobook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apc.motobook.repository.AddVehicleRepository
import com.apc.motobook.repository.ShowVehiclesRepository

/**
 * Created by Akshay on 06/09/21
 */
class ShowVehicleViewModelFactory(private val repository: AddVehicleRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ShowVehiclesViewModel::class.java)) {
            return ShowVehiclesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")


    }


}