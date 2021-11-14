package com.apc.motobook.DataBase

import androidx.room.TypeConverter
import com.apc.motobook.model.InsuranceDetails
import com.google.gson.Gson
import org.json.JSONObject
import java.util.*

/**
 * Created by Akshay on 11/07/21
 */
class Converters {

    var gson = Gson()

    @TypeConverter
    fun fromDateToLong(value: Date): Long {
        return value.time
    }

    @TypeConverter
    fun fromLongToDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun fromJsonToString(value: InsuranceDetails) : String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromStringToJson(value: String) : InsuranceDetails {
        return gson.fromJson(value, InsuranceDetails::class.java)
    }

}