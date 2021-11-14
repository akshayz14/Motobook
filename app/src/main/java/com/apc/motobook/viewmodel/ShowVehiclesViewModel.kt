package com.apc.motobook.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.apc.motobook.repository.AddVehicleRepository
import kotlinx.coroutines.flow.collect

/**
 * Created by Akshay on 05/09/21
 */
class ShowVehiclesViewModel(
    private val addVehicleRepository: AddVehicleRepository
) : ViewModel() {

    fun getAllSavedVehicles() = liveData {
        addVehicleRepository.getAllVehicles.collect {
            emit(it)
        }
    }


}