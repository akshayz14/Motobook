package com.apc.motobook.viewmodel

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apc.motobook.model.VehicleDetails

/**
 * Created by Akshay on 28/05/21
 */
class HomeFragmentViewModel() : ViewModel() {

    var count = 1
    val liveData = MutableLiveData<String>("0")

    fun getUnit() {
        val s = count++
        liveData.value = s.toString()
    }

}