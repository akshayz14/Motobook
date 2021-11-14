package com.apc.motobook.repository

import com.apc.motobook.dao.VehicleDetailsDao
import com.apc.motobook.model.VehicleDetails

/**
 * Created by Akshay on 26/08/21
 */
class AddVehicleRepository(private val vehicleDetailsDao: VehicleDetailsDao) {


    val getAllVehicles = vehicleDetailsDao.getAll()

    suspend fun insert(vehicleDetails: VehicleDetails): Long {
        return vehicleDetailsDao.insert(vehicleDetails)
    }

    suspend fun update(vehicleDetails: VehicleDetails): Int {
        return vehicleDetailsDao.update(vehicleDetails)
    }

    suspend fun delete(vehicleDetails: VehicleDetails) :Int{

        return vehicleDetailsDao.delete(vehicleDetails)

    }

    suspend fun deleteAll() {
        return vehicleDetailsDao.deleteAll()
    }


}