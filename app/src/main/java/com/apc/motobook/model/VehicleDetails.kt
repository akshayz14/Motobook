package com.apc.motobook.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by Akshay on 28/05/21
 */
@Entity(tableName = "vehicleDetails")
data class VehicleDetails(

    @PrimaryKey(autoGenerate = true)
    val vehicleId: Long,
    val vehicleType: String,
    val lastServiceDate: Date,
    val nextServiceDate: Date,
    val serviceInterval: Int,
    val model: String,
    val purchaseYear: Int,
    val registrationNumber: String,
    val insuranceDetails: InsuranceDetails
)
