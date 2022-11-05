package es.proyecto.proyecto_dam_desde0.database.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate(epoch: Long): Date {
        return Date(epoch)
    }

    @TypeConverter
    fun fromDate(date:Date): Long {
        return date.time
    }
}