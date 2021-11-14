package com.apc.motobook.viewmodel

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.apc.motobook.model.InsuranceDetails
import com.apc.motobook.model.VehicleDetails
import com.apc.motobook.repository.AddVehicleRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.flow.collect
import java.util.*

/**
 * Created by Akshay on 10/07/21
 */
class AddVehicleFragmentsViewModel(
    private val addVehicleRepository:
    AddVehicleRepository
) : ViewModel() {

    val lastServiceDateLiveData = MutableLiveData<String>("")
    val nextServiceDateLiveData = MutableLiveData<String>("")
    val insuranceExpiryDateLiveData = MutableLiveData<String>("")
    val isDateValidLiveData = MutableLiveData<Boolean>(false)
    val carModel = MutableLiveData<String>("")

    val serviceInterval = MutableLiveData<String>("")
    val registrationNumber = MutableLiveData<String>("")
    val insuranceName = MutableLiveData<String>("")
    val insuranceNumber = MutableLiveData<String>("")
    var selectedRadioButtonLiveData = MutableLiveData<Int>()
    var selectedVehicleTypeLiveData = MutableLiveData<Int>()
    var addButtonClickedLiveData = MutableLiveData<Int>(0)


    val selectedRB: LiveData<Int>
        get() = selectedRadioButtonLiveData

    init {
        selectedRadioButtonLiveData.postValue(-1)
    }

    fun isFormValid(lastServiceDate: String) {
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        try {
            val date = formatter.parse(lastServiceDate)
            Log.d("TAG", "isFormValid: " + date)
            isDateValidLiveData.value = true
        } catch (e: Exception) {
            isDateValidLiveData.value = false
        }
    }

    fun setValueLastService(lastServiceDate: String) {
        lastServiceDateLiveData.value = lastServiceDate
    }

    fun setValueNextService(nextServiceDate: String) {
        nextServiceDateLiveData.value = nextServiceDate
    }

    fun setValueInsuranceExpiry(insuranceExpiryDate: String) {
        insuranceExpiryDateLiveData.value = insuranceExpiryDate
    }

    fun setVehicleType(vehicleTypeValue: Int) {
        selectedVehicleTypeLiveData.value = vehicleTypeValue
    }

    fun saveData() {
        val insuranceDetails = InsuranceDetails(
            insuranceName.value!!,
            getDateFromString(insuranceExpiryDateLiveData.value), insuranceNumber.value!!
        )

        val selectedVehicleType = selectedVehicleTypeLiveData.value
        val vehicleType = when (selectedVehicleType) {
            1 -> "car"
            2 -> "bike"
            3 -> "other"
            else -> {
                "unknown"
            }
        }

        val vehicleDetails = VehicleDetails(
            0, vehicleType,
            getDateFromString(lastServiceDateLiveData.value),
            getDateFromString(nextServiceDateLiveData.value),
            Integer.parseInt(serviceInterval.value!!), carModel.value!!,
            2000, registrationNumber.value!!, insuranceDetails
        )

        viewModelScope.launch {
            val value = addVehicleRepository.insert(vehicleDetails)
            addButtonClickedLiveData.value = 1
        }
    }




    private fun getDateFromString(value: String?): Date {
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val date = LocalDate.parse(value, formatter)
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
    }

    fun getAllSavedVehicles() = liveData {
        addVehicleRepository.getAllVehicles.collect {
            emit(it)
        }
    }

}

