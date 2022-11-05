package es.proyecto.proyecto_dam_desde0.database.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*

abstract class BaseEntity {



    @ColumnInfo(name = "create_at")
    var createAt = Date(System.currentTimeMillis())
}