package es.proyecto.mecagoenelactivity.database.daos

import androidx.room.Query
import es.proyecto.mecagoenelactivity.database.entities.Toma

interface TomaDao: BaseDao<Toma> {
    @Query("DELETE FROM tomas where id_medicacion=:id")
    fun deleteByDeviceId(id: Long)

}