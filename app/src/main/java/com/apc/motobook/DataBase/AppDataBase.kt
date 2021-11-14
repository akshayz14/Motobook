package com.apc.motobook.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.apc.motobook.dao.VehicleDetailsDao
import com.apc.motobook.model.VehicleDetails

/**
 * Created by Akshay on 10/07/21
 */

@Database(entities = arrayOf(VehicleDetails::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun vehicleDetailsDao(): VehicleDetailsDao


    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java, "MotoBookDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}