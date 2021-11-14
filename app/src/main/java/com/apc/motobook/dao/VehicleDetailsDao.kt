package com.apc.motobook.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.apc.motobook.model.VehicleDetails
import kotlinx.coroutines.flow.Flow

/**
 * Created by Akshay on 10/07/21
 */
@Dao
interface VehicleDetailsDao {

    @Query("SELECT * FROM vehicleDetails")
    fun getAll(): Flow<List<VehicleDetails>>


    @Insert
    suspend fun insert(vehicleDetails: VehicleDetails) :Long

//    @Query("SELECT * FROM vehicleDetails")
//    suspend fun getVehicleDetailsFromId(id: v)

    @Update
    suspend fun update(vehicleDetails: VehicleDetails): Int

    @Delete
    suspend fun delete(vehicleDetails: VehicleDetails) :Int

    @Query("DELETE FROM vehicleDetails")
    suspend fun deleteAll()


}